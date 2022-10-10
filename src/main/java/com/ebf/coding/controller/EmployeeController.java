package com.ebf.coding.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ebf.coding.dto.EmployeeDTO;
import com.ebf.coding.exception.CompanyNotFoundException;
import com.ebf.coding.exception.EmployeeNotFound;
import com.ebf.coding.service.EmployeeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/v1/employee")
@CrossOrigin(origins = "http://localhost:3000")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@Operation(summary = "Get all Employee.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200") })
	@GetMapping()
	public ResponseEntity<List<EmployeeDTO>> get() {
		List<EmployeeDTO> employeeDTOs=employeeService.getAllEmployees();
		return ResponseEntity.ok(employeeDTOs);
	}

	@Operation(summary = "Insert a new employee.")
	@ApiResponses(value = { @ApiResponse(responseCode = "201") })
	@PostMapping()
	public ResponseEntity<EmployeeDTO> insert(@Valid @RequestBody final EmployeeDTO dto) throws  CompanyNotFoundException {
		
		EmployeeDTO employeeDTO=employeeService.insert(dto);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(employeeDTO.getId()).toUri();
		return ResponseEntity.created(location).body(employeeDTO);
		
	}
	
	@Operation(summary = "Get employee for given country id.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200") })
	@GetMapping("/{employeeId}")
	public ResponseEntity<EmployeeDTO> get(@Parameter(description = "", required = true) @PathVariable long employeeId) throws EmployeeNotFound  {
		
		EmployeeDTO employes=employeeService.getEmployeeById(employeeId);
		return ResponseEntity.ok(employes);
	}

	@Operation(summary = "Update an existing employee.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200") })
	@PutMapping("/{employeeId}")
	public ResponseEntity<EmployeeDTO> update(
			@Parameter(description = "", required = true) @PathVariable long employeeId,
			@Parameter(description = "", required = true) @Valid @RequestBody final EmployeeDTO dto) throws EmployeeNotFound, CompanyNotFoundException  {
		EmployeeDTO employeeDTO=employeeService.update(employeeId,dto);
		return ResponseEntity.ok(employeeDTO);
	}

	@Operation(summary = "Delete employees.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200") })
	@DeleteMapping("/{employeeId}")
	public ResponseEntity<Void> delete(@Parameter(description = "", required = true) @PathVariable Long employeeId) {
		employeeService.deleteEmployees(employeeId);
		return ResponseEntity.ok().build();
	}
	
	@Operation(summary = "Delete employees.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200") })
	@DeleteMapping()
	public ResponseEntity<Void> deleteAll() {
		employeeService.deleteAll();
		return ResponseEntity.ok().build();
	}
	

}
