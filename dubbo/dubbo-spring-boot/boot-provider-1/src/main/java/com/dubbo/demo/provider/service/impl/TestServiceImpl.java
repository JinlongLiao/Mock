package com.dubbo.demo.provider.service.impl;

import com.dubbo.demo.common.service.TestService;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

//@Service(loadbalance = "random",retries = 2,cluster = "")
@Service
@Component
public class TestServiceImpl implements TestService {

    @Override
    public String sayHello() {
        System.out.println("我是第一个服务提供者");
        return "我是第一个服务提供者";
    }
}
