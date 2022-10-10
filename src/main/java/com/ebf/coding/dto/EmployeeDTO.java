package com.ebf.coding.dto;

import java.io.Serializable;

import com.ebf.coding.domain.Employee;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class EmployeeDTO implements Serializable{

    private long id;

    private String firstName;

    private String lastName;

    private String emailId;

    private String address;

    private Double salary;

    private long companyId;   
    
    private String companyName;

    
	public static Employee toEmployee(final EmployeeDTO dto) {
        final Employee e = new Employee();
        e.setId(dto.getId());
        e.setFirstName(dto.getFirstName());
        e.setLastName(dto.getLastName());
        e.setEmail(dto.getEmailId());
        e.setAddress(dto.getAddress());
        e.setSalary(dto.getSalary());
        return e;
      }

      public static EmployeeDTO fromEmployee(final Employee e) {
        final EmployeeDTO dto = new EmployeeDTO();
        dto.setId(e.getId());
        dto.setFirstName(e.getFirstName());
        dto.setLastName(e.getLastName());
        dto.setEmailId(e.getEmail());
        dto.setAddress(e.getAddress());
        dto.setSalary(e.getSalary());
        dto.setCompanyId(e.getCompanyId().getId());
        dto.setCompanyName(e.getCompanyId().getName());
        return dto;
      }

    
	@Override
	public String toString() {
		return "EmployeeDTO [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + emailId
				+ ", address=" + address + ", salary=" + salary + ", companyId=" + companyId + "]";
	}


}
