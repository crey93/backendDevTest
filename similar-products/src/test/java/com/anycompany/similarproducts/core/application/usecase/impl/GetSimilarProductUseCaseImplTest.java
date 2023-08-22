package com.anycompany.similarproducts.core.application.usecase.impl;

import com.anycompany.similarproducts.core.application.port.GetProductPort;
import com.anycompany.similarproducts.core.domain.Product;
import com.anycompany.similarproducts.core.exception.ProductNotFoundException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class GetSimilarProductUseCaseImplTest {
    
    @Mock
    private GetProductPort port;
    
    
    private GetSimilarProductUseCaseImpl useCase;
    
    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        useCase = new GetSimilarProductUseCaseImpl(port);
    }
    
    @Test
    void getSimilarProduct_happy_path() {
        String productId = "1";
        when(port.getSimilarProductIds(productId)).thenReturn(List.of("1", "2", "3"));
        
        when(port.getProductDetail("1")).thenReturn(Product.builder().id("1").build());
        when(port.getProductDetail("2")).thenReturn(Product.builder().id("2").build());
        when(port.getProductDetail("3")).thenReturn(Product.builder().id("3").build());
        
        List<Product> response = useCase.getSimilarProduct(productId);

        
        verify(port).getProductDetail("1");
        verify(port).getProductDetail("2");
        verify(port).getProductDetail("3");
        Assertions.assertEquals(3, response.size());
        Assertions.assertEquals("1", response.get(0).getId());
        Assertions.assertEquals("2", response.get(1).getId());
        Assertions.assertEquals("3", response.get(2).getId());
    }

    @Test
    void getSimilarProduct_one_detail_nulled() {
        String productId = "1";
        when(port.getSimilarProductIds(productId)).thenReturn(List.of("1", "2", "3"));

        when(port.getProductDetail("1")).thenReturn(Product.builder().id("1").build());
        when(port.getProductDetail("2")).thenReturn(Product.builder().id("2").build());
        when(port.getProductDetail("3")).thenReturn(null);

        List<Product> response = useCase.getSimilarProduct(productId);
        Assertions.assertEquals(2, response.size());
    }

    @Test
    void getSimilarProduct_ProductNotFound() {
        String productId = "1";
        when(port.getSimilarProductIds(productId)).thenThrow(new ProductNotFoundException());
        
        assertThrows(ProductNotFoundException.class, () -> {
            useCase.getSimilarProduct(productId);
        });
    }

}
