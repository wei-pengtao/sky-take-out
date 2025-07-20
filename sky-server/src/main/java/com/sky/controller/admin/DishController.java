package com.sky.controller.admin;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admin/dish")
@RequiredArgsConstructor
public class DishController {

    private final DishService dishService;
    private final RedisTemplate<String, Object> redisTemplate;

    @PostMapping
    public Result<String> save(@RequestBody DishDTO dishDTO) {
        dishService.saveWithFlavor(dishDTO);
        clearCache("dish_" + dishDTO.getCategoryId());
        return Result.success();
    }

    @GetMapping("/page")
    public Result<PageResult> page(DishPageQueryDTO dishPageQueryDTO) {
        PageResult pageResult = dishService.pageQuery(dishPageQueryDTO);
        return Result.success(pageResult);
    }

    @DeleteMapping
    public Result<String> delete(@RequestParam List<Long> ids) {
        dishService.deleteBatch(ids);
        clearCache("dish_*");
        return Result.success();
    }

    @PostMapping("/status/{status}")
    public Result<String> startOrStop(@PathVariable Integer status, Long id) {
        dishService.startOrStop(status, id);
        clearCache("dish_*");
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<DishVO> getById(@PathVariable Long id) {
        DishVO dishVO = dishService.getById(id);
        return Result.success(dishVO);
    }

    @PutMapping
    public Result<String> update(@RequestBody DishDTO dishDTO) {
        dishService.updateWithFlavor(dishDTO);
        clearCache("dish_*");
        return Result.success();
    }

    @GetMapping("/list")
    public Result<List<Dish>> list(Long categoryId) {
        List<Dish> list = dishService.list(categoryId);
        return Result.success(list);
    }

    private void clearCache(String pattern) {
        redisTemplate.keys(pattern).forEach(redisTemplate::delete);
    }
}
