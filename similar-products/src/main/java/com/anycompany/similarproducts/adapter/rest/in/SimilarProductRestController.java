package com.anycompany.similarproducts.adapter.rest.in;


import com.anycompany.similarproducts.adapter.rest.dto.ProductDto;
import com.anycompany.similarproducts.adapter.rest.mapper.ProductMapper;
import com.anycompany.similarproducts.core.application.usecase.GetSimilarProductUseCase;
import com.anycompany.similarproducts.core.exception.ProductNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/product/")
@RequiredArgsConstructor
@CrossOrigin("*")
public class SimilarProductRestController {

    private final GetSimilarProductUseCase useCase;
    private final ProductMapper mapper;

    @GetMapping("{productId}/similar")
    public ResponseEntity<List<ProductDto>> getSimilarProduct(@PathVariable String productId) {
        return ResponseEntity.ok(mapper.toDto(useCase.getSimilarProduct(productId)));
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<String> handleProductNotFound(ProductNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
