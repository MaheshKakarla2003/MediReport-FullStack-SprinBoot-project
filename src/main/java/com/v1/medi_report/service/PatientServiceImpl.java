package com.v1.medi_report.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.v1.medi_report.dto.HospitalResponse;
import com.v1.medi_report.dto.Mapper;
import com.v1.medi_report.dto.PatientCreateRequest;
import com.v1.medi_report.dto.PatientResponse;
import com.v1.medi_report.entity.Patient;
import com.v1.medi_report.entity.Visit;
import com.v1.medi_report.repository.HospitalRepository;
import com.v1.medi_report.repository.PatientRepository;
import com.v1.medi_report.repository.VisitRepository;

import jakarta.transaction.Transactional;

@Service
public class PatientServiceImpl  implements  PatientService{

	
    @Autowired
    private  PatientRepository patientRepository;

    @Autowired
    private  HospitalRepository  hospitalRepository;
   
    @Autowired
    private  VisitRepository  visitRepository;
  

    @Override
    public PatientResponse createPatient(PatientCreateRequest request) {

    	boolean exists = patientRepository.existsByAadhaarAndHospitalId(
                request.getAadhaar(),
                request.getHospitalId()
        );
    	if(exists)
    		throw  new IllegalArgumentException(  "Patient with Aadhaar already exists in this hospital");
    	
        Patient saved = patientRepository.save(Mapper.toPatientEntity(request));
        return Mapper.toPatientResponse(saved);
    }

    @Override
    public PatientResponse getPatientById(Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Patient not found with id: " + id));

        return Mapper.toPatientResponse(patient);
    }

    @Override
    public List<PatientResponse> getPatientsByPhone(String phoneNumber) {
        return patientRepository.findUniquePatientsByPhoneNumber(phoneNumber)
                .stream()
                .map(Mapper::toPatientResponse)
                .toList();
    }

	@Override
	public List<PatientResponse> getPatientsByHospital(long id) {

		   List<Patient> patients=  patientRepository.findByHospitalId(id);
	        return patients.stream()
	                .map(Mapper::toPatientResponse)
	                .toList();
	}

	@Override
	public List<PatientResponse> getPatientsByHospitalAndMobile(long id , String mobile) {
		
		 List<Patient> patients=  patientRepository.findByHospitalIdAndPhoneNumber(id , mobile);
	        return patients.stream()
	                .map(Mapper::toPatientResponse)
	                .toList();
	}

	@Override
	@Transactional
	public String deletePatient(long patientId) {
		
		List<Visit> visits = visitRepository.findByPatientId(patientId);

	    // 2. Delete visits (documents auto deleted)
	    visitRepository.deleteAll(visits);

	    Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new IllegalArgumentException("Patient not found with id: " + patientId));
	    // 3. Delete patient
	    patientRepository.delete(patient);
	    return "Patient deleted successfully";
	}

	@Override
	public List<HospitalResponse> getHospitalsByMobileAndAadhaar(long aadhaar, String mobile) {
		
	    List<Long> hospitalIdList=patientRepository.findHospitalIdsByPhoneAndAadhaar( aadhaar,  mobile) ;
	    

	    return hospitalRepository.findByIdIn(hospitalIdList).stream()
	            .map(Mapper::toHospitalResponse)
	            .toList();
	}

	@Override
	public List<PatientResponse>  getPatientByAadhaarAndHospitalId(Long aadhaar, Long id) {
		return patientRepository
	            .findByAadhaarAndHospitalId(aadhaar, id)
	            .map(patient -> List.of(Mapper.toPatientResponse(patient)))
	            .orElse(List.of());
	}

    
	
}
