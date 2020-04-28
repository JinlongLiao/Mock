package com.liaojl.annotation.dto;


import com.liaojl.annotation.MyTestAnntation;

@MyTestAnntation
public class BaseITestII implements BaseService {
    @Override
    public void getInfo() {
        System.out.println("Hello,BaseITestII");
    }
}
