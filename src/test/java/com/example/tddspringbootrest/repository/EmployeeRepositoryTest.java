package com.example.tddspringbootrest.repository;

import com.example.tddspringbootrest.domain.Employee;
import com.example.tddspringbootrest.domain.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

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
}

