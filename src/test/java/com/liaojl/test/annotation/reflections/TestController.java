package com.liaojl.test.annotation.reflections;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;

@Documented
@Retention(value = RetentionPolicy.RUNTIME)
@Target({TYPE, METHOD})
public @interface TestController {
    String name() default "";

    String value() default "";

}
