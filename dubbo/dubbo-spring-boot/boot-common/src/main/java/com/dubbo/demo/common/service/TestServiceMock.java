package com.dubbo.demo.common.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

//@Service(loadbalance = "random",retries = 2,cluster = "")
@Service
@Component
public class TestServiceMock implements TestService {

    @Override
    public String sayHello() {
        throw new RuntimeException("添加、修改、刪除等操作失敗");
    }
}
