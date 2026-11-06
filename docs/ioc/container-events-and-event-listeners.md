# Container Events and Event Listeners

!!! info "Code branch"
    [`event-and-event-listener`](https://github.com/DerekYRC/mini-spring/tree/event-and-event-listener)

The `ApplicationContext` container provides comprehensive event publishing and listener functionality.

The `ApplicationEventMulticaster` interface is the abstract interface for registering listeners and publishing events. `AbstractApplicationContext` contains an instance of its implementation, giving the `ApplicationContext` container the ability to register listeners and publish events. In the `AbstractApplicationContext#refresh` method, `ApplicationEventMulticaster` is instantiated, listeners are registered, and the container refresh event (`ContextRefreshedEvent`) is published. In the `AbstractApplicationContext#doClose` method, the container close event (`ContextClosedEvent`) is published.

**Test:**
<br> event-and-event-listener.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
	         http://www.springframework.org/schema/beans/spring-beans.xsd
		 http://www.springframework.org/schema/context
		 http://www.springframework.org/schema/context/spring-context-4.0.xsd">

    <bean class="org.springframework.test.common.event.ContextRefreshedEventListener"/>

    <bean class="org.springframework.test.common.event.CustomEventListener"/>

    <bean class="org.springframework.test.common.event.ContextClosedEventListener"/>
</beans>
```

```java
public class EventAndEventListenerTest {

	@Test
	public void testEventListener() throws Exception {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:event-and-event-listener.xml");
		applicationContext.publishEvent(new CustomEvent(applicationContext));

		applicationContext.registerShutdownHook(); // Or use applicationContext.close() to manually close the container
	}
}
```

Observe Output：
```
org.springframework.test.common.event.ContextRefreshedEventListener
org.springframework.test.common.event.CustomEventListener
org.springframework.test.common.event.ContextClosedEventListener
```
