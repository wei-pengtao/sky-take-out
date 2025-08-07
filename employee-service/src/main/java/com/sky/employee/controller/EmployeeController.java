package com.sky.employee.controller;

import com.sky.employee.domain.dto.*;
import com.sky.employee.domain.result.Result;
import com.sky.employee.domain.vo.EmployeeLoginVO;
import com.sky.employee.domain.vo.EmployeeVO;
import com.sky.employee.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "员工管理接口")
@RestController
@RequestMapping("/admin/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @Operation(summary = "添加员工")
    @PostMapping
    public Result<String> add(@RequestBody EmployeeDTO employeeDTO) {
        employeeService.save(employeeDTO);
        return Result.success();
    }

    @Operation(summary = "更新员工")
    @PutMapping
    public Result<String> update(@RequestBody EmployeeDTO employeeDTO) {
        employeeService.updateById(employeeDTO);
        return Result.success();
    }

    @Operation(summary = "员工分页查询")
    @GetMapping("/page")
    public Result<PageResultDTO<EmployeeVO>> page(EmployeePageQueryDTO employeePageQueryDTO) {
        PageResultDTO<EmployeeVO> pageResult = employeeService.page(employeePageQueryDTO);
        return Result.success(pageResult);
    }

    @Operation(summary = "员工登录")
    @PostMapping("/login")
    public Result<EmployeeLoginVO> login(@RequestBody EmployeeLoginDTO employeeLoginDTO) {
        EmployeeLoginVO employeeLoginVO = employeeService.login(employeeLoginDTO);
        return Result.success(employeeLoginVO);
    }

    @Operation(summary = "修改员工密码")
    @PutMapping("/editPassword")
    public Result<String> editPassword(@RequestBody EmployeeEditPasswordDTO employeeEditPasswordDTO) {
        employeeService.editPassword(employeeEditPasswordDTO);
        return Result.success();
    }

    @Operation(summary = "修改员工状态")
    @PutMapping("/status/{status}")
    public Result<String> updateStatus(
            @RequestParam @Schema(description = "员工id", example = "1") Long id,
            @PathVariable @Schema(description = "状态", example = "1") Integer status) {
        employeeService.updateStatus(id, status);
        return Result.success();
    }

    @Operation(summary = "获取员工信息")
    @GetMapping("/{id}")
    public Result<EmployeeVO> getEmployeeById(@PathVariable @Schema(description = "员工ID", example = "1") Long id) {
        EmployeeVO employeeVO = employeeService.getById(id);
        return Result.success(employeeVO);
    }

    @Operation(summary = "退出登录")
    @PostMapping("/logout")
    public Result<String> logout() {
        // 退出登录逻辑可以根据实际需求实现
        // 例如清除用户的登录状态或令牌等
        return Result.success();
    }
}
