package com.anycompany.similarproducts.adapter.rest.mapper;

import com.anycompany.similarproducts.adapter.rest.dto.ProductDto;
import com.anycompany.similarproducts.core.domain.Product;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProductMapper {
    
    List<ProductDto> toDto(List<Product> product);
    Product toModel(ProductDto productDto);
}
