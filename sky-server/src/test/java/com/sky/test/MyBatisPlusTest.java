package com.sky.test;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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

    @Test
    void testPageQuery() {
        Page<Employee> employeePage = new Page<>(1, 10);
        employeeMapper.selectPage(employeePage, null);
        System.out.println("Total Records: " + employeePage.getTotal());
        System.out.println("Total Pages: " + employeePage.getPages());
        System.out.println("Records on Current Page: " + employeePage.getRecords());
        for (Employee emp : employeePage.getRecords()) {
            System.out.println(emp);
        }
    }
}
