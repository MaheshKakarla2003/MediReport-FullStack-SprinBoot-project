package com.v1.medi_report.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppUserResponse {

	 
	
        private Long id;
        
	    private String username; // This will be the email
	    
	    private String password;

	    private String role; //  roles: HOSPITAL or CUSTOMER

	    private long hospitalId; 

	    private long customerId; 
}
