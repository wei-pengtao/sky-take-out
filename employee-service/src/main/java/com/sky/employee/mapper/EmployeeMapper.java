package com.sky.employee.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sky.employee.domain.entity.Employee;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {
}
