package com.bootcamp.mvp_m6.mapper;

import com.bootcamp.mvp_m6.dto.product.ProductFormDTO;
import com.bootcamp.mvp_m6.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductMapper {


    @Mapping(source = "brandId", target = "brand.id")
    @Mapping(source = "brandId", target = "category.id")
    @Mapping(target = "orderItems", ignore = true)
    Product toEntity(ProductFormDTO dto);
}
