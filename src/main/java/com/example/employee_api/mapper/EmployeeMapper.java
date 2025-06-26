package com.example.employee_api.mapper;

// com.example.employee_api.mapper.EmployeeMapper.java


import com.example.employee_api.dto.*;
import com.example.employee_api.model.Employee;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {

    public Employee toEntity(CreateEmployeeRequest request) {
        Employee emp = new Employee();
        emp.setName(request.getName());
        emp.setEmail(request.getEmail());
        emp.setAge(request.getAge());
        emp.setDepartment(request.getDepartment());
        return emp;
    }

    public EmployeeResponse toDto(Employee emp) {
        EmployeeResponse dto = new EmployeeResponse();
        dto.setId(emp.getId());
        dto.setName(emp.getName());
        dto.setEmail(emp.getEmail());
        dto.setAge(emp.getAge());
        dto.setDepartment(emp.getDepartment());
        return dto;
    }

    public void updateFromRequest(CreateEmployeeRequest request, Employee employee) {
        employee.setName(request.getName());
        employee.setDepartment(request.getDepartment());
        employee.setAge(request.getAge());
        employee.setEmail(request.getEmail());
    }
}

