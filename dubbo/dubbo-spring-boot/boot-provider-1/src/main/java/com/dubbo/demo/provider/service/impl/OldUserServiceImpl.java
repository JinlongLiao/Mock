package com.dubbo.demo.provider.service.impl;

import com.dubbo.demo.common.entity.SysUser;
import com.dubbo.demo.common.service.UserService;
import com.dubbo.demo.provider.dao.UserDao;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Service(group = "weixin")
@Component
public class OldUserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public List<SysUser> findAll() {
        return userDao.findAll();
    }

    @Override
    public String pay() {
        return "weixin";
    }
}
