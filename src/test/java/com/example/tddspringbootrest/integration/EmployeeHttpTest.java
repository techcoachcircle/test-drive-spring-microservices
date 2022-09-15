package com.example.tddspringbootrest.integration;

import com.example.tddspringbootrest.domain.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@Sql(scripts = "/load_sample_employees.sql")
@Sql(scripts = "/delete_employees.sql",executionPhase = AFTER_TEST_METHOD)
public class EmployeeHttpTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void shouldReturnEmployees() {
        ResponseEntity<Employee[]> responseEntity = this.restTemplate
                .getForEntity("/employees", Employee[].class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

        Employee[] returnedEmployees = responseEntity.getBody();
        assertThat(returnedEmployees.length).isEqualTo(3);

        Employee[] expectedEmployees = {
                new Employee(1L,"John Doe",25),
                new Employee(2L,"Jane Davis",30),
                new Employee(3L,"Ram Kumar",19)
        };

        verifyListsAreEqual(returnedEmployees, expectedEmployees);
    }

    private void verifyListsAreEqual(Employee[] returnedEmployees,
                                     Employee[] expectedEmployees) {
        assertThat(returnedEmployees[0].getId()).
                isEqualTo(expectedEmployees[0].getId());
        assertThat(returnedEmployees[0].getName()).
                isEqualTo(expectedEmployees[0].getName());
        assertThat(returnedEmployees[0].getAge()).
                isEqualTo(expectedEmployees[0].getAge());

        assertThat(returnedEmployees[1].getId()).
                isEqualTo(expectedEmployees[1].getId());
        assertThat(returnedEmployees[1].getName()).
                isEqualTo(expectedEmployees[1].getName());
        assertThat(returnedEmployees[1].getAge()).
                isEqualTo(expectedEmployees[1].getAge());

        assertThat(returnedEmployees[2].getId()).
                isEqualTo(expectedEmployees[2].getId());
        assertThat(returnedEmployees[2].getName()).
                isEqualTo(expectedEmployees[2].getName());
        assertThat(returnedEmployees[2].getAge()).
                isEqualTo(expectedEmployees[2].getAge());
    }
}
