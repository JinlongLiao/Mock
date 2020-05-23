package com.dubbo.demo.consumer;

import com.dubbo.demo.service.TestService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author LIUYH
 */
public class ConsumerRun {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-consumer.xml");
        TestService testDubboService = (TestService) applicationContext.getBean("dubboTest");
        System.out.println(testDubboService.sayHello("Hello Dubbo !!!"));
        TestService testRmiService = (TestService) applicationContext.getBean("rmiTest");
        System.out.println(testRmiService.sayHello("Hello Rmi !!!"));
    }
}
