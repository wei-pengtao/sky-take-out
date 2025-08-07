package com.sky.employee.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema(description = "员工分页查询数据传输对象")
public class EmployeePageQueryDTO implements Serializable {
    @Schema(description = "页码", example = "1")
    private Long page;

    @Schema(description = "每页大小", example = "10")
    private Long pageSize;

    @Schema(description = "员工姓名", example = "张三")
    private String name;
}
