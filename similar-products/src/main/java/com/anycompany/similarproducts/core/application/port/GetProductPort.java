package com.anycompany.similarproducts.core.application.port;

import com.anycompany.similarproducts.core.domain.Product;

import java.util.List;

public interface GetProductPort {
    List<String> getSimilarProductIds(String productId);

    Product getProductDetail(String productId);
}
