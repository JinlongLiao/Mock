package com.dubbo.demo.provider;


import com.dubbo.demo.service.TestService;

/**
 * @author LIUYH
 */
public class TestDubboServiceImpl implements TestService {

    @Override
    public String sayHello(String name) {
        System.out.println(name);
        return name;
    }
}
