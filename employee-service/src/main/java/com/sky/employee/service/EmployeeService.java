package com.sky.employee.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sky.employee.domain.dto.EmployeeDTO;
import com.sky.employee.domain.dto.EmployeeLoginDTO;
import com.sky.employee.domain.dto.EmployeePageQueryDTO;
import com.sky.employee.domain.dto.PageResultDTO;
import com.sky.employee.domain.entity.Employee;
import com.sky.employee.domain.vo.EmployeeLoginVO;
import com.sky.employee.domain.vo.EmployeeVO;

public interface EmployeeService extends IService<Employee> {
    void save(EmployeeDTO employeeDTO);

    void updateById(EmployeeDTO employeeDTO);

    PageResultDTO<EmployeeVO> page(EmployeePageQueryDTO employeePageQueryDTO);

    EmployeeLoginVO login(EmployeeLoginDTO employeeLoginDTO);
}
