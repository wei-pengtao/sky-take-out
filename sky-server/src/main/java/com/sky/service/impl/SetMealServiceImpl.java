package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.constant.MessageConstant;
import com.sky.constant.StatusConstant;
import com.sky.dto.SetMealDTO;
import com.sky.dto.SetMealPageQueryDTO;
import com.sky.entity.SetMeal;
import com.sky.exception.DeletionNotAllowedException;
import com.sky.mapper.SetMealDishMapper;
import com.sky.mapper.SetMealMapper;
import com.sky.result.PageResult;
import com.sky.service.SetMealService;
import com.sky.vo.SetMealVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public PageResult pageQuery(SetMealPageQueryDTO setMealPageQueryDTO) {
        int page = setMealPageQueryDTO.getPage();
        int pageSize = setMealPageQueryDTO.getPageSize();

        PageHelper.startPage(page, pageSize);
        Page<SetMealVO> result = setMealMapper.pageQuery(setMealPageQueryDTO);

        return new PageResult(result.getTotal(), result.getResult());
    }

    @Override
    public void deleteBatch(List<Long> ids) {
        ids.forEach(id -> {
            SetMeal setMeal = setMealMapper.getById(id);
            if (setMeal.getStatus().equals(StatusConstant.ENABLE)) {
                throw new DeletionNotAllowedException(MessageConstant.SETMEAL_ON_SALE);
            }
        });

        setMealDishMapper.deleteBySetMealIds(ids);
        setMealMapper.deleteByIds(ids);
    }
}
