package com.sky.employee.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "员工数据传输对象")
public class EmployeeDTO {
    @Schema(description = "员工ID", example = "2")
    private Long id;

    @Schema(description = "员工姓名", example = "张三")
    private String name;

    @Schema(description = "用户名", example = "zhangsan")
    private String username;

    @Schema(description = "电话", example = "13800138000")
    private String phone;

    @Schema(description = "性别", allowableValues = {"1", "0"}, example = "1")
    private String sex;

    @Schema(description = "身份证号", pattern = "\\d{17}[0-9Xx]", example = "11010119900307888X")
    private String idNumber;
}
