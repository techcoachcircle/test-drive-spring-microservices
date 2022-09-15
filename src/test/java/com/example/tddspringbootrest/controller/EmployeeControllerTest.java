package com.example.tddspringbootrest.controller;

import com.example.tddspringbootrest.domain.Employee;
import com.example.tddspringbootrest.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@AutoConfigureMockMvc
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private EmployeeService employeeService;

    @Test
    public void getEmployees_ReturnsEmployees() throws Exception {
        //arrange
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1,"John Doe",25));
        employees.add(new Employee(2,"Jane Davis",30));

        when(this.employeeService.getEmployees()).thenReturn(employees);

        //act & assert
        this.mockMvc.perform(get("/employees"))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        "[{\"id\":1,\"name\":\"John Doe\",\"age\":25},{\"id\":2,\"name\":\"Jane Davis\",\"age\":30}]"
                ));
    }

}
