package com.sky.employee.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sky.employee.domain.dto.EmployeeDTO;
import com.sky.employee.domain.dto.EmployeePageQueryDTO;
import com.sky.employee.domain.dto.PageResultDTO;
import com.sky.employee.domain.entity.Employee;
import com.sky.employee.domain.vo.EmployeeVO;
import com.sky.employee.mapper.EmployeeMapper;
import com.sky.employee.service.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {
    @Override
    public void save(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDTO,employee);
        employee.setStatus(Employee.STATUS_ENABLED);
        employee.setPassword(Employee.DEFAULT_PASSWORD);
        super.save(employee);
    }

    @Override
    public void updateById(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDTO, employee);
        super.updateById(employee);
    }

    @Override
    public PageResultDTO<EmployeeVO> page(EmployeePageQueryDTO employeePageQueryDTO) {
        Page<Employee> page = new Page<>(employeePageQueryDTO.getPage(), employeePageQueryDTO.getPageSize());
        lambdaQuery()
                .like(employeePageQueryDTO.getName() != null && !employeePageQueryDTO.getName().isEmpty(), Employee::getName, employeePageQueryDTO.getName())
                .page(page);
        List<EmployeeVO> employeeVOList = page.getRecords().stream()
                .map(employee -> {
                    EmployeeVO employeeVO = new EmployeeVO();
                    BeanUtils.copyProperties(employee, employeeVO);
                    return employeeVO;
                })
                .toList();
        return new PageResultDTO<>(page.getTotal(), employeeVOList);
    }
}
