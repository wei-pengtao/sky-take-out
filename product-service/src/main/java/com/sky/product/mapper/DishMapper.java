package com.sky.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sky.product.domain.entity.Dish;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DishMapper extends BaseMapper<Dish> {
}
