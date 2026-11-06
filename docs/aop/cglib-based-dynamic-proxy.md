# CGLIB-Based Dynamic Proxy

!!! info "Code branch"
    [`cglib-dynamic-proxy`](https://github.com/DerekYRC/mini-spring/tree/cglib-dynamic-proxy)

The logic behind CGLIB-based dynamic proxy is simple, as seen in `CglibAopProxy`. Unlike JDK dynamic proxies, which generate proxy objects for interfaces at runtime, CGLIB dynamic proxies generate subclass bytecode files at runtime. This means that the target class does not need to implement any interface.

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
	public void testCglibDynamicProxy() throws Exception {
		WorldService proxy = (WorldService) new CglibAopProxy(advisedSupport).getProxy();
		proxy.explode();
	}
}
```
