package com.ebf.coding.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = {"classpath:test.sql"})
public class CompanyControllerTest {

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
    public void testGetCompany_basic() throws Exception {
    	mockMvc.perform(get(COMPANY_URL+ "/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.avgSalary").value(5009090.0))
                .andExpect(jsonPath("$.name").value("EBF"));

    }
    
}
