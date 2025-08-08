package com.sky.product.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SetmealDish {
    private Long id; // 套餐菜品ID

    private Long setmealId; // 套餐ID

    private Long dishId; // 菜品ID

    private String name; // 菜品名称

    private Double price; // 菜品价格

    private Integer copies; // 菜品数量
}
