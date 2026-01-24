package com.v1.medi_report.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.v1.medi_report.dto.HospitalResponse;
import com.v1.medi_report.dto.PatientCreateRequest;
import com.v1.medi_report.dto.PatientResponse;
import com.v1.medi_report.service.PatientService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

	 @Autowired
	 private  PatientService patientService;
	 
    // CREATING A PATIENT BY THE HOSPITAL MANAGEMENT
	  @PostMapping
	    public ResponseEntity<PatientResponse> createPatient(@Valid  @ModelAttribute PatientCreateRequest request) {
	 
	            PatientResponse response = patientService.createPatient(request);
	            return ResponseEntity.status(HttpStatus.CREATED).body(response);
	            	
	    }

	  // GETTING A PARTICULAR PATIENT BY PATIENT ID
	    @GetMapping("/{id}")
	    public ResponseEntity<PatientResponse> getPatientById(@PathVariable Long id) {
	
	            PatientResponse response = patientService.getPatientById(id);
	            return ResponseEntity.ok(response);
	     
	    }  

	    // GETTING A LIST OF PATIENTS BASED ON THE MOBILE NUMBER
	    @GetMapping("/by-phone")
	    public ResponseEntity<List<PatientResponse>> getPatientsByPhone(@RequestParam String phone) {
	        List<PatientResponse> patients = patientService.getPatientsByPhone(phone);
	        return ResponseEntity.ok(patients);
	    }
	    
	    // GETTING A LIST OF HOSPITALS BASED ON A PARTICULAR MOBILE AND AADHAAR
	    @GetMapping("/hospitalList/{aadhaar}/{mobile}")
	    public ResponseEntity<List<HospitalResponse>> getHospitalsByMobileAndAadhaar(@PathVariable long aadhaar, @PathVariable String mobile) {
	        List<HospitalResponse> patients = patientService.getHospitalsByMobileAndAadhaar(aadhaar , mobile);
	        return ResponseEntity.ok(patients);
	    }
	      
	    // GETTING A LIST OF PATIENTS BASED ON A PARTICULAR HOSPITAL
	    @GetMapping("/by-hospitalId/{id}")
	    public ResponseEntity<List<PatientResponse>> getPatientsByHospital(@PathVariable long id) {
	        List<PatientResponse> patients = patientService.getPatientsByHospital(id);
	        return ResponseEntity.ok(patients);
	    }
	        
	 // GETTING A LIST OF PATIENTS BASED ON A PARTICULAR HOSPITAL AND MOBILE
	    @GetMapping("/by-hospitalId&mobile/{id}/{mobile}")
	    public ResponseEntity<List<PatientResponse>> getPatientsByHospitalAndMobile(@PathVariable long id , @PathVariable String mobile) {
	        List<PatientResponse> patients = patientService.getPatientsByHospitalAndMobile(id , mobile);
	        return ResponseEntity.ok(patients);
	    }
	    
	    // GETTING A PARTICULAR PATIENT BY GIVEN AADHAAR IN A PARTICULAR HOSPITAL
	    @GetMapping("/{aadhaar}/{id}")
	    public ResponseEntity<List<PatientResponse> > getPatientById( @PathVariable Long aadhaar, @PathVariable Long id) {
	
	    	List<PatientResponse>  response = patientService.getPatientByAadhaarAndHospitalId(aadhaar,id);
	            return ResponseEntity.ok(response);
	     
	    }
	    
	    
		 // DELETING A PATIENT
		    @DeleteMapping("/{patientId}")
		    public ResponseEntity<String>  deletePatient(@PathVariable long patientId ) {
		    	 String res= patientService.deletePatient( patientId);
				  
	  	          return  ResponseEntity.status(HttpStatus.ACCEPTED).body(res);   
		    }
		    
	    
	    
	    
}
