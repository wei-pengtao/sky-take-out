package com.sky.api.client;

import com.sky.api.dto.DishOverViewDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("product-service")
public interface DishClient {
    @GetMapping("/admin/dish/overviewDishes")
    DishOverViewDTO overviewDishes();
}
