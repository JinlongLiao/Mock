package com.dubbo.demo.consumer;


import com.alibaba.dubbo.config.annotation.Reference;
import com.dubbo.demo.common.service.TestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    //@Reference(loadbalance = "random",cluster = "",retries = 2)
//    private TestService testService;
    @Reference(mock = "true", check = false)
    private TestService testService;


    @GetMapping("test")
    public String test() {
        return testService.sayHello();
    }

}
