package com.anycompany.similarproducts.core.domain;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Product {
    String id;
    String name;
    double price;
    boolean availability;
}
