package com.v1.medi_report.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.v1.medi_report.dto.HospitalResponse;
import com.v1.medi_report.service.HospitalService;



@RestController
@RequestMapping("/api/hospitals")
public class HospitalController {
	
    @Autowired
    private  HospitalService  hospitalService;
    

    // GETTING ALL THE HOSPITALS AS A LIST
    @GetMapping
    public ResponseEntity<List<HospitalResponse>> getAllHospitals() {
        List<HospitalResponse> hospitals = hospitalService.getAllHospitals();
        return ResponseEntity.ok(hospitals);
    }

    //GETTING A PARTICULAR HOSPITAL BASED ON THE HOSPITAL ID
    @GetMapping("/{id}")
    public ResponseEntity<HospitalResponse> getHospitalById(@PathVariable Long id) {
      
            HospitalResponse response = hospitalService.getHospitalById(id);
            return ResponseEntity.ok(response);
        
    }

}
