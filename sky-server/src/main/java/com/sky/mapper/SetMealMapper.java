package com.sky.mapper;

import com.sky.annotation.AutoFill;
import com.sky.entity.SetMeal;
import com.sky.enumeration.OperationType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SetMealMapper {

    @Select("select count(id) from setmeal where category_id = #{categoryId}")
    Integer countByCategoryId(Long id);

    @AutoFill(operationType = OperationType.INSERT)
    void insert(SetMeal setMeal);
}
