package com.sky.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sky.product.domain.entity.Category;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CategoryMapper extends BaseMapper<Category> {
}
