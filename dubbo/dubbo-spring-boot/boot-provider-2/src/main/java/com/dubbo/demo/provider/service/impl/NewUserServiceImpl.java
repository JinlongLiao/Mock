package com.dubbo.demo.provider.service.impl;

import com.dubbo.demo.common.entity.SysUser;
import com.dubbo.demo.common.service.UserService;
import com.dubbo.demo.provider.dao.UserDao;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Service(group = "aliPay")
@Component
public class NewUserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public List<SysUser> findAll() {
        List<String> ids = new ArrayList<>();
        ids.add("1");
        return userDao.findAllById(ids);
    }

    @Override
    public String pay() {
        return "aliPay";
    }
}
