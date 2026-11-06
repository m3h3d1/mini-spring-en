# Package Scan

!!! info "Code branch"
    [`package-scan`](https://github.com/DerekYRC/mini-spring/tree/package-scan)

In the bean lifecycle, package scanning is simply about scanning classes with specific annotations, extracting relevant information from the class, and assembling it into a `BeanDefinition` that is then registered in the container.

The process of parsing the `<context:component-scan />` tag in `XmlBeanDefinitionReader` to scan classes and register `BeanDefinition` in the container is implemented in `ClassPathBeanDefinitionScanner#doScan`.

**Test:**
```java
@Component
public class Car {

}
```

package-scan.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
	         http://www.springframework.org/schema/beans/spring-beans.xsd
		 http://www.springframework.org/schema/context
		 http://www.springframework.org/schema/context/spring-context-4.0.xsd">

    <context:component-scan base-package="org.springframework.test.bean"/>

</beans>
```

```java
public class PackageScanTest {

	@Test
	public void testScanPackage() throws Exception {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:package-scan.xml");

		Car car = applicationContext.getBean("car", Car.class);
		assertThat(car).isNotNull();
	}
}
```
