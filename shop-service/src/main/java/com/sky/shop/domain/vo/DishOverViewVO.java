package com.sky.shop.domain.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DishOverViewVO {
    private Long discontinued;  // 停售菜品数量

    private Long sold; // 起售菜品数量
}
