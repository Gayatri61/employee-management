package com.ebf.coding.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ebf.coding.dto.CompanyDTO;
import com.ebf.coding.service.CompanyService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/v1/company")
@CrossOrigin(origins = "http://localhost:3000")
public class CompanyController {
	
	@Autowired
	private CompanyService companyService;
	
	@Operation(summary = "Insert a new company.")
	@ApiResponses(value = { @ApiResponse(responseCode = "201") })
	@PostMapping()
	public ResponseEntity<CompanyDTO> insert(@Valid @RequestBody final CompanyDTO  dto) {
		
		CompanyDTO companyDTO=companyService.insert(dto);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(companyDTO.getId()).toUri();
		return ResponseEntity.created(location).body(companyDTO);
		
	}
	
	@Operation(summary = "Get company details.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200") })
	@GetMapping("/{companyId}")
	public ResponseEntity<CompanyDTO> getCompanyDetails(@Parameter(description = "", required = true) @PathVariable long companyId) throws NotFoundException {
		CompanyDTO companyDTO = companyService.getCompanyDetails(companyId);
		return ResponseEntity.ok(companyDTO);
	}

	
	@Operation(summary = "Delete all company.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200") })
	@DeleteMapping()
	public ResponseEntity<Void> deleteAll() {
		companyService.deleteAll();
		return ResponseEntity.ok().build();
	}
}
