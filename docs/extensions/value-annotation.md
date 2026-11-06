# @Value Annotation

!!! info "Code branch"
    [`value-annotation`](https://github.com/DerekYRC/mini-spring/tree/value-annotation)

The `@Value` annotation, like `@Autowired`, is processed through `BeanPostProcessor`. The `InstantiationAwareBeanPostProcessor` adds the `postProcessPropertyValues` method, which is executed after bean instantiation but before setting properties, as seen in `AbstractAutowireCapableBeanFactory#doCreateBean`.

The `AutowiredAnnotationBeanPostProcessor` is introduced to handle annotations like `@Value` and `@Autowired`. The processing of these annotations will be covered in the next section. The processor is added to the container in `ClassPathBeanDefinitionScanner#doScan`. In `AutowiredAnnotationBeanPostProcessor#postProcessPropertyValues`, the string resolver `StringValueResolver` is added to the `BeanFactory` via `PropertyPlaceholderConfigurer`.

**Test:**
```java
@Component
public class Car {

	@Value("${brand}")
	private String brand;
}
```

value-annotation.xml
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

    <context:component-scan base-package="org.springframework.test.bean"/>

</beans>
```

car.properties
```properties
brand=lamborghini
```

```java
public class ValueAnnotationTest {

	@Test
	public void testValueAnnotation() throws Exception {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:value-annotation.xml");

		Car car = applicationContext.getBean("car", Car.class);
		assertThat(car.getBrand()).isEqualTo("lamborghini");
	}
}
```
