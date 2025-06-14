package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.annotation.AutoFill;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.enumeration.OperationType;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface EmployeeMapper {

    @Select("select * from employee where username = #{username}")
    Employee getByUsername(String username);

    @AutoFill(operationType = OperationType.INSERT)
    @Insert("insert into employee (username, name, password, phone, sex, id_number, status, create_time, update_time," +
            " create_user, update_user) values (#{username}, #{name}, #{password}, #{phone}, #{sex}, #{idNumber}, " +
            "#{status}, #{createTime}, #{updateTime}, #{createUser}, #{updateUser})")
    void insert(Employee employee);

    Page<Employee> query(EmployeePageQueryDTO employeePageQueryDTO);

    @AutoFill(operationType = OperationType.UPDATE)
    void update(Employee employee);

    @Select("select * from employee where id = #{id}")
    Employee getById(Long id);
}
