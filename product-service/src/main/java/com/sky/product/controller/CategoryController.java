package com.sky.product.controller;

import com.sky.product.domain.dto.CategoryDTO;
import com.sky.product.domain.dto.CategoryPageQueryDTO;
import com.sky.product.domain.dto.PageResultDTO;
import com.sky.product.domain.entity.Category;
import com.sky.product.domain.result.Result;
import com.sky.product.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "分类管理接口")
@RestController
@RequestMapping("/admin/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    @Operation(summary = "添加分类")
    public Result<String> add(@RequestBody CategoryDTO categoryDTO) {
        categoryService.save(categoryDTO);
        return Result.success();
    }

    @DeleteMapping
    @Operation(summary = "删除分类")
    public Result<String> delete(@RequestParam Long id) {
        // 其实需要先判断该分类下是否有菜品或者套餐存在，如果有则不能删除
        categoryService.removeById(id);
        return Result.success();
    }

    @PutMapping
    @Operation(summary = "更新分类")
    public Result<String> update(@RequestBody CategoryDTO categoryDTO) {
        categoryService.updateById(categoryDTO);
        return Result.success();
    }

    @PostMapping("/status/{status}")
    @Operation(summary = "更新分类状态")
    public Result<String> updateStatus(
            @RequestParam @Schema(description = "分类id", example = "1") Long id,
            @PathVariable @Schema(description = "状态", allowableValues = {"0", "1"}, example = "1") Integer status) {
        categoryService.updateStatus(id, status);
        return Result.success();
    }

    @GetMapping("/page")
    @Operation(summary = "分页查询分类")
    public Result<PageResultDTO<Category>> page(CategoryPageQueryDTO categoryPageQueryDTO) {
        PageResultDTO<Category> pageResult = categoryService.page(categoryPageQueryDTO);
        return Result.success(pageResult);
    }

    @GetMapping("/list")
    @Operation(summary = "根据类型获取分类列表")
    public Result<List<Category>> listByType(@RequestParam @Schema(description = "分类类型", allowableValues = {"1", "2"}, example = "1") Integer type) {
        List<Category> categories = categoryService.listByType(type);
        return Result.success(categories);
    }
}
