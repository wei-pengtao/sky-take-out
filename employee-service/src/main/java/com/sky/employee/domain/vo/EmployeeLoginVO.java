package com.sky.employee.domain.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmployeeLoginVO {
    private Long id;

    private String name;

    private String username;

    private String token;
}
