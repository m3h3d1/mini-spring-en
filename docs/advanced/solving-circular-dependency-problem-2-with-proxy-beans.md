# Solving Circular Dependency Problem (Part 2): With Proxy Beans

!!! info "Code branch"
    [`circular-reference-with-proxy-bean`](https://github.com/DerekYRC/mini-spring/tree/circular-reference-with-proxy-bean)

To solve circular dependencies with proxy beans, we need to expose the proxy bean reference early, not the instantiated bean reference (this is the issue left from the previous section).

Spring uses `singletonFactories` (often called the third-level cache) to handle circular dependencies with proxy beans. After instantiation, we expose the proxy reference (see `AbstractAutowireCapableBeanFactory#doCreateBean`).

When `getBean()` is called, it checks `singletonObjects`, `earlySingletonObjects`, and `singletonFactories` in sequence. If the bean is found in the third-level cache, it is moved to the second-level cache and returned. Finally, the proxy bean is added to `singletonObjects` (see `AbstractAutowireCapableBeanFactory`).

Tests are in `CircularReferenceWithProxyBeanTest`.
