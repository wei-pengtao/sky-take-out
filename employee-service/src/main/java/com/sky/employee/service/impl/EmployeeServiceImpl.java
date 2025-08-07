package com.sky.employee.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jwt.util.JwtUtil;
import com.sky.employee.domain.dto.*;
import com.sky.employee.domain.entity.Employee;
import com.sky.employee.domain.vo.EmployeeLoginVO;
import com.sky.employee.domain.vo.EmployeeVO;
import com.sky.employee.mapper.EmployeeMapper;
import com.sky.employee.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {

    private final BCryptPasswordEncoder passwordEncoder;
    private final EmployeeMapper employeeMapper;
    private final JwtUtil jwtUtil;

    @Override
    public void save(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDTO, employee);
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

    @Override
    public EmployeeLoginVO login(EmployeeLoginDTO employeeLoginDTO) {
        String username = employeeLoginDTO.getUsername();
        String password = employeeLoginDTO.getPassword();

        Employee employee = lambdaQuery()
                .eq(Employee::getUsername, username)
                .one();

        if (employee == null) {
            throw new RuntimeException("用户名不存在");
        }

        if (!passwordEncoder.matches(password, employee.getPassword())) {
            throw new RuntimeException("密码错误");
        }

        if (employee.getStatus().equals(Employee.STATUS_DISABLED)) {
            throw new RuntimeException("账号已禁用");
        }

        Map<String, Object> claims = new HashMap<>();
        claims.put("id", employee.getId());
        String jwt = jwtUtil.createJWT(claims);

        return EmployeeLoginVO.builder()
                .id(employee.getId())
                .name(employee.getName())
                .username(employee.getUsername())
                .token(jwt)
                .build();
    }

    @Override
    public void editPassword(EmployeeEditPasswordDTO employeeEditPasswordDTO) {
        Employee employee = employeeMapper.selectById(employeeEditPasswordDTO.getEmpId());

        if (employee == null) {
            throw new RuntimeException("员工不存在");
        }

        if (!passwordEncoder.matches(employeeEditPasswordDTO.getOldPassword(), employee.getPassword())) {
            throw new RuntimeException("旧密码错误");
        }

        employee = Employee.builder()
                .id(employee.getId())
                .password(passwordEncoder.encode(employeeEditPasswordDTO.getNewPassword()))
                .build();
        updateById(employee);
    }

    @Override
    public void updateStatus(Long id, Integer status) {
        Employee employee = employeeMapper.selectById(id);

        if (employee == null) {
            throw new RuntimeException("员工不存在");
        }

        if (!status.equals(Employee.STATUS_ENABLED) && !status.equals(Employee.STATUS_DISABLED)) {
            throw new RuntimeException("无效的状态");
        }

        employee = Employee.builder()
                .id(employee.getId())
                .status(status)
                .build();
        updateById(employee);
    }

    @Override
    public EmployeeVO getById(Long id) {
        Employee employee = employeeMapper.selectById(id);
        if (employee == null) {
            throw new RuntimeException("员工不存在");
        }
        EmployeeVO employeeVO = new EmployeeVO();
        BeanUtils.copyProperties(employee, employeeVO);
        return employeeVO;
    }
}
