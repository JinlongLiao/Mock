package com.liaojl.test.rpc.hession.nct;

import com.liaojl.test.rpc.hession.dto.User;

public interface IBasicApi {

    public boolean setUserName(String name);

    public String sayHello();

    public User getUser();
}