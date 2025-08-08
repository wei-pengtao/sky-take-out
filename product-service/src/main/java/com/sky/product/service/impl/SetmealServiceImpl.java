package com.sky.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sky.product.domain.dto.PageResultDTO;
import com.sky.product.domain.dto.SetmealDTO;
import com.sky.product.domain.dto.SetmealPageQueryDTO;
import com.sky.product.domain.entity.Category;
import com.sky.product.domain.entity.Setmeal;
import com.sky.product.domain.entity.SetmealDish;
import com.sky.product.domain.vo.SetmealVO;
import com.sky.product.mapper.CategoryMapper;
import com.sky.product.mapper.SetmealDishMapper;
import com.sky.product.mapper.SetmealMapper;
import com.sky.product.service.SetmealService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SetmealServiceImpl extends ServiceImpl<SetmealMapper, Setmeal> implements SetmealService {

    private final SetmealMapper setmealMapper;
    private final SetmealDishMapper setmealDishMapper;
    private final CategoryMapper categoryMapper;

    @Override
    public void save(SetmealDTO setmealDTO) {
        Setmeal setmeal = new Setmeal();
        BeanUtils.copyProperties(setmealDTO, setmeal);
        setmealMapper.insert(setmeal);

        List<SetmealDish> setmealDishes = setmealDTO.getSetmealDishes();
        if (setmealDishes == null || setmealDishes.isEmpty()) {
            return; // 如果没有菜品信息，直接返回
        }

        setmealDishes.forEach(setmealDish -> setmealDish.setSetmealId(setmeal.getId()));
        setmealDishMapper.insert(setmealDishes);
    }

    @Override
    public void updateById(SetmealDTO setmealDTO) {
        Setmeal setmeal = new Setmeal();
        BeanUtils.copyProperties(setmealDTO, setmeal);
        setmealMapper.updateById(setmeal);

        // 删除原有的套餐菜品信息
        LambdaQueryWrapper<SetmealDish> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(SetmealDish::getSetmealId, setmeal.getId());
        setmealDishMapper.delete(lambdaQueryWrapper);

        List<SetmealDish> setmealDishes = setmealDTO.getSetmealDishes();
        if (setmealDishes == null || setmealDishes.isEmpty()) {
            return; // 如果没有菜品信息，直接返回
        }

        // 设置新的套餐菜品信息
        setmealDishes.forEach(setmealDish -> setmealDish.setSetmealId(setmeal.getId()));
        setmealDishMapper.insert(setmealDishes);
    }

    @Override
    public void updateStatus(Long id, Integer status) {
        Setmeal setmeal = Setmeal.builder().id(id).status(status).build();
        setmealMapper.updateById(setmeal);
    }

    @Override
    public SetmealVO getById(Long id) {
        Setmeal setmeal = setmealMapper.selectById(id);
        if (setmeal == null) {
            return null; // 如果套餐不存在，返回null
        }

        SetmealVO setmealVO = new SetmealVO();
        BeanUtils.copyProperties(setmeal, setmealVO);

        Category category = categoryMapper.selectById(setmeal.getCategoryId());
        if (category == null) {
            setmealVO.setCategoryName("未知分类");
        } else {
            setmealVO.setCategoryName(category.getName());
        }


        LambdaQueryWrapper<SetmealDish> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(SetmealDish::getSetmealId, id);
        List<SetmealDish> setmealDishes = setmealDishMapper.selectList(lambdaQueryWrapper);
        setmealVO.setSetmealDishes(setmealDishes);

        return setmealVO;
    }

    @Override
    public PageResultDTO<Setmeal> page(SetmealPageQueryDTO setmealPageQueryDTO) {
        String name = setmealPageQueryDTO.getName();
        Long categoryId = setmealPageQueryDTO.getCategoryId();
        Integer status = setmealPageQueryDTO.getStatus();

        Page<Setmeal> page = new Page<>(setmealPageQueryDTO.getPage(), setmealPageQueryDTO.getPageSize());
        lambdaQuery()
                .like(name != null && !name.isEmpty(), Setmeal::getName, name)
                .eq(categoryId != null, Setmeal::getCategoryId, categoryId)
                .eq(status != null, Setmeal::getStatus, status)
                .page(page);

        return new PageResultDTO<>(page.getTotal(), page.getRecords());
    }
}
