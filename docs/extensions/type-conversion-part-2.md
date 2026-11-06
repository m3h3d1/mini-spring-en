# Type Conversion (Part 2)

!!! info "Code branch"
    [`type-conversion-second-part`](https://github.com/DerekYRC/mini-spring/tree/type-conversion-second-part)

In the previous section, we implemented the type conversion system in Spring. In this section, we integrate the type conversion capabilities into the container.

To make it easier to use, Spring provides a `ConversionService` factory bean: `ConversionServiceFactoryBean`.

If a `ConversionService` is defined, it will be set into the container in the `AbstractApplicationContext#finishBeanFactoryInitialization` method.

There are two points in time when type conversion occurs:

- When filling bean properties: See `AbstractAutowireCapableBeanFactory#applyPropertyValues`.
- When processing `@Value` annotations: See `AutowiredAnnotationBeanPostProcessor#postProcessPropertyValues`.

You might wonder how basic type conversion works if no `ConversionService` is defined. Spring maintains an older type conversion mechanism for backward compatibility. If no `ConversionService` is defined, it uses this old mechanism for basic type conversions, so you don't need to worry about it.

**Test:**
```java
public class Car {

	private int price;

	private LocalDate produceDate;
}
```

```java
public class StringToLocalDateConverter implements Converter<String, LocalDate> {

	private final DateTimeFormatter DATE_TIME_FORMATTER;

	public StringToLocalDateConverter(String pattern) {
		DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(pattern);
	}

	@Override
	public LocalDate convert(String source) {
		return LocalDate.parse(source, DATE_TIME_FORMATTER);
	}
}
```

type-conversion-second-part.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
	         http://www.springframework.org/schema/beans/spring-beans.xsd
		 http://www.springframework.org/schema/context
		 http://www.springframework.org/schema/context/spring-context-4.0.xsd">

    <bean id="car" class="org.springframework.test.bean.Car">
        <property name="price" value="1000000"/>
        <property name="produceDate" value="2021-01-01"/>
    </bean>

    <bean id="conversionService" class="org.springframework.context.support.ConversionServiceFactoryBean">
        <property name="converters" ref="converters"/>
    </bean>

    <bean id="converters" class="org.springframework.test.common.ConvertersFactoryBean"/>

</beans>
```

```java
public class TypeConversionSecondPartTest {

	@Test
	public void testConversionService() throws Exception {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:type-conversion-second-part.xml");

		Car car = applicationContext.getBean("car", Car.class);
		assertThat(car.getPrice()).isEqualTo(1000000);
		assertThat(car.getProduceDate()).isEqualTo(LocalDate.of(2021, 1, 1));
	}
}
```
