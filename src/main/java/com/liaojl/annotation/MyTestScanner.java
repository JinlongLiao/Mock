package com.liaojl.annotation;

import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Set;

/**
 * @author liaojinlong
 */
public class MyTestScanner extends ClassPathBeanDefinitionScanner {

    private Class<? extends Annotation> annotationClass;

    private Class<?> MarkerInterface;

    public Class<?> getMarkerInterface() {
        return MarkerInterface;
    }

    public void setMarkerInterface(Class<?> markerInterface) {
        MarkerInterface = markerInterface;
    }

    public Class<? extends Annotation> getAnnotationClass() {
        return annotationClass;
    }

    public void setAnnotationClass(Class<? extends Annotation> annotationClass) {
        this.annotationClass = annotationClass;
    }

    public MyTestScanner(BeanDefinitionRegistry registry) {
        super(registry);
    }

    public void registerFilters() {
        if (!this.annotationClass.isInstance(Annotation.class)) {
            addIncludeFilter(new AnnotationTypeFilter(this.annotationClass));
        }
    }

    @Override
    protected Set<BeanDefinitionHolder> doScan(String... basePackages) {
        Set<BeanDefinitionHolder> beanDefinitions = super.doScan(basePackages);
        if (beanDefinitions.isEmpty()) {
            logger.info("No MyScanAnnotation class was found in '" + Arrays.toString(basePackages) + "' package. Please check your configuration.");
        }

        return beanDefinitions;
    }
}
