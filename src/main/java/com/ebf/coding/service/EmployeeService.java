package com.ebf.coding.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ebf.coding.domain.Employee;
import com.ebf.coding.dto.EmployeeDTO;
import com.ebf.coding.exception.CompanyNotFoundException;
import com.ebf.coding.exception.EmployeeNotFound;
import com.ebf.coding.repository.CompanyRepository;
import com.ebf.coding.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired CompanyRepository companyRepository;
	
	/**
	 * @return all employees
	 */
	public List<EmployeeDTO> getAllEmployees() {
		List<Employee> employees = employeeRepository.findAll();
		return employees.parallelStream().map(e -> EmployeeDTO.fromEmployee(e)).collect(Collectors.toList());
	}

	/**
	 * @param EmployeeDTO
	 * @return 
	 * @throws CompanyNotFoundException
	 */
	public EmployeeDTO insert(@Valid EmployeeDTO dto) throws CompanyNotFoundException {
		Employee employee = EmployeeDTO.toEmployee(dto);
		
		employee.setCompanyId(companyRepository.findById(dto.getCompanyId())
				.orElseThrow(()-> new CompanyNotFoundException("Company Not found For id "+dto.getCompanyId())));
		
		Employee insertedEmployee = employeeRepository.save(employee);
		return EmployeeDTO.fromEmployee(insertedEmployee);
	}
	

	/**
	 * @param employeeId
	 * @return EmployeeDTO
	 * @throws EmployeeNotFound
	 */
	public EmployeeDTO getEmployeeById(long employeeId) throws EmployeeNotFound {
		return EmployeeDTO.fromEmployee(employeeRepository.findById(employeeId)
				.orElseThrow(()->new EmployeeNotFound("Employee Not found for id: "+employeeId)));
	}
	

	/**
	 * @param employeeId
	 * @param dto
	 * @return
	 * @throws EmployeeNotFound
	 * @throws CompanyNotFoundException
	 */
	public EmployeeDTO update(long employeeId, @Valid EmployeeDTO dto) throws EmployeeNotFound, CompanyNotFoundException {
		
		Employee employee = EmployeeDTO.toEmployee(dto);
		employee.setId(employeeRepository.findById(employeeId)
				.orElseThrow(()->new EmployeeNotFound("Employee Not found for id: "+employeeId)).getId());
		
		employee.setCompanyId(companyRepository.findById(dto.getCompanyId())
				.orElseThrow(()-> new CompanyNotFoundException("Company Not found For id "+dto.getCompanyId())));
		
		Employee updatedEmployee = employeeRepository.save(employee);
		return EmployeeDTO.fromEmployee(updatedEmployee);
	}

	
	/**
	 * @param employeeId
	 */
	public void deleteEmployees(Long employeeId) {
		employeeRepository.deleteById(employeeId);
	}

	/**
	 * @param companyId
	 * @return avg salary
	 */
	public Double getAverageSalaryForCompanyId(long companyId) {
		return employeeRepository.getAverageSalaryForCompanyId(companyId);
	}

	/**
	 * delete all employees
	 */
	public void deleteAll() {
		employeeRepository.deleteAll();
	}
}
