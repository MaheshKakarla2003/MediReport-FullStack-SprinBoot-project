package com.v1.medi_report.service;

import java.util.List;

import com.v1.medi_report.dto.HospitalResponse;

public interface HospitalService {

//	HospitalResponse createHospital(HospitalCreateRequest request);

    List<HospitalResponse> getAllHospitals();

    HospitalResponse getHospitalById(Long id);

}
