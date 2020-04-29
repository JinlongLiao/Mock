package com.liaojl.test.rpc.rmi.server;

import com.liaojl.test.rpc.rmi.MyService;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.RMISocketFactory;

public class ServerMain {
    private static final Logger log = LoggerFactory.getLogger(ServerMain.class);

    @Test
    public void test() throws Exception {
        //注册服务
        LocateRegistry.createRegistry(8866);
        //指定通信端口，防止被防火墙拦截
        RMISocketFactory.setSocketFactory(new CustomerSocketFactory());
        //创建服务
        MyService myService = new MyServiceImpl();
        Naming.bind("rmi://localhost:8866/myService", myService);
        log.info("RMI 服务端启动正常");
        Thread.sleep(10000);

    }

}