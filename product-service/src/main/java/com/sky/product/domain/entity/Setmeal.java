package com.sky.product.domain.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Setmeal {
    private Long id; // 套餐ID

    private String name; // 套餐名称

    private Long categoryId; // 分类ID

    private Double price; // 套餐价格

    private String image; // 套餐图片URL

    private String description; // 套餐描述

    private Integer status; // 套餐状态（1：启用，0：禁用）

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime; // 创建时间

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime; // 更新时间

    @TableField(fill = FieldFill.INSERT)
    private Long createUser; // 创建用户ID

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateUser; // 更新用户ID
}
