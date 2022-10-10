package com.ebf.coding.dto;

import java.io.Serializable;

import com.ebf.coding.domain.Company;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CompanyDTO implements Serializable{

    private long id;
    
    private String name;
    
    private Double avgSalary;
    
    public static Company toCompany(final CompanyDTO dto) {
        final Company c = new Company();
        c.setId(dto.getId());
        c.setName(dto.getName());
        return c;
      }

      public static CompanyDTO fromCompany(final Company c) {
        final CompanyDTO dto = new CompanyDTO();
        dto.setId(c.getId());
        dto.setName(c.getName());
        return dto;
      }

}
