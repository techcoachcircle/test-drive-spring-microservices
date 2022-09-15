package com.example.tddspringbootrest.repository;

import com.example.tddspringbootrest.domain.Employee;
import com.example.tddspringbootrest.domain.EmployeeRepository;
import org.apache.commons.collections.IteratorUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;

@DataJpaTest
public class EmployeeRepositoryTest {
    @Autowired
    private EmployeeRepository repository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void shouldAutoGenerateId() {
        Employee persistedEmployee = testEntityManager.persistFlushFind(new Employee());
        assertThat(persistedEmployee.getId()).isNotEqualTo(0);
    }

    @Test
    @Sql(scripts = "/load_sample_employees.sql")
    @Sql(scripts = "/delete_employees.sql",executionPhase = AFTER_TEST_METHOD)
    public void shouldReturnAllEmployeesWhenFindAll(){
        //act
        Iterable<Employee> returnedEmployees = repository.findAll();

        //assert
        List<Employee> returnedEmployeeList = IteratorUtils.toList(returnedEmployees.iterator());
        Employee firstReturnedEmployee = returnedEmployeeList.get(0);
        assertThat(firstReturnedEmployee.getId()).isEqualTo(1);
        assertThat(firstReturnedEmployee.getName()).isEqualTo("John Doe");
        assertThat(firstReturnedEmployee.getAge()).isEqualTo(25);

        Employee secondReturnedEmployee = returnedEmployeeList.get(1);
        assertThat(secondReturnedEmployee.getId()).isEqualTo(2);
        assertThat(secondReturnedEmployee.getName()).isEqualTo("Jane Davis");
        assertThat(secondReturnedEmployee.getAge()).isEqualTo(30);

        Employee thirdReturnedEmployee = returnedEmployeeList.get(2);
        assertThat(thirdReturnedEmployee.getId()).isEqualTo(3);
        assertThat(thirdReturnedEmployee.getName()).isEqualTo("Ram Kumar");
        assertThat(thirdReturnedEmployee.getAge()).isEqualTo(19);
    }

}

