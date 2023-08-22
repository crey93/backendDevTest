package com.anycompany.similarproducts.adapter.rest.in;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/cache/")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ClearCacheApi {


    @PostMapping("clear")
    @CacheEvict(value = "detail-product-cache", allEntries = true)
    public ResponseEntity<Object> clearCache() {
        return ResponseEntity.ok().build();
    }
}
