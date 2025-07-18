package com.sky.service;

import com.sky.dto.SetMealDTO;
import com.sky.dto.SetMealPageQueryDTO;
import com.sky.result.PageResult;

public interface SetMealService {
    void saveWithDish(SetMealDTO setMealDTO);

    PageResult pageQuery(SetMealPageQueryDTO setMealPageQueryDTO);
}
