# FactoryBean

!!! info "Code branch"
    [`factory-bean`](https://github.com/DerekYRC/mini-spring/tree/factory-bean)

`FactoryBean` is a special type of bean. When this bean is requested from the container, the container doesn't return the bean itself but instead returns the value from the `FactoryBean#getObject` method. This allows complex beans to be defined programmatically.

The logic is simple: when the container detects that a bean is of type `FactoryBean`, it calls its `getObject` method to return the actual bean. If `FactoryBean#isSingleton` is `true`, the resulting bean is cached and reused on subsequent requests. See changes in `AbstractBeanFactory#getBean`.

**Test:** 
<br> factory-bean.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
	         http://www.springframework.org/schema/beans/spring-beans.xsd
		 http://www.springframework.org/schema/context
		 http://www.springframework.org/schema/context/spring-context-4.0.xsd">

    <bean id="car" class="org.springframework.test.common.CarFactoryBean">
        <property name="brand" value="porsche"/>
    </bean>

</beans>
```

```java
public class CarFactoryBean implements FactoryBean<Car> {

	private String brand;

	@Override
	public Car getObject() throws Exception {
		Car car = new Car();
		car.setBrand(brand);
		return car;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}
}
```

```java
public class FactoryBeanTest {

	@Test
	public void testFactoryBean() throws Exception {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:factory-bean.xml");

		Car car = applicationContext.getBean("car", Car.class);
		applicationContext.getBean("car");
		assertThat(car.getBrand()).isEqualTo("porsche");
	}
}
```
