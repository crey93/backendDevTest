package com.anycompany.similarproducts.core.exception;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException() {
        super("Product Not found");
    }
}
