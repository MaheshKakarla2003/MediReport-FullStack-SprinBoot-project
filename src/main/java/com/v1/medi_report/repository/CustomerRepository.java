package com.v1.medi_report.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.v1.medi_report.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{

	boolean existsByContactNumber(String phone);

}
