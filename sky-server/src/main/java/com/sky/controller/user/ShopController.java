package com.sky.controller.user;

import com.sky.constant.StatusConstant;
import com.sky.result.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController("userShopController")
@RequiredArgsConstructor
@RequestMapping("/user/shop")
public class ShopController {

    private final RedisTemplate<String, Object> redisTemplate;

    @GetMapping("/status")
    public Result<Integer> getStatus() {
        Integer status = (Integer) redisTemplate.opsForValue().get(StatusConstant.SHOP_STATUS_KEY);
        return Result.success(Objects.requireNonNullElse(status, 0));
    }
}
