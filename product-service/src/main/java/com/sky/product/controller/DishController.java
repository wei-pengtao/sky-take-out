package com.sky.product.controller;

import com.sky.product.domain.dto.DishDTO;
import com.sky.product.domain.result.Result;
import com.sky.product.service.DishService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name =  "菜品管理接口")
@RestController
@RequestMapping("/admin/dish")
@RequiredArgsConstructor
public class DishController {

    private final DishService dishService;

    @PostMapping
    @Operation(summary = "添加菜品")
    public Result<String> add(@RequestBody DishDTO dishDTO) {
        dishService.save(dishDTO);
        return Result.success();
    }

    @DeleteMapping
    @Operation(summary = "删除菜品")
    public Result<String> delete(@RequestParam @Schema(description = "菜品id集合", example = "1,2") List<Long> ids) {
        dishService.removeByIds(ids);
        return Result.success();
    }
}
