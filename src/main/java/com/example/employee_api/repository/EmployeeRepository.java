package com.example.employee_api.repository;

import com.example.employee_api.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

//public interface EmployeeRepository extends JpaRepository<Employee, Long> {
//}

public interface EmployeeRepository extends JpaRepository<Employee, Long>, JpaSpecificationExecutor<Employee> {
}
