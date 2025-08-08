package com.sky.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sky.product.domain.dto.DishDTO;
import com.sky.product.domain.dto.DishPageQueryDTO;
import com.sky.product.domain.dto.PageResultDTO;
import com.sky.product.domain.entity.Category;
import com.sky.product.domain.entity.Dish;
import com.sky.product.domain.entity.DishFlavor;
import com.sky.product.domain.vo.DishVO;
import com.sky.product.mapper.CategoryMapper;
import com.sky.product.mapper.DishFlavorMapper;
import com.sky.product.mapper.DishMapper;
import com.sky.product.service.DishService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService {

    private final DishMapper dishMapper;
    private final DishFlavorMapper dishFlavorMapper;
    private final CategoryMapper categoryMapper;

    @Override
    @Transactional
    public void save(DishDTO dishDTO) {
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDTO, dish);
        dishMapper.insert(dish);

        List<DishFlavor> flavors = dishDTO.getFlavors();
        if (flavors == null || flavors.isEmpty()) {
            return; // 如果没有口味信息，则不需要保存
        }

        flavors.forEach(flavor -> flavor.setDishId(dish.getId()));
        dishFlavorMapper.insert(flavors);
    }

    @Override
    @Transactional
    public void removeByIds(List<Long> ids) {
        LambdaQueryWrapper<DishFlavor> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(DishFlavor::getDishId, ids);
        dishFlavorMapper.delete(queryWrapper);
        dishMapper.deleteByIds(ids);
    }

    @Override
    @Transactional
    public void updateById(DishDTO dishDTO) {
        LambdaQueryWrapper<DishFlavor> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(DishFlavor::getDishId, dishDTO.getId());
        dishFlavorMapper.delete(queryWrapper);

        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDTO, dish);
        dishMapper.updateById(dish);

        List<DishFlavor> flavors = dishDTO.getFlavors();
        if (flavors == null || flavors.isEmpty()) {
            return;
        }
        flavors.forEach(flavor -> flavor.setDishId(dish.getId()));
        dishFlavorMapper.insert(flavors);
    }

    @Override
    public DishVO getById(Long id) {
        Dish dish = dishMapper.selectById(id);
        if (dish == null) {
            return null; // 如果没有找到菜品，返回null
        }

        DishVO dishVO = new DishVO();
        BeanUtils.copyProperties(dish, dishVO);
        Category category = categoryMapper.selectById(dish.getCategoryId());
        if (category == null) {
            dishVO.setCategoryName("未知分类"); // 如果分类不存在，设置默认值
        } else {
            dishVO.setCategoryName(category.getName());
        }

        // 获取菜品口味信息
        LambdaQueryWrapper<DishFlavor> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(DishFlavor::getDishId, id);
        List<DishFlavor> flavors = dishFlavorMapper.selectList(queryWrapper);
        dishVO.setFlavors(flavors);

        return dishVO;
    }

    @Override
    public List<Dish> listByCategoryId(Long categoryId) {
        return lambdaQuery().eq(Dish::getCategoryId, categoryId).list();
    }

    @Override
    public PageResultDTO<DishVO> page(DishPageQueryDTO dishPageQueryDTO) {
        String name = dishPageQueryDTO.getName();
        Long categoryId = dishPageQueryDTO.getCategoryId();
        Integer status = dishPageQueryDTO.getStatus();

        Page<Dish> page = new Page<>(dishPageQueryDTO.getPage(), dishPageQueryDTO.getPageSize());

        lambdaQuery()
                .like(name != null, Dish::getName, name)
                .eq(categoryId != null, Dish::getCategoryId, categoryId)
                .eq(status != null, Dish::getStatus, status)
                .page(page);

        List<Dish> dishes = page.getRecords();
        List<DishVO> dishVOs = dishes.stream().map(dish -> {
            DishVO dishVO = new DishVO();
            BeanUtils.copyProperties(dish, dishVO);
            Category category = categoryMapper.selectById(dish.getCategoryId());
            if (category == null) {
                dishVO.setCategoryName("未知分类"); // 如果分类不存在，设置默认值
            } else {
                dishVO.setCategoryName(category.getName());
            }

            // 获取菜品口味信息
            LambdaQueryWrapper<DishFlavor> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(DishFlavor::getDishId, dish.getId());
            List<DishFlavor> flavors = dishFlavorMapper.selectList(queryWrapper);
            dishVO.setFlavors(flavors);

            return dishVO;
        }).toList();

        return new PageResultDTO<>(page.getTotal(), dishVOs);
    }

    @Override
    public void updateStatus(Long id, Integer status) {
        Dish dish = dishMapper.selectById(id);
        if (dish == null) {
            throw new IllegalArgumentException("菜品不存在，无法更新状态");
        }
        dish = Dish.builder().id(id).status(status).build();
        dishMapper.updateById(dish);
    }
}
