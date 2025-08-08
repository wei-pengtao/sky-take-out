package com.sky.product.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sky.product.domain.dto.CategoryDTO;
import com.sky.product.domain.dto.CategoryPageQueryDTO;
import com.sky.product.domain.dto.PageResultDTO;
import com.sky.product.domain.entity.Category;
import com.sky.product.mapper.CategoryMapper;
import com.sky.product.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    private final CategoryMapper categoryMapper;

    @Override
    public void save(CategoryDTO categoryDTO) {
        Category category = new Category();
        BeanUtils.copyProperties(categoryDTO,category);
        category.setStatus(Category.STATUS_DISABLED);
        categoryMapper.insert(category);
    }

    @Override
    public void updateById(CategoryDTO categoryDTO) {
        Category category = new Category();
        BeanUtils.copyProperties(categoryDTO,category);
        categoryMapper.updateById(category);
    }

    @Override
    public void updateStatus(Long id, Integer status) {
        Category category = Category.builder().id(id).status(status).build();
        categoryMapper.updateById(category);
    }

    @Override
    public PageResultDTO<Category> page(CategoryPageQueryDTO categoryPageQueryDTO) {
        String name = categoryPageQueryDTO.getName();
        Integer type = categoryPageQueryDTO.getType();

        Page<Category> page = new Page<>(categoryPageQueryDTO.getPage(), categoryPageQueryDTO.getPageSize());
        lambdaQuery()
                .eq(name != null, Category::getName, name)
                .eq(type != null, Category::getType, type)
                .page(page);

        return new PageResultDTO<>(page.getTotal(), page.getRecords());
    }

    @Override
    public List<Category> listByType(Integer type) {
        return lambdaQuery()
                .eq(Category::getType, type)
                .eq(Category::getStatus, Category.STATUS_ENABLED)
                .list();
    }
}
