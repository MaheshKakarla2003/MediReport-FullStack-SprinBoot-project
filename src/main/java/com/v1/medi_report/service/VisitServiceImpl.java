package com.v1.medi_report.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.v1.medi_report.dto.Mapper;
import com.v1.medi_report.dto.VisitCreateRequest;
import com.v1.medi_report.dto.VisitSummaryResponse;
import com.v1.medi_report.entity.Hospital;
import com.v1.medi_report.entity.Patient;
import com.v1.medi_report.entity.Visit;
import com.v1.medi_report.exception.NotFoundException;
import com.v1.medi_report.repository.DocumentRepository;
import com.v1.medi_report.repository.HospitalRepository;
import com.v1.medi_report.repository.PatientRepository;
import com.v1.medi_report.repository.VisitRepository;

@Service
@Transactional
public class VisitServiceImpl implements VisitService {

	@Autowired
	private VisitRepository visitRepository;

	@Autowired
	private PatientRepository patientRepository;

	@Autowired
	private HospitalRepository hospitalRepository;

	@Autowired
	private DocumentRepository documentRepository;

	// CREATING A VISIT THAT CONTAINS BOTH PATIENT AND HOSPITAL IDS
	@Override
	public VisitSummaryResponse createVisit(VisitCreateRequest request) {
		Patient patient = patientRepository.findById(request.getPatientId()).orElseThrow(
				() -> new NotFoundException("No such Patient available with this  Id " + request.getPatientId()));

		Hospital hospital = hospitalRepository.findById(request.getHospitalId()).orElseThrow(
				() -> new NotFoundException("No such Hospital available with this  Id " + request.getHospitalId()));

		Visit saved = visitRepository.save(Mapper.toVisitEntity(request, patient, hospital));

		return Mapper.toVisitSummaryResponse(saved, 0L);
	}

	// GETTING ALL VISITS FOR A PARTICULAR PATIENT IN A PARTICULAR HOSPITAL
	@Override
	public List<VisitSummaryResponse> getVisitsForPatientAndHospital(Long patientId, Long hospitalId) {
		Patient patient = patientRepository.findById(patientId)
				.orElseThrow(() -> new NotFoundException("No such Patient available with this  Id " + patientId));

		Hospital hospital = hospitalRepository.findById(hospitalId)
				.orElseThrow(() -> new NotFoundException("No such Hospital available with this  Id " + hospitalId));

		List<Visit> visits = visitRepository.findByPatientAndHospitalOrderByVisitDateDesc(patient, hospital);

		return visits.stream().map(v -> {
			long reportCount = documentRepository.countByVisit(v);
			return Mapper.toVisitSummaryResponse(v, reportCount);
		}).collect(Collectors.toList());
	}

	// GETTING THE VISIT + COUNT OF DOCUMENTS FOR THAT PARTCULAR VISIT ID
	@Override
	public VisitSummaryResponse getVisitById(Long visitId) {
		Visit visit = visitRepository.findById(visitId)
				.orElseThrow(() -> new NotFoundException("No such Visit Data available with this  Id " + visitId));

		long reportCount = documentRepository.countByVisit(visit);

		return Mapper.toVisitSummaryResponse(visit, reportCount);
	}

	// DELETING A VISIT BY ID
	@Override
	public String deleteVisit(long id) {

		Visit visit = visitRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("No such Visit Data available with this  Id " + id));
		visitRepository.delete(visit);
		return "visit deleted successfully";
	}

}
