package com.v1.medi_report.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.v1.medi_report.dto.HospitalResponse;
import com.v1.medi_report.dto.PatientCreateRequest;
import com.v1.medi_report.dto.PatientResponse;

public interface PatientService {

	PatientResponse createPatient(PatientCreateRequest request);

	PatientResponse getPatientById(Long id);

	List<PatientResponse> getPatientsByPhone(String phoneNumber);

	Page<PatientResponse> getPatientsByHospital(long id, int pageNo, int pageSize);

	List<PatientResponse> getPatientsByHospitalAndMobile(long id, String mobile);

	String deletePatient(long patientId);

	List<HospitalResponse> getHospitalsByMobileAndAadhaar(long aadhaar, String mobile);

	List<PatientResponse> getPatientByAadhaarAndHospitalId(Long aadhaar, Long id);
}
