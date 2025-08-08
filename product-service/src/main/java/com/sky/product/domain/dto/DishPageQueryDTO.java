package com.sky.product.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "菜品分页查询数据传输对象")
public class DishPageQueryDTO {
    @Schema(description = "菜品名称", example = "宫保鸡丁")
    private String name;

    @Schema(description = "菜品分类ID", example = "2")
    private Long categoryId;

    @Schema(description = "菜品状态", allowableValues = {"1", "0"}, example = "1")
    private Integer status;

    @Schema(description = "当前页码", example = "1")
    private Long page;

    @Schema(description = "每页显示数量", example = "10")
    private Long pageSize;
}
