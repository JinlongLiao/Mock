package com.liaojl.annotation.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author liaojinlong
 */
public interface BaseService {
    Logger log = LoggerFactory.getLogger(BaseService.class);

    /**
     *
     */
    void getInfo();
}
