package com.sky.mapper;

import com.sky.entity.SetMealDish;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SetMealDishMapper {
    List<Long> getDishIdsByDishIds(List<Long> dishIds);

    void insertBatch(List<SetMealDish> setMealDishes);
}
