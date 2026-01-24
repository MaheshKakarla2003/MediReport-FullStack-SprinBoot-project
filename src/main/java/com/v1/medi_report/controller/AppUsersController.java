package com.v1.medi_report.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.v1.medi_report.dto.AppUserResponse;
import com.v1.medi_report.dto.CustomerRegisterRequest;
import com.v1.medi_report.dto.CustomerResponse;
import com.v1.medi_report.dto.CustomerUpdateRequest;
import com.v1.medi_report.dto.HospitalRegistrationRequest;
import com.v1.medi_report.dto.HospitalResponse;
import com.v1.medi_report.dto.HospitalUpdateRequest;
import com.v1.medi_report.service.AppUsersService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class AppUsersController {

	@Autowired
	 private  AppUsersService appUserService;

	//   CREATING A HOSPITL WITH CREDENTIALS
	 @PostMapping("/register-hospital")
	    public ResponseEntity<HospitalResponse> registerHospital( @Valid  @ModelAttribute   HospitalRegistrationRequest request) {

	    	 HospitalResponse response=appUserService.registerHospital(request);
	    	return ResponseEntity.status(HttpStatus.CREATED).body(response);
	        
	    }
	    
	 //   CREATING A CUSTOMER WITH CREDENTIALS
	    @PostMapping("/register-customer")
	    public ResponseEntity<CustomerResponse> registerCustomer( @Valid  @ModelAttribute   CustomerRegisterRequest request) {

	    	 CustomerResponse response=appUserService.registerCustomer(request);
	    	return ResponseEntity.status(HttpStatus.CREATED).body(response);
	        
	    }
	    
	    // UPDATING A CUSTOMER WITH  DATA
	    
	    @PutMapping("/update-customer/{customerId}/{userId}")
	    	public ResponseEntity<CustomerResponse> updateCustomer(   @PathVariable("customerId") long customerId , @PathVariable("userId") long userId ,
	    			                                                                         @Valid  @RequestBody CustomerUpdateRequest request) {

		    	 CustomerResponse response=appUserService.updateCustomer(customerId , userId,request);
		    	return ResponseEntity.status(HttpStatus.CREATED).body(response);
		        
		    }
	    
	    // UPDATING A HOSPITAL WITH  DATA
	    
	    @PutMapping("/update-hospital/{hospitalId}/{userId}")
    	public ResponseEntity<HospitalResponse> updateHospital(   @PathVariable("hospitalId") long hospitalId , @PathVariable("userId") long userId ,
    			                                                                         @Valid  @RequestBody HospitalUpdateRequest request) {

	    	HospitalResponse response=appUserService.updateHospital(hospitalId , userId,request);
	    	return ResponseEntity.status(HttpStatus.CREATED).body(response);
	        
	    }
	
	    // TRY TO LOG IN WITH CREDENTIALS
	  @GetMapping("/login")
	  public   ResponseEntity<AppUserResponse>  login(@RequestParam String username , @RequestParam  String password ) {
		  AppUserResponse user=  appUserService.login(username, password);
		  
	  	          return ResponseEntity.ok(user);
	  }
	  
	  // DELETE A USER ACCOUNT EITHER A HOSPITAL OR CUSTOMER
	  @DeleteMapping("/{id}")
	  public   ResponseEntity<String>  deleteUserAccount(@PathVariable long id) {
		          String res= appUserService.deleteUserAccount( id);
		  
	  	          return  ResponseEntity.status(HttpStatus.ACCEPTED).body(res);
	  }
	  
	  
	
	   
}
