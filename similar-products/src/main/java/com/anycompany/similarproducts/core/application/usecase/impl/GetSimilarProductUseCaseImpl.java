package com.anycompany.similarproducts.core.application.usecase.impl;

import com.anycompany.similarproducts.common.UseCase;
import com.anycompany.similarproducts.core.application.port.GetProductPort;
import com.anycompany.similarproducts.core.application.usecase.GetSimilarProductUseCase;
import com.anycompany.similarproducts.core.domain.Product;

import java.util.List;
import java.util.Objects;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCase
public class GetSimilarProductUseCaseImpl implements GetSimilarProductUseCase {

    private final GetProductPort port;

    @Override
    public List<Product> getSimilarProduct(String productId) {
        return port.getSimilarProductIds(productId).stream().distinct().map(port::getProductDetail)
                   .filter(Objects::nonNull)
                   .toList();
    }
}
