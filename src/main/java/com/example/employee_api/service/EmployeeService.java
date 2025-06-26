package com.example.employee_api.service;

import com.example.employee_api.dto.CreateEmployeeRequest;
import com.example.employee_api.dto.EmployeeResponse;
import com.example.employee_api.jpa.EmployeeSpecification;
import com.example.employee_api.mapper.EmployeeMapper;
import com.example.employee_api.model.Employee;
import com.example.employee_api.repository.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    @Autowired
    private EmployeeMapper mapper;

    public List<Employee> getAll() {
        return repository.findAll();
    }

//    public Employee save(Employee emp) {
//        return repository.save(emp);
//    }

//    public EmployeeResponse save(CreateEmployeeRequest emp) {
//        Employee entity = mapper.toEntity(emp);
//         Employee saved= repository.save(entity);
//         return mapper.toDto(saved);
//    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Employee getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    public EmployeeResponse create(CreateEmployeeRequest request) {
        Employee entity = mapper.toEntity(request);
        Employee saved = repository.save(entity);
        return mapper.toDto(saved);
    }

//    public List<EmployeeResponse> findAll() {
//        return repository.findAll()
//                .stream()
//                .map(mapper::toDto)
//                .collect(Collectors.toList());
//    }

    public Page<EmployeeResponse> findAll(Pageable pageable) {
        return repository.findAll(pageable)
                .map(mapper::toDto);  // works directly on Page!
    }


    public Page<EmployeeResponse> search(Long id, String name, String department, Pageable pageable) {
        Specification<Employee> spec = EmployeeSpecification.filterBy(id, name, department);
        return repository.findAll(spec, pageable)
                .map(mapper::toDto);
    }

    public EmployeeResponse update(Long id, CreateEmployeeRequest request) {
        Employee employee = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found"));

        mapper.updateFromRequest(request, employee);

        repository.save(employee);

        return mapper.toDto(employee);
    }
}
