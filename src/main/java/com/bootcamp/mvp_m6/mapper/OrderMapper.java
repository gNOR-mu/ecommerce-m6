package com.bootcamp.mvp_m6.mapper;

import com.bootcamp.mvp_m6.dto.cart.NewOrderDTO;
import com.bootcamp.mvp_m6.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creationDate", ignore = true)
    @Mapping(target = "orderItems", ignore = true)
    Order toEntity(NewOrderDTO dto);

}
