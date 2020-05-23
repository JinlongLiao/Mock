package com.dubbo.demo.common.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @desc 用户信息表
 * @author liuyh
 */
@Data
@Entity
@Table(name = "sys_user")
@DynamicInsert
@DynamicUpdate
@Accessors(chain = true)
@EntityListeners(AuditingEntityListener.class)
public class SysUser implements Serializable {
    private static final long serialVersionUID = 292641124936908999L;

    /**
     * 编号
     */
    @Id
    @Column(name = "id")
    public String id;

    /**
     * 部门ID
     */
     @Column(name = "dept_id")
     private String deptId;

    /**
     * 用户账号
     */
     @Column(name = "user_name")
     private String userName;

    /**
     * 用户昵称
     */
     @Column(name = "nick_name")
     private String nickName;

    /**
     * 用户类型（0系统用户）
     */
     @Column(name = "user_type")
     private String userType;

    /**
     * 用户邮箱
     */
     @Column(name = "email")
     private String email;

    /**
     * 手机号码
     */
     @Column(name = "mobile")
     private String mobile;

    /**
     * 用户性别（0男 1女 2未知）
     */
     @Column(name = "sex")
     private String sex;

    /**
     * 头像地址
     */
     @Column(name = "avatar")
     private String avatar;

    /**
     * 密码
     */
     @Column(name = "password")
     private String password;

    /**
     * 帐号状态（0正常 1停用）
     */
     @Column(name = "status")
     private String status;

    /**
     * 排序
     */
     @Column(name = "order_num")
     private Integer orderNum;

    /**
     * 删除标志（0代表存在 2代表删除）
     */
     @Column(name = "del_flag")
     private String delFlag;

    /**
     * 最后登陆IP
     */
     @Column(name = "login_ip")
     private String loginIp;

    /**
     * 最后登陆时间
     */
     @Column(name = "login_date")
     @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
     @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
     private LocalDateTime loginDate;

    /**
     * 创建者
     */
    @Column(name = "create_by")
    public String createBy;

    /**
     * 创建时间
     */
    @CreatedDate
    @Column(name = "create_time")
    @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public LocalDateTime createTime;

    /**
     * 更新者
     */
    @Column(name = "update_by")
    public String updateBy;

    /**
     * 更新时间
     */
    @LastModifiedDate
    @Column(name = "update_time")
    @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public LocalDateTime updateTime;

    /**
     * 备注
     */
    @Column(name = "remark")
    public String remark;

}