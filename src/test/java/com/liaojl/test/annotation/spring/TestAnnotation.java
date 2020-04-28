package com.liaojl.test.annotation.spring;


import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface TestAnnotation {
    String[] value() default {};
}
