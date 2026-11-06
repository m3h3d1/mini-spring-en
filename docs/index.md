# <img src="assets/spring-framework.png" width="80" height="80"> mini-spring

## About

**mini-spring** is a simplified version of the Spring framework. It helps you quickly understand Spring's source code and core principles. The project extracts Spring’s essential logic while keeping the code minimal, preserving key features like IoC, AOP, resource loading, event listeners, type conversion, container extension points, bean lifecycle and scope, application context, and more.

**Sister projects:**
 - [**mini-spring-cloud**](https://github.com/DerekYRC/mini-spring-cloud) **(simplified Spring Cloud framework)**
 - [**mini-netty**](https://github.com/DerekYRC/mini-netty) **(simplified Netty framework)**
 - [**mini-claude-code**](https://github.com/DerekYRC/mini-claude-code) **(simplified Claude Code)**

If you find this project helpful, please give it a **STAR, thanks!!!**

## Features

### Basics: IoC

- [IoC](ioc/simple-bean-container.md)
  - [Simple Bean Container](ioc/simple-bean-container.md)
  - [BeanDefinition and BeanDefinitionRegistry](ioc/beandefinition-and-beandefinitionregistry.md)
  - [Bean Instantiation Strategy](ioc/bean-instantiation-strategy.md)
  - [Populating Bean Properties](ioc/populating-bean-properties.md)
  - [Injecting a Bean into Another Bean](ioc/injecting-a-bean-into-another-bean.md)
  - [Resources and Resource Loaders](ioc/resources-and-resource-loaders.md)
  - [Defining Beans in XML](ioc/defining-beans-in-xml.md)
  - [Container Extension Mechanisms (BeanFactoryPostProcessor & BeanPostProcessor)](ioc/bean-factory-post-processor-and-bean-post-processor.md)
  - [ApplicationContext](ioc/applicationcontext.md)
  - [Bean Initialization and Destruction Methods](ioc/bean-initialization-and-destruction-methods.md)
  - [Aware Interface](ioc/aware-interface.md)
  - [Bean Scope - Adding Prototype Support](ioc/bean-scope-adding-prototype-support.md)
  - [FactoryBean](ioc/factorybean.md)
  - [Container Events and Event Listeners](ioc/container-events-and-event-listeners.md)

### Basics: AOP

- [AOP](aop/pointcut-expression.md)
  - [Pointcut Expressions](aop/pointcut-expression.md)
  - [JDK-Based Dynamic Proxy](aop/jdk-based-dynamic-proxy.md)
  - [CGLIB-Based Dynamic Proxy](aop/cglib-based-dynamic-proxy.md)
  - [AOP Proxy Factory](aop/aop-proxy-factory.md)
  - [Common Advice Types: BeforeAdvice, AfterAdvice, AfterReturningAdvice, ThrowsAdvice](aop/common-advice-beforeadvice-afteradvice-afterreturningadvice-throwsadvice.md)
  - [PointcutAdvisor: Combination of Pointcut and Advice](aop/pointcutadvisor-pointcut-and-advice-combination.md)
  - [Dynamic Proxy in Bean Lifecycle](aop/dynamic-proxy-in-bean-lifecycle.md)

### Extension Features

- [PropertyPlaceholderConfigurer](extensions/propertyplaceholderconfigurer.md)
- [Package Scan](extensions/package-scan.md)
- [@Value Annotation](extensions/value-annotation.md)
- [Dependency Injection with @Autowired](extensions/autowired-annotation.md)
- [Type Conversion (Part 1)](extensions/type-conversion-part-1.md)
- [Type Conversion (Part 2)](extensions/type-conversion-part-2.md)

### Advanced Topics

- [Solving Circular Dependency Problem (Part 1): Without Proxy Objects](advanced/solving-circular-dependency-problem-1-without-proxy-objects.md)
- [Solving Circular Dependency Problem (Part 2): With Proxy Beans](advanced/solving-circular-dependency-problem-2-with-proxy-beans.md)

#### Others

- [Proxy bean properties not set (discovered and fixed by kerwin89)](extensions/bug-fix-no-property-set-for-proxy-bean-discovered-and-fixed-by-kerwin89.md)
- [Support for lazy loading and multiple aspects (by zqczgl)](advanced/support-for-lazy-init-and-multi-advice-by-zqczgl.md)

## Usage

Start with [Simple Bean Container](ioc/simple-bean-container.md), or use the sidebar.

## Star History

[![Star History Chart](https://star-history.dera.page/svg?repos=DerekYRC/mini-spring&type=Date)](https://star-history.dera.page/#DerekYRC/mini-spring&Date)
