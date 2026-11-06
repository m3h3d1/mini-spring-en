# Common Advice Types: BeforeAdvice, AfterAdvice, AfterReturningAdvice, ThrowsAdvice

!!! info "Code branch"
    [`common-advice`](https://github.com/DerekYRC/mini-spring/tree/common-advice)

Spring refines the AOP alliance's Advice into different types, including `BeforeAdvice`, `AfterAdvice`, `AfterReturningAdvice`, and `ThrowsAdvice`. These can be implemented by extending `MethodInterceptor`.

For now, we only implement `BeforeAdvice`. If interested, other types of Advice can be implemented. We define a `MethodBeforeAdviceInterceptor` to execute the `BeforeAdvice` method before the target method is invoked.
- [x] BeforeAdvice
- [ ] AfterAdvice
- [ ] AfterReturningAdvice
- [ ] ThrowsAdvice

**Test:**
```java
public class WorldServiceBeforeAdvice implements MethodBeforeAdvice {

	@Override
	public void before(Method method, Object[] args, Object target) throws Throwable {
		System.out.println("BeforeAdvice: do something before the earth explodes");
	}
}
```

```java
public class DynamicProxyTest {

	private AdvisedSupport advisedSupport;

	@Before
	public void setup() {
		WorldService worldService = new WorldServiceImpl();

		advisedSupport = new AdvisedSupport();
		TargetSource targetSource = new TargetSource(worldService);
		MethodMatcher methodMatcher = new AspectJExpressionPointcut("execution(* org.springframework.test.service.WorldService.explode(..))").getMethodMatcher();
		advisedSupport.setTargetSource(targetSource);
		advisedSupport.setMethodMatcher(methodMatcher);
	}

	@Test
	public void testBeforeAdvice() throws Exception {
		// Set BeforeAdvice
		WorldServiceBeforeAdvice beforeAdvice = new WorldServiceBeforeAdvice();
		MethodBeforeAdviceInterceptor methodInterceptor = new MethodBeforeAdviceInterceptor(beforeAdvice);
		advisedSupport.setMethodInterceptor(methodInterceptor);

		WorldService proxy = (WorldService) new ProxyFactory(advisedSupport).getProxy();
		proxy.explode();
	}
}
```
