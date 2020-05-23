package com.dubbo.demo.provider.dao;

import com.dubbo.demo.common.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

/**
 * @author LIUYH
 */
@Component
public interface UserDao extends JpaRepository<SysUser,String> {

}
