package com.sky.shop.controller;

import com.sky.shop.domain.result.Result;
import com.sky.shop.domain.vo.DishOverViewVO;
import com.sky.shop.service.WorkspaceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "工作空间管理接口")
@RestController
@RequestMapping("/admin/workspace")
@RequiredArgsConstructor
public class WorkspaceController {

    private final WorkspaceService workspaceService;

    @GetMapping("/overviewDishes")
    @Operation(summary = "菜品概览接口")
    public Result<DishOverViewVO> overviewDishes() {
        DishOverViewVO dishOverViewVO = workspaceService.overviewDishes();
        return Result.success(dishOverViewVO);
    }
}
