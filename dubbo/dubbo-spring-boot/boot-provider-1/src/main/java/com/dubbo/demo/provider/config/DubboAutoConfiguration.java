package com.dubbo.demo.provider.config;//package com.dubbo.demo.provider.config;
//
//import javax.annotation.Resource;
//
//import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import com.alibaba.dubbo.config.ApplicationConfig;
//import com.alibaba.dubbo.config.ProtocolConfig;
//import com.alibaba.dubbo.config.ProviderConfig;
//import com.alibaba.dubbo.config.RegistryConfig;
//import com.alibaba.dubbo.rpc.Exporter;
//
///**
// * 多端口提供dubbo服务
// *     当你使用多端口提供服务，使用默认端口提供服务：需要加入在service上加上defaultProvider
// * @author liuyh
// *
// */
//@Configuration
//@ConditionalOnClass(Exporter.class)
//public class DubboAutoConfiguration {
//
//    /**
//     * 默认基于dubbo协议提供服务
//     *
//     * @return
//     */
//    @Bean(name = "rmi")
//    public ProtocolConfig rmi() {
//        // 服务提供者协议配置
//        ProtocolConfig protocolConfig = new ProtocolConfig();
//        protocolConfig.setName("rmi");
//        protocolConfig.setPort(20881);
//        protocolConfig.setThreads(200);
//        return protocolConfig;
//    }
//
//
//    /**
//     * 默认基于dubbo协议提供服务
//     *
//     * @return
//     */
//    @Bean(name = "dubbo")
//    public ProtocolConfig dubbo() {
//        // 服务提供者协议配置
//        ProtocolConfig protocolConfig = new ProtocolConfig();
//        protocolConfig.setName("dubbo");
//        protocolConfig.setPort(20882);
//        protocolConfig.setThreads(200);
//        return protocolConfig;
//    }
//
//}