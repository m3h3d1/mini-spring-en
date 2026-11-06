# Solving Circular Dependency Problem (Part 1): Without Proxy Objects

!!! info "Code branch"
    [`circular-reference-without-proxy-bean`](https://github.com/DerekYRC/mini-spring/tree/circular-reference-without-proxy-bean)

Although this is in the advanced section, the solution to the circular dependency problem is actually very simple.

First, let's understand why circular dependencies occur in Spring. For example, consider the following code:

```java
public class A {

	private B b;

	//getter and setter
}
```

```java
public class B {

	private A a;

	//getter and setter
}
```

```xml
<beans>
    <bean id="a" class="org.springframework.test.bean.A">
        <property name="b" ref="b"/>
    </bean>
    <bean id="b" class="org.springframework.test.bean.B">
        <property name="a" ref="a"/>
    </bean>
</beans>
```

A depends on B, and B depends on A, creating a circular dependency. During container loading, the dependency process happens as follows:

- Instantiate A, find dependency B, then instantiate B.
- Instantiate B, find dependency A, then instantiate A.
- Repeat, causing infinite recursion and stack overflow.

The key to solving this problem lies in when to add the instantiated bean to the container — before or after setting properties. In the current process, beans are added to `singletonObjects` after instantiation and property setting. To solve this, we can change the order so the bean is added to `singletonObjects` right after instantiation, exposing the reference early, and then setting properties. This changes the flow to:

- Step 1: GetBean(A), check `singletonObjects`, A is not in it, instantiate A and add to `singletonObjects`, set property B, find dependency B, try GetBean(B).
- Step 2: GetBean(B), check `singletonObjects`, B is not in it, instantiate B and add to `singletonObjects`, set property A, find dependency A, try GetBean(A).
- Step 3: GetBean(A), check `singletonObjects`, A is in it, return A.
- Step 4: B gets A and sets property A, then return B.
- Step 5: A gets B, sets property B, then return A.

Thus, adjusting the timing of adding beans to `singletonObjects` after instantiation solves the circular dependency. To align with Spring, we introduce a second-level cache `earlySingletonObjects`, where the bean is stored after instantiation. During `getBean()`, we check both caches (`singletonObjects` and `earlySingletonObjects`).

Tests are in `CircularReferenceWithoutProxyBeanTest#testCircularReference`.

However, adding the second-level cache does not solve circular dependencies with proxy beans. The reason is that the bean in the `earlySingletonObjects` cache is the instantiated bean, while the bean in the `singletonObjects` cache is the proxy (the proxy is returned in `BeanPostProcessor#postProcessAfterInitialization`). Therefore, when A is proxied, B receives the instantiated version of A, not the proxied version. This leads to `b.getA() != a`, as shown in `CircularReferenceWithProxyBeanTest`.

The next section addresses this.
