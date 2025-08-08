package com.sky.product.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "套餐分页查询DTO")
public class SetmealPageQueryDTO {
    @Schema(description = "套餐名称", example = "家庭套餐")
    private String name; // 套餐名称

    @Schema(description = "分类ID", example = "1")
    private Long categoryId; // 分类ID

    @Schema(description = "套餐状态", example = "1")
    private Integer status; // 套餐状态（1：启用，0：禁用）

    @Schema(description = "当前页码", example = "1")
    private Integer page; // 当前页码

    @Schema(description = "每页显示数量", example = "10")
    private Integer pageSize; // 每页显示数量
}
