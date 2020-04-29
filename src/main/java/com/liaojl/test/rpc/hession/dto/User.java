package com.liaojl.test.rpc.hession.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {
    private String name;
    private Integer age;

    public User() {
    }

    public User(String name, Integer age) {
        super();
        this.name = name;
        this.age = age;
    }
}