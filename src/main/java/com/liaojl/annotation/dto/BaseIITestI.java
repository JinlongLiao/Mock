package com.liaojl.annotation.dto;


import com.liaojl.annotation.MyTestAnntation;

@MyTestAnntation
public class BaseIITestI implements BaseService {
    @Override
    public void getInfo() {
        log.info("Hello,BaseIITestI");
    }
}
