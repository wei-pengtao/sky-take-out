package com.sky.controller.admin;

import com.sky.constant.StatusConstant;
import com.sky.result.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController("adminShopController")
@RequiredArgsConstructor
@RequestMapping("/admin/shop")
public class ShopController {

    private final RedisTemplate<String, Object> redisTemplate;

    @PutMapping("/{status}")
    public Result<String> setStatus(@PathVariable Integer status) {
        redisTemplate.opsForValue().set(StatusConstant.SHOP_STATUS_KEY, status);
        return Result.success();
    }

    @GetMapping("/status")
    public Result<Integer> getStatus() {
        Integer status = (Integer) redisTemplate.opsForValue().get(StatusConstant.SHOP_STATUS_KEY);
        return Result.success(Objects.requireNonNullElse(status, 0));
    }
}
