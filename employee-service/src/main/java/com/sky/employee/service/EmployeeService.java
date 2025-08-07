package com.sky.employee.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sky.employee.domain.dto.*;
import com.sky.employee.domain.entity.Employee;
import com.sky.employee.domain.vo.EmployeeLoginVO;
import com.sky.employee.domain.vo.EmployeeVO;

public interface EmployeeService extends IService<Employee> {
    void save(EmployeeDTO employeeDTO);

    void updateById(EmployeeDTO employeeDTO);

    PageResultDTO<EmployeeVO> page(EmployeePageQueryDTO employeePageQueryDTO);

    EmployeeLoginVO login(EmployeeLoginDTO employeeLoginDTO);

    void editPassword(EmployeeEditPasswordDTO employeeEditPasswordDTO);

    void updateStatus(Long id, Integer status);

    EmployeeVO getById(Long id);
}
