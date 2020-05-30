package com.liaojl.test.mapstruct.mapper;

import com.liaojl.test.mapstruct.Order;
import com.liaojl.test.mapstruct.OrderQueryParam;
import org.mapstruct.Mapper;

@Mapper
public interface OrderMapper {
    OrderQueryParam entity2queryParam(Order order);
}