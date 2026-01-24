package com.v1.medi_report.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.v1.medi_report.entity.Hospital;

public interface HospitalRepository extends JpaRepository<Hospital, Long> {

	boolean existsByName(String name);

	boolean existsByEmail(String email);

	boolean existsByContactNumber(String contactNumber);
	
	 List<Hospital> findByIdIn(List<Long> ids);

}
