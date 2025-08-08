package com.sky.shop.service.impl;

import com.sky.api.client.DishClient;
import com.sky.api.dto.DishOverViewDTO;
import com.sky.shop.domain.vo.DishOverViewVO;
import com.sky.shop.service.WorkspaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WorkspaceServiceImpl implements WorkspaceService {

    private final DishClient dishClient;

    @Override
    public DishOverViewVO overviewDishes() {
        DishOverViewDTO dishOverViewDTO = dishClient.overviewDishes();
        return DishOverViewVO.builder()
                .discontinued(dishOverViewDTO.getDiscontinued())
                .sold(dishOverViewDTO.getSold())
                .build();
    }
}
