package com.ebf.coding.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.ebf.coding.domain.Company;
import com.ebf.coding.dto.CompanyDTO;
import com.ebf.coding.repository.CompanyRepository;

@Service
public class CompanyService {
	
	@Autowired CompanyRepository companyRepository;
	
	@Autowired EmployeeService employeeService;

	/**
	 * @param companyId
	 * @return company details with avg salary
	 * @throws NotFoundException
	 */
	public CompanyDTO getCompanyDetails(long companyId) throws NotFoundException {
		Company company=companyRepository.findById(companyId).orElseThrow(()->new NotFoundException());
		Double avgSalary=employeeService.getAverageSalaryForCompanyId(company.getId());
		CompanyDTO companyDTO=CompanyDTO.fromCompany(company);
		companyDTO.setAvgSalary(avgSalary);
		return companyDTO;

	}

	/**
	 * @param CompanyDTO
	 * @return
	 */
	public CompanyDTO insert(@Valid CompanyDTO dto) {
		Company company=companyRepository.save(CompanyDTO.toCompany(dto));
		return CompanyDTO.fromCompany(company);
	}

	/**
	 * delete all companies
	 */
	public void deleteAll() {
		companyRepository.deleteAll();
	}
	
	
}
