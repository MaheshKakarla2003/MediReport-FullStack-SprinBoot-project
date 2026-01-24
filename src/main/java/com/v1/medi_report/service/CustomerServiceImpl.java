package com.v1.medi_report.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.v1.medi_report.dto.CustomerResponse;
import com.v1.medi_report.dto.Mapper;
import com.v1.medi_report.entity.Customer;
import com.v1.medi_report.repository.CustomerRepository;

@Service
public class CustomerServiceImpl  implements CustomerService{
	
	@Autowired
	private CustomerRepository customerRepo;

	 //GETTING A PARTICULAR CUSTOMER BASED ON THE CUSTOMER ID
	@Override
	public CustomerResponse getCustomerById(Long id) {
		Customer customer= customerRepo.findById(id)
	                .orElseThrow(() -> new IllegalArgumentException("Customer not found with id: " + id));

	        return Mapper.toCustomerResponse(customer);
	}

	
}
