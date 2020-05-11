package com.liaojl.test.probe.agent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TransClass {
    private static final Logger log = LoggerFactory.getLogger(TransClass.class);

    public String getNumber() {
        return "我是原始的";
    }
}