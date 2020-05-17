package com.liaojl;

import com.liaojl.annotation.MyScanAnnotation;
import com.liaojl.annotation.MyTestAnntation;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
@MyScanAnnotation(basePackages = {"com.liaojl"},
        annotationClass = MyTestAnntation.class)
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}