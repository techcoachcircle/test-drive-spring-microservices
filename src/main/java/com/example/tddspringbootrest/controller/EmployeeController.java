package com.example.tddspringbootrest.controller;

import com.example.tddspringbootrest.domain.Employee;
import com.example.tddspringbootrest.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeController {
    @Autowired
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    @ResponseBody
    public List<Employee> getEmployees() {
        return this.employeeService.getEmployees();
    }
}
