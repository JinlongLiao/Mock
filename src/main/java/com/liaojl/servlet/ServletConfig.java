package com.liaojl.servlet;

import com.caucho.hessian.server.HessianServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author LiaoJL
 * @description TODO
 * @file ServletConfig.java
 * @email jinlongliao@foxmail.com
 * @date 2020/4/29 15:58
 */
@Configuration
public class ServletConfig {

    @Bean
    public ServletRegistrationBean heServletRegisterBen() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(
                new HessianServlet(), "api/service"
        );
        servletRegistrationBean.addInitParameter("service-class", "com.liaojl.test.rpc.hession.nct.BasicService");
        return servletRegistrationBean;
    }
}
