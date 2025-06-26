package com.example.employee_api.dto;

// com.example.employee_api.dto.CreateEmployeeRequest.java

import jakarta.validation.constraints.*;

public class CreateEmployeeRequest {

    @NotBlank
    private String name;

    @Email
    private String email;

    @Min(18)
    @Max(65)
    private int age;

    @NotBlank
    private String department;

    // Getters and Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}

