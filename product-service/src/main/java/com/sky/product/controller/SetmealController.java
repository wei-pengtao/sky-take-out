package com.sky.product.controller;

import com.sky.product.domain.dto.PageResultDTO;
import com.sky.product.domain.dto.SetmealDTO;
import com.sky.product.domain.dto.SetmealPageQueryDTO;
import com.sky.product.domain.entity.Setmeal;
import com.sky.product.domain.result.Result;
import com.sky.product.domain.vo.SetmealVO;
import com.sky.product.service.SetmealService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "套餐管理接口")
@RestController
@RequestMapping("/admin/setmeal")
@RequiredArgsConstructor
public class SetmealController {

    private final SetmealService setmealService;

    @PostMapping
    @Operation(summary = "添加套餐")
    public Result<String> add(@RequestBody SetmealDTO setmealDTO) {
        setmealService.save(setmealDTO);
        return Result.success();
    }

    @DeleteMapping
    @Operation(summary = "删除套餐")
    public Result<String> delete(@RequestParam @Schema(description = "套餐id集合", example = "1,2") List<Long> ids) {
        setmealService.removeByIds(ids);
        return Result.success();
    }

    @PutMapping
    @Operation(summary = "更新套餐")
    public Result<String> update(@RequestBody SetmealDTO setmealDTO) {
        setmealService.updateById(setmealDTO);
        return Result.success();
    }

    @PostMapping("/status/{status}")
    @Operation(summary = "修改套餐状态")
    public Result<String> updateStatus(
            @RequestParam @Schema(description = "菜品id", example = "1") Long id,
            @PathVariable @Schema(description = "状态", allowableValues = {"0", "1"}, example = "1") Integer status) {
        setmealService.updateStatus(id, status);
        return Result.success();
    }

    @GetMapping("/{id}")
    @Operation(summary = "获取套餐详情")
    public Result<SetmealVO> getById(@PathVariable Long id) {
        SetmealVO setmealVO = setmealService.getById(id);
        return Result.success(setmealVO);
    }

    @GetMapping("/page")
    @Operation(summary = "分页查询套餐")
    public Result<PageResultDTO<Setmeal>> page(SetmealPageQueryDTO setmealPageQueryDTO) {
        PageResultDTO<Setmeal> pageResult = setmealService.page(setmealPageQueryDTO);
        return Result.success(pageResult);
    }
}
