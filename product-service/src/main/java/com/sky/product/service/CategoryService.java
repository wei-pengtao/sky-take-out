package com.sky.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sky.product.domain.dto.CategoryDTO;
import com.sky.product.domain.dto.CategoryPageQueryDTO;
import com.sky.product.domain.dto.PageResultDTO;
import com.sky.product.domain.entity.Category;

import java.util.List;

public interface CategoryService extends IService<Category> {
    void save(CategoryDTO categoryDTO);

    void updateById(CategoryDTO categoryDTO);

    void updateStatus(Long id, Integer status);

    PageResultDTO<Category> page(CategoryPageQueryDTO categoryPageQueryDTO);

    List<Category> listByType(Integer type);
}
