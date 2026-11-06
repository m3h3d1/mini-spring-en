# AOP Proxy Factory

!!! info "Code branch"
    [`proxy-factory`](https://github.com/DerekYRC/mini-spring/tree/proxy-factory)

Added `ProxyFactory` for AOP proxies, which decides between JDK dynamic proxy or CGLIB dynamic proxy based on the `AdvisedSupport#proxyTargetClass` property.

**Test:**
```java
public class DynamicProxyTest {

	private AdvisedSupport advisedSupport;

	@Before
	public void setup() {
		WorldService worldService = new WorldServiceImpl();

		advisedSupport = new AdvisedSupport();
		TargetSource targetSource = new TargetSource(worldService);
		WorldServiceInterceptor methodInterceptor = new WorldServiceInterceptor();
		MethodMatcher methodMatcher = new AspectJExpressionPointcut("execution(* org.springframework.test.service.WorldService.explode(..))").getMethodMatcher();
		advisedSupport.setTargetSource(targetSource);
		advisedSupport.setMethodInterceptor(methodInterceptor);
		advisedSupport.setMethodMatcher(methodMatcher);
	}

	@Test
	public void testProxyFactory() throws Exception {
		// Use JDK dynamic proxy
		advisedSupport.setProxyTargetClass(false);
		WorldService proxy = (WorldService) new ProxyFactory(advisedSupport).getProxy();
		proxy.explode();

		// Use CGLIB dynamic proxy
		advisedSupport.setProxyTargetClass(true);
		proxy = (WorldService) new ProxyFactory(advisedSupport).getProxy();
		proxy.explode();
	}
}
```
