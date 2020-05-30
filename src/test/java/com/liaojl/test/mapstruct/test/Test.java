package com.liaojl.test.mapstruct.test;

import com.liaojl.test.mapstruct.Order;
import com.liaojl.test.mapstruct.OrderQueryParam;
import com.liaojl.test.mapstruct.mapper.OrderMapper;
import org.mapstruct.factory.Mappers;

import static org.junit.Assert.assertEquals;

/**
 * @author: liaojl
 * @date: 2020/5/30 18:09
 * @since: 1.0
 */
public class Test {
    @org.junit.Test
    public void entity2queryParam() {
        Order order = new Order();
        order.setId(12345L);
        order.setOrderSn("orderSn");
        order.setOrderType(0);
        order.setReceiverKeyword("keyword");
        order.setSourceType(1);
        order.setStatus(2);
        OrderMapper mapper = Mappers.getMapper(OrderMapper.class);
        OrderQueryParam orderQueryParam = mapper.entity2queryParam(order);
        assertEquals(orderQueryParam.getOrderSn(), order.getOrderSn());
        assertEquals(orderQueryParam.getOrderType(), order.getOrderType());
        assertEquals(orderQueryParam.getReceiverKeyword(), order.getReceiverKeyword());
        assertEquals(orderQueryParam.getSourceType(), order.getSourceType());
        assertEquals(orderQueryParam.getStatus(), order.getStatus());
    }
}
