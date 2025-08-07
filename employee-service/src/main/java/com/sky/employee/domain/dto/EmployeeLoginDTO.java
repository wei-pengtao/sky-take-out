package com.sky.employee.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "员工登录数据传输对象")
public class EmployeeLoginDTO {
    @Schema(description = "用户名", example = "zhangsan")
    private String username;

    @Schema(description = "密码", example = "123456")
    private String password;
}
