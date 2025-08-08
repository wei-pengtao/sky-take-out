package com.sky.product.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "分类数据传输对象")
public class CategoryDTO {
    @Schema(description = "分类ID", example = "1")
    private Long id;

    @Schema(description = "分类名称", example = "热菜")
    private String name;

    @Schema(description = "分类类型", allowableValues = {"1", "2"}, example = "1")
    private Integer type;

    @Schema(description = "分类排序", example = "1")
    private Integer sort;

    @Schema(description = "分类状态", allowableValues = {"1", "0"}, example = "1")
    private Integer status;
}
