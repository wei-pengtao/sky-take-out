package com.sky.service.impl;

import com.sky.dto.SetMealDTO;
import com.sky.entity.SetMeal;
import com.sky.mapper.SetMealDishMapper;
import com.sky.mapper.SetMealMapper;
import com.sky.service.SetMealService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SetMealServiceImpl implements SetMealService {
    
    private final SetMealDishMapper setMealDishMapper;
    private final SetMealMapper setMealMapper;

    @Override
    public void saveWithDish(SetMealDTO setMealDTO) {
        SetMeal setMeal = new SetMeal();
        BeanUtils.copyProperties(setMealDTO, setMeal);

        setMealMapper.insert(setMeal);

        Long setMealId = setMeal.getId();
        setMealDTO.getSetmealDishes().forEach(setMealDish -> {
            setMealDish.setSetMealId(setMealId);
        });
        
        setMealDishMapper.insertBatch(setMealDTO.getSetmealDishes());
    }
}
