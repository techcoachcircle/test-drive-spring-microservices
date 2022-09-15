package com.example.tddspringbootrest.service;

import com.example.tddspringbootrest.domain.Employee;
import com.example.tddspringbootrest.domain.EmployeeRepository;
import org.apache.commons.collections.IteratorUtils;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmployeeService {
    private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository=employeeRepository;
    }

    public List<Employee> getEmployees() {
        return IteratorUtils.toList(employeeRepository.findAll().iterator());    }
}
