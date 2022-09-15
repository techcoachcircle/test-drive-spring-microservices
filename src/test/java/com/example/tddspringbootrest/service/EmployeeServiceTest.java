package com.example.tddspringbootrest.service;

import com.example.tddspringbootrest.domain.Employee;
import com.example.tddspringbootrest.domain.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {
    @Mock
    private EmployeeRepository employeeRepository;

    @Test
    public void getCars_returnsCars() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1,"John Doe",25));
        employees.add(new Employee(2,"Jane Davis",30));
        given(employeeRepository.findAll()).willReturn(employees);

        EmployeeService employeeService = new EmployeeService(employeeRepository);
        List<Employee> returnedEmployees = employeeService.getEmployees();

        assertThat(returnedEmployees.get(0).getId()).isEqualTo(1L);
        assertThat(returnedEmployees.get(0).getName()).isEqualTo("John Doe");
        assertThat(returnedEmployees.get(0).getAge()).isEqualTo(25);

        assertThat(returnedEmployees.get(1).getId()).isEqualTo(2L);
        assertThat(returnedEmployees.get(1).getName()).isEqualTo("Jane Davis");
        assertThat(returnedEmployees.get(1).getAge()).isEqualTo(30);
    }

}
