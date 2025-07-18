package com.sky.service;

import com.sky.dto.SetMealDTO;
import com.sky.dto.SetMealPageQueryDTO;
import com.sky.result.PageResult;

import java.util.List;

public interface SetMealService {
    void saveWithDish(SetMealDTO setMealDTO);

    PageResult pageQuery(SetMealPageQueryDTO setMealPageQueryDTO);

    void deleteBatch(List<Long> ids);
}
