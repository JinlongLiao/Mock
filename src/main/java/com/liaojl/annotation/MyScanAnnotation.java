package com.liaojl.annotation;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author liaojinlong
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import(MyTestScannerRegistrar.class)
public @interface MyScanAnnotation {
    String[] basePackages() default {};

    Class<? extends Annotation> annotationClass() default Annotation.class;

    Class<?> markerInterface() default Class.class;
}
