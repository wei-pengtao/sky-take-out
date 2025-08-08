package com.sky.product.domain.dto;

import com.sky.product.domain.entity.DishFlavor;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(description = "菜品数据传输对象")
public class DishDTO {
    @Schema(description = "菜品ID", example = "1")
    private Long id;

    @Schema(description = "菜品名称", example = "宫保鸡丁")
    private String name;

    @Schema(description = "菜品分类ID", example = "2")
    private Long categoryId;

    @Schema(description = "菜品价格", example = "29.99")
    private Double price;

    @Schema(description = "菜品图片URL", example = "http://example.com/images/dish.jpg")
    private String image;

    @Schema(description = "菜品描述", example = "经典川菜，口感鲜香微辣")
    private String description;

    @Schema(description = "菜品状态", allowableValues = {"1", "0"}, example = "1")
    private Integer status;

    @Schema(description = "菜品口味列表")
    private List<DishFlavor> flavors;
}
