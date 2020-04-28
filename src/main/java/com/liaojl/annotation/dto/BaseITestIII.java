package com.liaojl.annotation.dto;

/**
 * @author liaojinlong
 */
public class BaseITestIII implements BaseService {
    @Override
    public void getInfo() {
        log.info("Hello,BaseITestIII");
    }
}
