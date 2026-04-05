package com.v1.medi_report.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.v1.medi_report.dto.HospitalResponse;
import com.v1.medi_report.dto.Mapper;
import com.v1.medi_report.entity.Hospital;
import com.v1.medi_report.exception.NotFoundException;
import com.v1.medi_report.repository.HospitalRepository;

@Service
public class HospitalServiceImpl implements HospitalService {

	@Autowired
	private HospitalRepository hospitalRepository;

	// GETTING ALL THE HOSPITALS
	@Override
	public List<HospitalResponse> getAllHospitals() {
		return hospitalRepository.findAll().stream().map(Mapper::toHospitalResponse).toList();
	}

	// GETTING A PARTICULAR HOSPITAL BASED ON THE HOSPITAL ID
	@Override
	public HospitalResponse getHospitalById(Long id) {
		Hospital hospital = hospitalRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Hospital not found with id: " + id));

		return Mapper.toHospitalResponse(hospital);
	}

}