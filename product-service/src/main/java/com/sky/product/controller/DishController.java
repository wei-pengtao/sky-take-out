package com.sky.product.controller;

import com.sky.product.domain.dto.DishDTO;
import com.sky.product.domain.dto.DishPageQueryDTO;
import com.sky.product.domain.dto.PageResultDTO;
import com.sky.product.domain.entity.Dish;
import com.sky.product.domain.result.Result;
import com.sky.product.domain.vo.DishVO;
import com.sky.product.service.DishService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "菜品管理接口")
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

    @PutMapping
    @Operation(summary = "更新菜品")
    public Result<String> update(@RequestBody DishDTO dishDTO) {
        dishService.updateById(dishDTO);
        return Result.success();
    }

    @GetMapping("/{id}")
    @Operation(summary = "获取菜品详情")
    public Result<DishVO> getById(@PathVariable Long id) {
        DishVO dishVO = dishService.getById(id);
        return Result.success(dishVO);
    }

    @GetMapping("/list")
    @Operation(summary = "根据分类id获取菜品列表")
    public Result<List<Dish>> listByCategoryId(@RequestParam Long categoryId) {
        List<Dish> dishes = dishService.listByCategoryId(categoryId);
        return Result.success(dishes);
    }

    @GetMapping("/page")
    @Operation(summary = "分页查询菜品")
    public Result<PageResultDTO<DishVO>> page(DishPageQueryDTO dishPageQueryDTO) {
        PageResultDTO<DishVO> pageResult = dishService.page(dishPageQueryDTO);
        return Result.success(pageResult);
    }

    @PostMapping("/status/{status}")
    @Operation(summary = "修改菜品状态")
    public Result<String> updateStatus(
            @RequestParam @Schema(description = "菜品id", example = "1") Long id,
            @PathVariable @Schema(description = "状态", allowableValues = {"0", "1"}, example = "1") Integer status) {
        dishService.updateStatus(id, status);
        return Result.success();
    }
}
