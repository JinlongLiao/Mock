package com.liaojl.test.rpc.hession;

import com.caucho.hessian.client.HessianProxyFactory;
import com.liaojl.test.rpc.hession.nct.IBasicApi;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;

public class HessionTest {
    private static final Logger log = LoggerFactory.getLogger(HessionTest.class);

    @Test
    public void test() throws MalformedURLException {
        String url = "http://localhost:8080/api/service";
        HessianProxyFactory factory = new HessianProxyFactory();
        IBasicApi api = (IBasicApi) factory.create(IBasicApi.class, url);
        api.setUserName("lvfang");
        log.info(api.sayHello());
        log.info(api.getUser().getName());
        log.info(String.valueOf(api.getUser().getAge()));
    }
}
