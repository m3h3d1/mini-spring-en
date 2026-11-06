# JDK-Based Dynamic Proxy

!!! info "Code branch"
    [`jdk-dynamic-proxy`](https://github.com/DerekYRC/mini-spring/tree/jdk-dynamic-proxy)

`AopProxy` is the abstract interface for obtaining proxy objects, and `JdkDynamicAopProxy` is the concrete implementation based on JDK dynamic proxies. `TargetSource` is the wrapper for the target object being proxied. `MethodInterceptor` is the method interceptor, a key concept from AOP Alliance. As the name suggests, it intercepts methods and can add proxy behavior before or after the method is executed.

**Test**
```java
public class DynamicProxyTest {

	@Test
	public void testJdkDynamicProxy() throws Exception {
		WorldService worldService = new WorldServiceImpl();

		AdvisedSupport advisedSupport = new AdvisedSupport();
		TargetSource targetSource = new TargetSource(worldService);
		WorldServiceInterceptor methodInterceptor = new WorldServiceInterceptor();
		MethodMatcher methodMatcher = new AspectJExpressionPointcut("execution(* org.springframework.test.service.WorldService.explode(..))").getMethodMatcher();
		advisedSupport.setTargetSource(targetSource);
		advisedSupport.setMethodInterceptor(methodInterceptor);
		advisedSupport.setMethodMatcher(methodMatcher);

		WorldService proxy = (WorldService) new JdkDynamicAopProxy(advisedSupport).getProxy();
		proxy.explode();
	}
}
```
