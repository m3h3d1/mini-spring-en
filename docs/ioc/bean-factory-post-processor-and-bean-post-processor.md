# BeanFactoryPostProcessor & BeanPostProcessor

!!! info "Code branch"
    [`bean-factory-post-processor-and-bean-post-processor`](https://github.com/DerekYRC/mini-spring/tree/bean-factory-post-processor-and-bean-post-processor)

`BeanFactoryPostProcessor` and `BeanPostProcessor` are two heavyweight interfaces in the Spring framework. Understanding the role of these interfaces provides a solid grasp of the core principles of Spring. To make it easier to understand, they are implemented in two sections.

`BeanFactoryPostProcessor` is a container extension mechanism in Spring that allows modification of bean definition information (i.e., `BeanDefinition`) before bean instantiation. Key implementations include `PropertyPlaceholderConfigurer` and `CustomEditorConfigurer`. The role of `PropertyPlaceholderConfigurer` is to replace placeholders in XML files with values from properties files, while `CustomEditorConfigurer` handles type conversion. The implementation of `BeanFactoryPostProcessor` is straightforward; refer to the unit test `BeanFactoryPostProcessorAndBeanPostProcessorTest#testBeanFactoryPostProcessor` for more details.

`BeanPostProcessor` is also a container extension mechanism provided by Spring. Unlike `BeanFactoryPostProcessor`, `BeanPostProcessor` modifies or replaces beans **after** bean instantiation. It plays a critical role in AOP implementation.

The two methods of `BeanPostProcessor` are executed before and after the bean's initialization method (which will be discussed later). Key implementations can be found in the unit test `BeanFactoryPostProcessorAndBeanPostProcessorTest#testBeanPostProcessor` and the method `AbstractAutowireCapableBeanFactory#initializeBean`. Some minor adjustments have been made, but these can be ignored for now.

```java
public interface BeanPostProcessor {
	/**
	 * Executed before the bean's initialization method
	 */
	Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException;

	/**
	 * Executed after the bean's initialization method
	 */
	Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException;
}
```

Next section introduces `ApplicationContext`, which can automatically detect `BeanFactoryPostProcessor` and `BeanPostProcessor`. These can be configured in the XML file without manually adding them to the `BeanFactory`.

**Test:**
```java
public class BeanFactoryProcessorAndBeanPostProcessorTest {

	@Test
	public void testBeanFactoryPostProcessor() throws Exception {
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
		XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
		beanDefinitionReader.loadBeanDefinitions("classpath:spring.xml");

		// Modify BeanDefinition properties before bean instantiation
		CustomBeanFactoryPostProcessor beanFactoryPostProcessor = new CustomBeanFactoryPostProcessor();
		beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);

		Person person = (Person) beanFactory.getBean("person");
		System.out.println(person);
		// Name changed to ivy in CustomBeanFactoryPostProcessor
		assertThat(person.getName()).isEqualTo("ivy");
	}

	@Test
	public void testBeanPostProcessor() throws Exception {
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
		XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
		beanDefinitionReader.loadBeanDefinitions("classpath:spring.xml");

		// Add BeanPostProcessor to modify bean properties after instantiation
		CustomerBeanPostProcessor customerBeanPostProcessor = new CustomerBeanPostProcessor();
		beanFactory.addBeanPostProcessor(customerBeanPostProcessor);

		Car car = (Car) beanFactory.getBean("car");
		System.out.println(car);
		// Brand changed to lamborghini in CustomerBeanPostProcessor
		assertThat(car.getBrand()).isEqualTo("lamborghini");
	}
}
```
