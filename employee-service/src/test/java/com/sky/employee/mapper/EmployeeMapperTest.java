package com.sky.employee.mapper;

import com.sky.employee.domain.entity.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;


@SpringBootTest
class EmployeeMapperTest {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Test
    void testInsertEmployee() {
        // 1. 创建有效的员工对象（避免空值导致非空约束错误）
        Employee employee = new Employee();
        employee.setName("测试员工");
        employee.setUsername("test_user");
        employee.setPassword("encrypted_password"); // 实际应使用加密后的密码
        employee.setPhone("13800138000");
        employee.setSex("男");
        employee.setIdNumber("110101199001011234"); // 有效身份证号
        employee.setStatus(Employee.STATUS_ENABLED); // 使用静态常量[2](@ref)

        // 2. 设置审计字段（可选但推荐）
        employee.setCreateUser(1L);   // 假设管理员ID=1
        employee.setUpdateUser(1L);
        employee.setCreateTime(LocalDateTime.now());
        employee.setUpdateTime(LocalDateTime.now());

        // 3. 执行插入操作
        int affectedRows = employeeMapper.insert(employee);
        System.out.println("插入影响行数: " + affectedRows);

        // 4. 验证自增ID和状态
        Assertions.assertNotNull(employee.getId(), "插入后应生成自增ID");
        Assertions.assertEquals(Employee.STATUS_ENABLED, employee.getStatus(), "状态应为启用");

        // 5. 查询验证（可选）
        Employee inserted = employeeMapper.selectById(employee.getId());
        Assertions.assertEquals("test_user", inserted.getUsername());
    }
}