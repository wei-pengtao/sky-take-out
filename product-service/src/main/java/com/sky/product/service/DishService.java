package com.sky.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sky.api.dto.DishOverViewDTO;
import com.sky.product.domain.dto.DishDTO;
import com.sky.product.domain.dto.DishPageQueryDTO;
import com.sky.product.domain.dto.PageResultDTO;
import com.sky.product.domain.entity.Dish;
import com.sky.product.domain.vo.DishVO;

import java.util.List;

public interface DishService extends IService<Dish> {
    void save(DishDTO dishDTO);

    void removeByIds(List<Long> ids);

    void updateById(DishDTO dishDTO);

    DishVO getById(Long id);

    List<Dish> listByCategoryId(Long categoryId);

    PageResultDTO<DishVO> page(DishPageQueryDTO dishPageQueryDTO);

    void updateStatus(Long id, Integer status);

    DishOverViewDTO overviewDishes();
}
