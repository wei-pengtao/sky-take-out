package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.annotation.AutoFill;
import com.sky.dto.SetMealPageQueryDTO;
import com.sky.entity.SetMeal;
import com.sky.enumeration.OperationType;
import com.sky.vo.SetMealVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SetMealMapper {

    @Select("select count(id) from setmeal where category_id = #{categoryId}")
    Integer countByCategoryId(Long id);

    @AutoFill(operationType = OperationType.INSERT)
    void insert(SetMeal setMeal);

    Page<SetMealVO> pageQuery(SetMealPageQueryDTO setMealPageQueryDTO);

    @Select("select * from setmeal where id = #{id}")
    SetMeal getById(Long id);

    void deleteByIds(List<Long> ids);
}
