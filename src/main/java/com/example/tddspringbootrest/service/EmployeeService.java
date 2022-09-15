package com.example.tddspringbootrest.service;

import com.example.tddspringbootrest.domain.Employee;
import com.example.tddspringbootrest.domain.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    public EmployeeService(EmployeeRepository employeeRepository) {

    }

    public List<Employee> getEmployees() {
        return null;
    }
}
