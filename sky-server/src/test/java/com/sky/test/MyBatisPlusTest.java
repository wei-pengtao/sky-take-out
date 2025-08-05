package com.sky.test;

import com.sky.entity.Employee;
import com.sky.mapper.EmployeeMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MyBatisPlusTest {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Test
    void testSelect() {
        // Example of a simple select operation
        Employee employee = employeeMapper.selectById(1L);
        System.out.println(employee);
    }

    @Test
    void testUpdate() {
        Employee employee = Employee.builder()
                .id(1L)
                .name("管理员")
                .build();
        employeeMapper.updateById(employee);
    }
}
