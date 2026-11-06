# Pointcut Expression

!!! info "Code branch"
    [`pointcut-expression`](https://github.com/DerekYRC/mini-spring/tree/pointcut-expression)

A **Joinpoint** is a specific method in a class where proxy operations need to be executed (only method-level JoinPoint is supported). A **Pointcut** defines the expression that captures the JoinPoint.

The most commonly used pointcut expression is AspectJ’s pointcut expression. To match a class, define the `ClassFilter` interface; to match a method, define the `MethodMatcher` interface. A **Pointcut** needs to match both the class and the method, and includes both `ClassFilter` and `MethodMatcher`. `AspectJExpressionPointcut` is an implementation of a Pointcut that supports AspectJ expressions, with a simple implementation that supports only the `execution` function.

**Test:**
```java
public class HelloService {
	public String sayHello() {
		System.out.println("hello");
		return "hello";
	}
}
```

```java
public class PointcutExpressionTest {

	@Test
	public void testPointcutExpression() throws Exception {
		AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut("execution(* org.springframework.test.service.HelloService.*(..))");
		Class<HelloService> clazz = HelloService.class;
		Method method = clazz.getDeclaredMethod("sayHello");

		assertThat(pointcut.matches(clazz)).isTrue();
		assertThat(pointcut.matches(method, clazz)).isTrue();
	}
}
```
