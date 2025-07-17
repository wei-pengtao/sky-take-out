package com.sky.controller.admin;

import com.sky.dto.SetMealDTO;
import com.sky.result.Result;
import com.sky.service.SetMealService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/setmeal")
@Slf4j
@RequiredArgsConstructor
public class SetMealController {

    private final SetMealService setMealService;

    @PostMapping
    public Result<String> save(@RequestBody SetMealDTO setMealDTO) {
        setMealService.saveWithDish(setMealDTO);
        return Result.success();
    }
}
