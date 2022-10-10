package com.ebf.coding.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ebf.coding.domain.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	List<Employee> findByCompanyId(long companyId);

	@Query("SELECT avg(e.salary) FROM Employee e WHERE e.companyId.id =:companyId ")
	Double getAverageSalaryForCompanyId(long companyId);

}
