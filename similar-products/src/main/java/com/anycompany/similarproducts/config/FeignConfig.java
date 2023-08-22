package com.anycompany.similarproducts.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "com.anycompany.similarproducts.adapter.rest")
public class FeignConfig {
}
