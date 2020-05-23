package com.dubbo.demo.consumer;

 import org.apache.dubbo.config.spring.context.annotation.EnableDubboConfig;
 import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author LIUYH
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableDubboConfig
public class ConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }

}
