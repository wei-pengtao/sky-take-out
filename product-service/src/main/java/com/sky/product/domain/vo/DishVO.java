package com.sky.product.domain.vo;

import com.sky.product.domain.entity.DishFlavor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class DishVO {
    private Long id;

    private String name;

    private Long categoryId;

    private String categoryName;

    private Double price;

    private String image;

    private String description;

    private Integer status;

    private LocalDateTime updateTime;

    private List<DishFlavor> flavors;
}
