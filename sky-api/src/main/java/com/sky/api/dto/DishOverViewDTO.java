package com.sky.api.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DishOverViewDTO {
    private Long discontinued;  // 停售菜品数量

    private Long sold; // 起售菜品数量
}
