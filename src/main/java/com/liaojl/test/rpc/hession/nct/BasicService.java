package com.liaojl.test.rpc.hession.nct;

import com.liaojl.test.rpc.hession.dto.User;

/**
 * @author LiaoJL
 * @description TODO
 * @file BasicService.java
 * @email jinlongliao@foxmail.com
 * @date 2020/4/29 16:01
 */
public class BasicService implements IBasicApi {
    private String name;

    @Override
    public boolean setUserName(String name) {
        this.name = name;
        return false;
    }

    @Override
    public String sayHello() {
        return "Hello " + name + ",Welcome to Hessian!";
    }

    @Override
    public User getUser() {
        return new User(name, 23);
    }
}