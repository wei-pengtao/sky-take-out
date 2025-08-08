package com.sky.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sky.product.domain.dto.PageResultDTO;
import com.sky.product.domain.dto.SetmealDTO;
import com.sky.product.domain.dto.SetmealPageQueryDTO;
import com.sky.product.domain.entity.Setmeal;
import com.sky.product.domain.vo.SetmealVO;

public interface SetmealService extends IService<Setmeal> {
    void save(SetmealDTO setmealDTO);

    void updateById(SetmealDTO setmealDTO);

    void updateStatus(Long id, Integer status);

    SetmealVO getById(Long id);

    PageResultDTO<Setmeal> page(SetmealPageQueryDTO setmealPageQueryDTO);
}
