package com.dubbo.demo.provider;

import org.apache.dubbo.config.spring.context.annotation.EnableDubboConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author LIUYH
 */
@EnableDubboConfig
@EnableTransactionManagement
@SpringBootApplication(scanBasePackages = {"com.dubbo.demo"})
@EnableJpaRepositories({"com.dubbo.demo.provider.dao"})
@EntityScan({"com.dubbo.demo.common.entity"})
@EnableJpaAuditing
public class Provider2Application {

    public static void main(String[] args) {
        SpringApplication.run(Provider2Application.class, args);
    }

}
