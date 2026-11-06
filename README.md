# <img src="docs/assets/spring-framework.png" width="80" height="80"> mini-spring

## About

**mini-spring** is a simplified version of the Spring framework. It helps you quickly understand Spring's source code and core principles. The project extracts Spring’s essential logic while keeping the code minimal, preserving key features like IoC, AOP, resource loading, event listeners, type conversion, container extension points, bean lifecycle and scope, application context, and more.

**Sister projects:**
 - [**mini-spring-cloud**](https://github.com/DerekYRC/mini-spring-cloud) **(simplified Spring Cloud framework)**
 - [**mini-netty**](https://github.com/DerekYRC/mini-netty) **(simplified Netty framework)**
 - [**mini-claude-code**](https://github.com/DerekYRC/mini-claude-code) **(simplified Claude Code)**

If you find this project helpful, please give it a **STAR, thanks!!!**

## Features

### Basics: IoC

- [IoC](https://m3h3d1.github.io/mini-spring-en/ioc/simple-bean-container/)
  - [Simple Bean Container](https://m3h3d1.github.io/mini-spring-en/ioc/simple-bean-container/)
  - [BeanDefinition and BeanDefinitionRegistry](https://m3h3d1.github.io/mini-spring-en/ioc/beandefinition-and-beandefinitionregistry/)
  - [Bean Instantiation Strategy](https://m3h3d1.github.io/mini-spring-en/ioc/bean-instantiation-strategy/)
  - [Populating Bean Properties](https://m3h3d1.github.io/mini-spring-en/ioc/populating-bean-properties/)
  - [Injecting a Bean into Another Bean](https://m3h3d1.github.io/mini-spring-en/ioc/injecting-a-bean-into-another-bean/)
  - [Resources and Resource Loaders](https://m3h3d1.github.io/mini-spring-en/ioc/resources-and-resource-loaders/)
  - [Defining Beans in XML](https://m3h3d1.github.io/mini-spring-en/ioc/defining-beans-in-xml/)
  - [Container Extension Mechanisms (BeanFactoryPostProcessor & BeanPostProcessor)](https://m3h3d1.github.io/mini-spring-en/ioc/bean-factory-post-processor-and-bean-post-processor/)
  - [ApplicationContext](https://m3h3d1.github.io/mini-spring-en/ioc/applicationcontext/)
  - [Bean Initialization and Destruction Methods](https://m3h3d1.github.io/mini-spring-en/ioc/bean-initialization-and-destruction-methods/)
  - [Aware Interface](https://m3h3d1.github.io/mini-spring-en/ioc/aware-interface/)
  - [Bean Scope - Adding Prototype Support](https://m3h3d1.github.io/mini-spring-en/ioc/bean-scope-adding-prototype-support/)
  - [FactoryBean](https://m3h3d1.github.io/mini-spring-en/ioc/factorybean/)
  - [Container Events and Event Listeners](https://m3h3d1.github.io/mini-spring-en/ioc/container-events-and-event-listeners/)

### Basics: AOP

- [AOP](https://m3h3d1.github.io/mini-spring-en/aop/pointcut-expression/)
  - [Pointcut Expressions](https://m3h3d1.github.io/mini-spring-en/aop/pointcut-expression/)
  - [JDK-Based Dynamic Proxy](https://m3h3d1.github.io/mini-spring-en/aop/jdk-based-dynamic-proxy/)
  - [CGLIB-Based Dynamic Proxy](https://m3h3d1.github.io/mini-spring-en/aop/cglib-based-dynamic-proxy/)
  - [AOP Proxy Factory](https://m3h3d1.github.io/mini-spring-en/aop/aop-proxy-factory/)
  - [Common Advice Types: BeforeAdvice, AfterAdvice, AfterReturningAdvice, ThrowsAdvice](https://m3h3d1.github.io/mini-spring-en/aop/common-advice-beforeadvice-afteradvice-afterreturningadvice-throwsadvice/)
  - [PointcutAdvisor: Combination of Pointcut and Advice](https://m3h3d1.github.io/mini-spring-en/aop/pointcutadvisor-pointcut-and-advice-combination/)
  - [Dynamic Proxy in Bean Lifecycle](https://m3h3d1.github.io/mini-spring-en/aop/dynamic-proxy-in-bean-lifecycle/)

### Extension Features

- [PropertyPlaceholderConfigurer](https://m3h3d1.github.io/mini-spring-en/extensions/propertyplaceholderconfigurer/)
- [Package Scan](https://m3h3d1.github.io/mini-spring-en/extensions/package-scan/)
- [@Value Annotation](https://m3h3d1.github.io/mini-spring-en/extensions/value-annotation/)
- [Dependency Injection with @Autowired](https://m3h3d1.github.io/mini-spring-en/extensions/autowired-annotation/)
- [Type Conversion (Part 1)](https://m3h3d1.github.io/mini-spring-en/extensions/type-conversion-part-1/)
- [Type Conversion (Part 2)](https://m3h3d1.github.io/mini-spring-en/extensions/type-conversion-part-2/)

### Advanced Topics

- [Solving Circular Dependency Problem (Part 1): Without Proxy Objects](https://m3h3d1.github.io/mini-spring-en/advanced/solving-circular-dependency-problem-1-without-proxy-objects/)
- [Solving Circular Dependency Problem (Part 2): With Proxy Beans](https://m3h3d1.github.io/mini-spring-en/advanced/solving-circular-dependency-problem-2-with-proxy-beans/)

#### Others

- [Proxy bean properties not set (discovered and fixed by kerwin89)](https://m3h3d1.github.io/mini-spring-en/extensions/bug-fix-no-property-set-for-proxy-bean-discovered-and-fixed-by-kerwin89/)
- [Support for lazy loading and multiple aspects (by zqczgl)](https://m3h3d1.github.io/mini-spring-en/advanced/support-for-lazy-init-and-multi-advice-by-zqczgl/)

## Usage

Read the full walkthrough on the [documentation site](https://m3h3d1.github.io/mini-spring-en/).

## Star History

[![Star History Chart](https://star-history.dera.page/svg?repos=DerekYRC/mini-spring&type=Date)](https://star-history.dera.page/#DerekYRC/mini-spring&Date)
