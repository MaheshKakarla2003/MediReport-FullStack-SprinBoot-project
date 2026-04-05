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
import org.springframework.web.bind.annotation.RestController;

import com.v1.medi_report.dto.VisitCreateRequest;
import com.v1.medi_report.dto.VisitSummaryResponse;
import com.v1.medi_report.service.VisitService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/visits")
public class VisitController {

	@Autowired
	private VisitService visitService;

	// CREATING A VISIT THAT CONTAINS BOTH PATIENT AND HOSPITAL IDS
	@PostMapping("/hospitalRole")
	public VisitSummaryResponse createVisit(@Valid @ModelAttribute VisitCreateRequest request) {
		return visitService.createVisit(request);
	}

	// GETTING ALL VISITS FOR A PARTICULAR PATIENT IN A PARTICULAR HOSPITAL
	@GetMapping("/patient/{patientId}/hospital/{hospitalId}")
	public List<VisitSummaryResponse> getVisitsForPatientAndHospital(@PathVariable Long patientId,
			@PathVariable Long hospitalId) {
		return visitService.getVisitsForPatientAndHospital(patientId, hospitalId);
	}

	// GETTING THE VISIT + COUNT OF DOCUMENTS FOR THAT PARTCULAR VISIT ID
	@GetMapping("/{visitId}")
	public VisitSummaryResponse getVisitById(@PathVariable Long visitId) {
		return visitService.getVisitById(visitId);
	}

	// DELETING A VISIT BY ID
	@DeleteMapping("/hospitalRole/{id}")
	public ResponseEntity<String> delelteVisit(@PathVariable long id) {
		String res = visitService.deleteVisit(id);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(res);
	}

}
