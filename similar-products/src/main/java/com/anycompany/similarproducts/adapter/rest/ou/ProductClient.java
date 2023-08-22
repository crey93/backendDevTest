package com.anycompany.similarproducts.adapter.rest.ou;

import com.anycompany.similarproducts.adapter.rest.dto.ProductDto;
import com.anycompany.similarproducts.adapter.rest.mapper.ProductMapper;
import com.anycompany.similarproducts.core.application.port.GetProductPort;
import com.anycompany.similarproducts.core.domain.Product;
import com.anycompany.similarproducts.core.exception.ProductNotFoundException;

import org.springframework.stereotype.Component;

import java.util.List;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class ProductClient implements GetProductPort {

    private final ProductFeignClient productFeignClient;
    private final ProductMapper mapper;

    @Override
    public List<String> getSimilarProductIds(String productId) {
        List<String> similarProductIds;
        try {
            similarProductIds = productFeignClient.getSimilarProductIds(productId);
        } catch (FeignException e) {
            log.info(e.getMessage());
            throw new ProductNotFoundException();
        }
        return similarProductIds;

    }

    @Override
    public Product getProductDetail(String productId) {
        ProductDto productDto = null;
        try {
            productDto = productFeignClient.getProductDetail(productId);
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return mapper.toModel(productDto);
    }
}
