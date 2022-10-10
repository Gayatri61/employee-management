package com.ebf.coding.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import com.ebf.coding.dto.EmployeeDTO;
import com.ebf.coding.exception.EmployeeNotFound;

@SpringBootTest
@Sql(scripts = {"classpath:test.sql"})
public class EmployeeServiceTest {
	
	@Autowired EmployeeService employeeService;
	
	@Autowired CompanyService companyService;
	
    @AfterEach
    private void tearDown() {
    	
    	employeeService.deleteAll();
    	companyService.deleteAll();
    }

    @Test
    public void testGetAllPersons() {
        List<EmployeeDTO> employees = employeeService.getAllEmployees();
        assertThat(employees, hasSize(3));
    }
    
    @Test
    public void testGetEmployeeById() throws EmployeeNotFound  {
        EmployeeDTO employees = employeeService.getEmployeeById(1);
        assertNotNull(employees);
    }

}
