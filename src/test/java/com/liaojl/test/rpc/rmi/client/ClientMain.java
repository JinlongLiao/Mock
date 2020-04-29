package com.liaojl.test.rpc.rmi.client;


import com.liaojl.test.rpc.rmi.MyService;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.rmi.Naming;

public class ClientMain {
    private static final Logger log = LoggerFactory.getLogger(ClientMain.class);

    @Test
    public void test() throws Exception {
        //服务引入
        MyService myService = (MyService) Naming.lookup("rmi://localhost:8866/myService");
        //调用远程方法
        log.info("RMI 服务端调用返回：" + myService.say("MySelf"));
    }

}