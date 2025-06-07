package com.sky.annotation;

import com.sky.enumeration.OperationType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * 自动填充注解
 * 用于标记需要自动填充的字段
 * 例如：创建时间、更新时间、创建人、更新人等
 */
@Target(ElementType.METHOD)
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
public @interface AutoFill {

    /**
     * 操作类型
     * 用于区分是新增还是修改
     */
    OperationType operationType();
}
