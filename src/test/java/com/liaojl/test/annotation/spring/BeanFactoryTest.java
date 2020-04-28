package com.liaojl.test.annotation.spring;

import com.liaojl.test.annotation.spring.scannere.MyTestScanner;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ScannedGenericBeanDefinition;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.classreading.SimpleMetadataReaderFactory;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

public class BeanFactoryTest {
    private static final Logger log = LoggerFactory.getLogger(BeanFactoryTest.class);

    @Test
    public void scanner1() throws IOException, ClassNotFoundException {
        Set<BeanDefinition> candidates = new LinkedHashSet<>();
//        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        ResourcePatternResolver resourcePatternResolver = new AnnotationConfigApplicationContext();
        //这里特别注意一下类路径必须这样写
        //获取指定包下的所有类
        Resource[] resources = resourcePatternResolver.getResources("classpath*:com/liaojl/test/*/*.class");

        MetadataReaderFactory metadata = new SimpleMetadataReaderFactory();
        for (Resource resource : resources) {
            log.info(String.valueOf(resource));
            MetadataReader metadataReader = metadata.getMetadataReader(resource);
            ScannedGenericBeanDefinition sbd = new ScannedGenericBeanDefinition(metadataReader);
            sbd.setResource(resource);
            sbd.setSource(resource);
            candidates.add(sbd);
        }
        for (BeanDefinition beanDefinition : candidates) {
            String classname = beanDefinition.getBeanClassName();
            //扫描controller注解
            TestAnnotation testAnnotation = Class.forName(classname).getAnnotation(TestAnnotation.class);
            if (testAnnotation != null) {
                log.info("classname:{}", classname);
            }
        }
    }

    @Test
    public void scanner2() throws IOException, ClassNotFoundException {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        MyTestScanner scanner = new MyTestScanner(applicationContext);
        scanner.setAnnotationClass(TestAnnotation.class);
        scanner.registerFilters();
        int scan = scanner.scan("com.liaojl.test");
        log.info("扫描到的类数量：{}", scan);
    }

}

