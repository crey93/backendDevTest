package com.anycompany.similarproducts.adapter.rest.ou;

import com.anycompany.similarproducts.adapter.rest.dto.ProductDto;
import com.anycompany.similarproducts.adapter.rest.ou.fallback.GetSimilarProductFallback;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "product-client", url = "${rest-client.product-client.url}", 
        fallback = GetSimilarProductFallback.class)
public interface ProductFeignClient {
    @GetMapping(value = "/{productId}/similarids")
    List<String> getSimilarProductIds(@PathVariable String productId);

    @GetMapping(value = "/{productId}")
    @Cacheable(cacheNames = "detail-product-cache", key = "#root.args[0]")
    ProductDto getProductDetail(@PathVariable String productId);
}
