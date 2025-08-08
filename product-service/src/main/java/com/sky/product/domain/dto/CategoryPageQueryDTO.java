package com.sky.product.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "分类分页查询数据传输对象")
public class CategoryPageQueryDTO {
    @Schema(description = "分类名称", example = "热菜")
    private String name;

    @Schema(description = "分类类型", allowableValues = {"1", "2"}, example = "1")
    private Integer type;

    @Schema(description = "页码", example = "1")
    private Integer page;

    @Schema(description = "每页条数", example = "10")
    private Integer pageSize;
}
