package com.sky.shop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sky.shop.domain.dto.*;
import com.sky.shop.domain.entity.Employee;
import com.sky.shop.domain.vo.EmployeeLoginVO;
import com.sky.shop.domain.vo.EmployeeVO;

public interface EmployeeService extends IService<Employee> {
    void save(EmployeeDTO employeeDTO);

    void updateById(EmployeeDTO employeeDTO);

    PageResultDTO<EmployeeVO> page(EmployeePageQueryDTO employeePageQueryDTO);

    EmployeeLoginVO login(EmployeeLoginDTO employeeLoginDTO);

    void editPassword(EmployeeEditPasswordDTO employeeEditPasswordDTO);

    void updateStatus(Long id, Integer status);

    EmployeeVO getById(Long id);
}
