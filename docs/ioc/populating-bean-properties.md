# Populating Bean Properties

!!! info "Code branch"
    [`populate-bean-with-property-values`](https://github.com/DerekYRC/mini-spring/tree/populate-bean-with-property-values)

- `BeanDefinition` now includes `PropertyValues` to store bean properties.
- After instantiating a bean, properties are set via `AbstractAutowireCapableBeanFactory#applyPropertyValues`.

**Test:**
```java
public class PopulateBeanWithPropertyValuesTest {

	@Test
	public void testPopulateBeanWithPropertyValues() throws Exception {
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
		PropertyValues propertyValues = new PropertyValues();
		propertyValues.addPropertyValue(new PropertyValue("name", "derek"));
		propertyValues.addPropertyValue(new PropertyValue("age", 18));
		BeanDefinition beanDefinition = new BeanDefinition(Person.class, propertyValues);
		beanFactory.registerBeanDefinition("person", beanDefinition);

		Person person = (Person) beanFactory.getBean("person");
		System.out.println(person);
		assertThat(person.getName()).isEqualTo("derek");
		assertThat(person.getAge()).isEqualTo(18);
	}
}
```
