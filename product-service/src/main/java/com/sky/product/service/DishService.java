package com.sky.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sky.product.domain.dto.DishDTO;
import com.sky.product.domain.entity.Dish;

public interface DishService extends IService<Dish> {
    void save(DishDTO dishDTO);
}
