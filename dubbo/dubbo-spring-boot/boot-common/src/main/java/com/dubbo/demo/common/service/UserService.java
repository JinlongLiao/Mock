package com.dubbo.demo.common.service;

import com.dubbo.demo.common.entity.SysUser;

import java.util.List;

/**
 * @author LIUYH
 */
public interface UserService {

    /**
     * 查询所有用户信息
     * @return
     */
    List<SysUser> findAll();

    /**
     * 支付
     * @return
     */
    String pay();

}
