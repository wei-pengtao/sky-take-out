package com.sky.product.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DishFlavor {
    private Long id;

    private Long dishId;

    private String name;

    private String value;
}
