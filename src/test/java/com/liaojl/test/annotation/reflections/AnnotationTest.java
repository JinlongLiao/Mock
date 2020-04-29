package com.liaojl.test.annotation.reflections;

import com.liaojl.test.speed.JavaTest;
import org.junit.BeforeClass;
import org.junit.Test;
import org.reflections.Reflections;
import org.reflections.scanners.*;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * @author LiaoJL
 * @description TODO
 * @file AnnotationTest.java
 * @CopyRight (C) http://www.koal.com/
 * @email jinlongliao@foxmail.com
 * @date 2020/4/20 16:42
 */
public class AnnotationTest {
    private static final Logger log = LoggerFactory.getLogger(AnnotationTest.class);
    static Reflections reflections;

    @BeforeClass
    public static void init() {
        reflections = new Reflections(new ConfigurationBuilder()
                .setUrls(Collections.singletonList(ClasspathHelper.forClass(JavaTest.class)))
                .forPackages("com.liaojl.test")
                .setScanners(
                        new SubTypesScanner(true),
                        new TypeAnnotationsScanner(),
                        new FieldAnnotationsScanner(),
                        new MethodAnnotationsScanner(),
                        new MethodParameterScanner(),
                        new MethodParameterNamesScanner(),
                        new MemberUsageScanner()));
    }

    /**
     * 根据@InterceptorPassController 获取不拦截路径
     */
    @Test
    public void getNotInterceptor() {
        List<String> notInterceptorList = new ArrayList<String>();
        Set<Class<?>> typesAnnotatedWith = reflections.getTypesAnnotatedWith(TestController.class);
        Set<Method> methodsAnnotatedWith = reflections.getMethodsAnnotatedWith(TestController.class);
        for (Class<?> el : typesAnnotatedWith) {
            //取到标签对象
            TestController annotation = el.getAnnotation(TestController.class);
            notInterceptorList.add(annotation.name());
            log.info("classes: {}", el.getName());
        }
        for (Method el : methodsAnnotatedWith) {
            //取到标签对象
            TestController annotation = el.getAnnotation(TestController.class);
            notInterceptorList.add(annotation.name());
            log.info("classes: {}", el.getName());
        }
    }
}
