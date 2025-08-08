package com.sky.product.domain.dto;

import com.sky.product.domain.entity.SetmealDish;
import lombok.Data;

import java.util.List;

@Data
public class SetmealDTO {
    private Long id; // 套餐ID

    private String name; // 套餐名称

    private Long categoryId; // 分类ID

    private Double price; // 套餐价格

    private String image; // 套餐图片URL

    private String description; // 套餐描述

    private Integer status; // 套餐状态（1：启用，0：禁用）

    private List<SetmealDish> setmealDishes; // 套餐菜品列表
}
