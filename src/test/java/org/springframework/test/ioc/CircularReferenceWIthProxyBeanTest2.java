package org.springframework.test.ioc;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.bean.A;
import org.springframework.test.bean.B;
import org.springframework.test.bean.C;
import org.springframework.test.service.HelloService;

public class CircularReferenceWIthProxyBeanTest2 {

    /**
     * Test 1: plain object with no circular dependency (helloService in spring.xml)
     *
     * The BeanPostProcessor returns the original bean. In the final step the second-level cache is empty, so there is no circular dependency, and the initialized bean goes into the first-level cache.
     */
    @Test
    public void test1() {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring.xml");
        HelloService helloService = context.getBean("helloService", HelloService.class);
        helloService.sayHello();
    }


    /**
     * Test 2: proxied object with no circular dependency (c in circular-reference-with-proxy-bean-2.xml)
     *
     * The BeanPostProcessor returns a proxy bean. In the final step the second-level cache is empty, so there is no circular dependency, and the proxy bean goes into the first-level cache.
     */
    @Test
    public void test2() {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:circular-reference-with-proxy-bean-2.xml");
        C c = (C) context.getBean("c");
        c.sayHello();
    }


    /**
     * Test 3: plain objects with a circular dependency (a and b in circular-reference-without-proxy-bean.xml)
     *
     * The BeanPostProcessor returns a plain bean. In the final step the second-level cache has a value, so there is a circular dependency; the bean from the second-level cache goes into the first-level cache so that a.getB() == b.
     *
     */
    @Test
    public void test3() {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:circular-reference-without-proxy-bean.xml");
        // A is a plain object
        A a = (A) context.getBean("a");
        // B is a plain object
        B b = (B) context.getBean("b");

        Assert.assertTrue(a.getB() == b);
        Assert.assertTrue(b.getA() == a);
    }


    /**
     * Test 4: proxied object with a circular dependency (a in circular-reference-with-proxy-bean.xml)
     *
     * The BeanPostProcessor is skipped. In the final step the second-level cache holds the proxy, which goes into the first-level cache.
     */
    @Test
    public void test4() {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:circular-reference-with-proxy-bean.xml");
        // A is a proxy object
        A a = (A) context.getBean("a");
        // B is a plain object
        B b = (B) context.getBean("b");

        a.func();

        // a is the same object in both caches, so b.getA() == a is true
        Assert.assertTrue(b.getA() == a);
    }

}
