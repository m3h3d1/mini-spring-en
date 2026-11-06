# Type Conversion (Part 1)

!!! info "Code branch"
    [`type-conversion-first-part`](https://github.com/DerekYRC/mini-spring/tree/type-conversion-first-part)

Spring defines three types of converters in the `org.springframework.core.convert.converter` package: `Converter`, `ConverterFactory`, and `GenericConverter`.

### 1. Converter
```java
public interface Converter<S, T> {

	/**
	 * Convert the source type to the target type
	 */
	T convert(S source);
}
```
The `Converter` interface can convert an object of type `S` to an object of type `T`. For example, here is an implementation that converts a `String` to an `Integer`:
```java
public class StringToIntegerConverter implements Converter<String, Integer> {
	@Override
	public Integer convert(String source) {
		return Integer.valueOf(source);
	}
}
```
**Use:**
```java
Integer num = new StringToIntegerConverter().convert("8888");
```

### 2. ConverterFactory
```java
public interface ConverterFactory<S, R> {

	<T extends R> Converter<S, T> getConverter(Class<T> targetType);
}
```

The `Converter<S, T>` interface is suitable for one-to-one type conversions. For example, to convert a `String` to `Integer`, `Long`, `Float`, `Double`, or `Decimal`, you would need to implement separate converters like `StringToInteger`, `StringToLongConverter`, etc. This approach can become cumbersome and unclean.

The `ConverterFactory` interface is more suitable for one-to-many type conversions. It allows you to convert one type to other types or their subclasses. For instance, to convert a `String` to multiple `Number` types like `Integer`, `Long`, `Float`, etc., you can implement a single `ConverterFactory`:
```java
public class StringToNumberConverterFactory implements ConverterFactory<String, Number> {

	@Override
	public <T extends Number> Converter<String, T> getConverter(Class<T> targetType) {
		return new StringToNumber<T>(targetType);
	}

	private static final class StringToNumber<T extends Number> implements Converter<String, T> {

		private final Class<T> targetType;

		public StringToNumber(Class<T> targetType) {
			this.targetType = targetType;
		}

		@Override
		public T convert(String source) {
			if (source.length() == 0) {
				return null;
			}

			if (targetType.equals(Integer.class)) {
				return (T) Integer.valueOf(source);
			} else if (targetType.equals(Long.class)) {
				return (T) Long.valueOf(source);
			}
			// TODO handle other number types

			else {
				throw new IllegalArgumentException(
						"Cannot convert String [" + source + "] to target class [" + targetType.getName() + "]");
			}
		}
	}

}
```
**Use:**
```java
StringToNumberConverterFactory converterFactory = new StringToNumberConverterFactory();
Converter<String, Integer> stringToIntegerConverter = converterFactory.getConverter(Integer.class);
Integer num = stringToIntegerConverter.convert("8888");
```

### 3. GenericConverter
```java
public interface GenericConverter {

	Set<ConvertiblePair> getConvertibleTypes();

	Object convert(Object source, Class sourceType, Class targetType);
}
```
Here is an implementation to convert `String` to `Boolean` using `GenericConverter`:
```java
public class StringToBooleanConverter implements GenericConverter {
	@Override
	public Set<ConvertiblePair> getConvertibleTypes() {
		return Collections.singleton(new ConvertiblePair(String.class, Boolean.class));
	}

	@Override
	public Object convert(Object source, Class sourceType, Class targetType) {
		return Boolean.valueOf((String) source);
	}
}
```
**Use:**
```java
Boolean flag = new StringToBooleanConverter().convert("true", String.class, Boolean.class);
```

`ConversionService` is the core interface of the type conversion system. It integrates the three types of converters mentioned earlier. `GenericConversionService` is its implementation, and `DefaultConversionService` adds built-in converters on top of it.

**Test:** See `TypeConversionFirstPartTest`
