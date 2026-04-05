package com.v1.medi_report.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.v1.medi_report.dto.CustomerResponse;
import com.v1.medi_report.service.CustomerService;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

	@Autowired
	private CustomerService customerServ;

	// GETTING A PARTICULAR CUSTOMER BASED ON THE CUSTOMER ID
	@GetMapping("/customerRole/{id}")
	public ResponseEntity<CustomerResponse> getCustomerById(@PathVariable Long id) {

		CustomerResponse response = customerServ.getCustomerById(id);
		return ResponseEntity.ok(response);

	}

}
