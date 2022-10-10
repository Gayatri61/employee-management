package com.ebf.coding.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ebf.coding.domain.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
}
