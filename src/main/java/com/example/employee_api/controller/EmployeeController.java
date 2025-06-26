package com.example.employee_api.controller;

import com.example.employee_api.dto.CreateEmployeeRequest;
import com.example.employee_api.dto.EmployeeResponse;
import com.example.employee_api.model.Employee;
import com.example.employee_api.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.domain.Pageable;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService service;

//    @GetMapping
//    public List<Employee> getAll() {
//        return service.getAll();
//    }

//    @GetMapping
//    public Page<EmployeeResponse> getAll(@PageableDefault(size = 10) Pageable pageable) {
//        return service.findAll(pageable);
//    }

    @GetMapping
    public Page<EmployeeResponse> getEmployees(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String department,
            @PageableDefault(size = 10, sort = "name") Pageable pageable
    ) {
        return service.search(id, name, department, pageable);
    }

    @GetMapping("/{id}")
    public Employee getOne(@PathVariable Long id) {
        return service.getById(id);
    }





//    @PostMapping
//    public Employee create(@Valid @RequestBody Employee emp) {
//        return service.save(emp);
//    }

    @PostMapping
    public ResponseEntity<EmployeeResponse> create(@Valid @RequestBody CreateEmployeeRequest request) {
        EmployeeResponse created = service.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).header("","").body(created);
    }

//    @PutMapping("/{id}")
//    public Employee update(@PathVariable Long id, @Valid @RequestBody Employee emp) {
//        emp.setId(id);
//        return service.save(emp);
//    }

    //may need to optimize
//    @PutMapping("/{id}")
//    public ResponseEntity<EmployeeResponse> update(@PathVariable Long id, @Valid @RequestBody EmployeeResponse emp) {
//        emp.setId(id);
//        return service.save(emp);
//    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponse> updateEmployee(
            @PathVariable Long id,
            @RequestBody @Valid CreateEmployeeRequest request
    ) {
        EmployeeResponse response = service.update(id, request);
        return ResponseEntity.ok(response);
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }




}
