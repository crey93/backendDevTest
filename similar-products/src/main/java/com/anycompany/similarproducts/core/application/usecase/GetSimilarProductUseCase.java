package com.anycompany.similarproducts.core.application.usecase;

import com.anycompany.similarproducts.core.domain.Product;

import java.util.List;

public interface GetSimilarProductUseCase {
    List<Product> getSimilarProduct(String productId);
}
