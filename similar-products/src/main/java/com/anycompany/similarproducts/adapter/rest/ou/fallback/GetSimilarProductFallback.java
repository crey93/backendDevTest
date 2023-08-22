package com.anycompany.similarproducts.adapter.rest.ou.fallback;

import com.anycompany.similarproducts.adapter.rest.dto.ProductDto;
import com.anycompany.similarproducts.adapter.rest.ou.ProductFeignClient;
import com.anycompany.similarproducts.core.exception.ProductNotFoundException;

import org.springframework.stereotype.Component;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class GetSimilarProductFallback implements ProductFeignClient {
    @Override
    public List<String> getSimilarProductIds(String productId) {
        log.info("[Fallback] Circuit breaker");
        throw new ProductNotFoundException();
    }

    @Override
    public ProductDto getProductDetail(String productId) {
        return null;
    }
}
