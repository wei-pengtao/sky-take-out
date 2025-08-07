package com.sky.employee.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "员工修改密码数据传输对象")
public class EmployeeEditPasswordDTO {
    @Schema(description = "员工ID", example = "2")
    private Long empId;

    @Schema(description = "旧密码", example = "123456")
    private String oldPassword;

    @Schema(description = "新密码", example = "654321")
    private String newPassword;
}
