# PropertyPlaceholderConfigurer

!!! info "Code branch"
    [`property-placeholder-configurer`](https://github.com/DerekYRC/mini-spring/tree/property-placeholder-configurer)

It is common to store configuration data in a `properties` file and reference it in an XML file using placeholders.

The implementation is simple: before bean instantiation, we edit the `BeanDefinition`, parse the placeholders in the XML, and replace them with values from the `properties` file. Since `BeanFactoryPostProcessor` has the ability to edit `BeanDefinition`, `PropertyPlaceholderConfigurer` extends `BeanFactoryPostProcessor`.

**Test:**
<br> car.properties
```properties
brand=lamborghini
```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
	         http://www.springframework.org/schema/beans/spring-beans.xsd
		 http://www.springframework.org/schema/context
		 http://www.springframework.org/schema/context/spring-context-4.0.xsd">

    <bean class="org.springframework.beans.factory.PropertyPlaceholderConfigurer">
        <property name="location" value="classpath:car.properties" />
    </bean>

    <bean id="car" class="org.springframework.test.bean.Car">
        <property name="brand" value="${brand}" />
    </bean>

</beans>
```

```java
public class PropertyPlaceholderConfigurerTest {

	@Test
	public void test() throws Exception {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:property-placeholder-configurer.xml");

		Car car = applicationContext.getBean("car", Car.class);
		assertThat(car.getBrand()).isEqualTo("lamborghini");
	}
}
```
