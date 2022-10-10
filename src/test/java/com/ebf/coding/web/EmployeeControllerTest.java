package com.ebf.coding.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.ebf.coding.dto.EmployeeDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = {"classpath:test.sql"})
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;
    
    private static final String COMPANY_URL= "/api/v1/company";
    
    private static final String EMPLOYEE_URL="/api/v1/employee";
    
    @AfterEach
    private void tearDown() throws Exception {
    	mockMvc.perform(delete(EMPLOYEE_URL))
        .andExpect(status().isOk());
    	
    	mockMvc.perform(delete(COMPANY_URL))
        .andExpect(status().isOk());
    }

    @Test
    public void testGetAllEmployee_basic() throws Exception  {

    	mockMvc.perform(get(EMPLOYEE_URL))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(3));
    }
    
    @Test
    public void testGetEmployee_basic() throws Exception {
    	mockMvc.perform(get(EMPLOYEE_URL+ "/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.firstName").value("Gayatri"));

    }
    
    @Test
    public void testCreateEmployee_basic() throws Exception {
    	EmployeeDTO employeeDTO=new EmployeeDTO();
    	employeeDTO.setId(1);
    	employeeDTO.setFirstName("Ben");
    	employeeDTO.setCompanyId(1);
    	ObjectMapper mapper = new ObjectMapper();

    	mockMvc.perform(post(EMPLOYEE_URL)
    	.contentType(MediaType.APPLICATION_JSON)
    	.content(mapper.writeValueAsString(employeeDTO))
    	.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isCreated())
		.andExpect(jsonPath("$.id").value(1))
		.andExpect(jsonPath("$.firstName").value("Ben"));

    }
    
    @Test
    public void testUpdateEmployee_basic() throws Exception {
    	EmployeeDTO employeeDTO=new EmployeeDTO();
    	employeeDTO.setId(1);
    	employeeDTO.setFirstName("Mick");
    	employeeDTO.setCompanyId(1);
    	ObjectMapper mapper = new ObjectMapper();

    	mockMvc.perform(put(EMPLOYEE_URL +"/1")
    	.contentType(MediaType.APPLICATION_JSON)
    	.content(mapper.writeValueAsString(employeeDTO))
    	.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.id").value(1))
		.andExpect(jsonPath("$.firstName").value("Mick"));

    }

}
