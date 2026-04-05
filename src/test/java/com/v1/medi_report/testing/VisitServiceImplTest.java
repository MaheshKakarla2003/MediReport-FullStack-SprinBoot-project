package com.v1.medi_report.testing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.v1.medi_report.dto.VisitCreateRequest;
import com.v1.medi_report.dto.VisitSummaryResponse;
import com.v1.medi_report.entity.Hospital;
import com.v1.medi_report.entity.Patient;
import com.v1.medi_report.entity.Visit;
import com.v1.medi_report.repository.DocumentRepository;
import com.v1.medi_report.repository.HospitalRepository;
import com.v1.medi_report.repository.PatientRepository;
import com.v1.medi_report.repository.VisitRepository;
import com.v1.medi_report.service.VisitServiceImpl;

@ExtendWith(MockitoExtension.class)
class VisitServiceImplTest {
	@Mock
	private VisitRepository visitRepository;

	@Mock
	private PatientRepository patientRepository;

	@Mock
	private HospitalRepository hospitalRepository;
	@Mock
	private DocumentRepository documentRepository;

	@InjectMocks
	private VisitServiceImpl visitServiceImpl;

	@Test
	public void testCreateVisit() {

		VisitCreateRequest request = new VisitCreateRequest();

		request.setPatientId(1L);
		request.setHospitalId(1L);

		Patient patient = new Patient();
		Hospital hospital = new Hospital();

		Visit visit = new Visit();
		visit.setId(1L);
		visit.setPatient(patient);
		visit.setHospital(hospital);

		when(patientRepository.findById(request.getPatientId())).thenReturn(Optional.of(patient));
		when(hospitalRepository.findById(request.getHospitalId())).thenReturn(Optional.of(hospital));

		when(visitRepository.save(any(Visit.class))).thenReturn(visit);
		VisitSummaryResponse response = visitServiceImpl.createVisit(request);

		assertNotNull(response);
		verify(visitRepository).save(any(Visit.class));
		assertEquals(1L, response.getId());
	}

	@Test
	public void getVisitByIdTest() {

		Long visitId = 1l;

		Patient patient = new Patient();
		Hospital hospital = new Hospital();

		Visit visit = new Visit();
		visit.setId(visitId);
		visit.setPatient(patient);
		visit.setHospital(hospital);

		when(visitRepository.findById(visitId)).thenReturn(Optional.of(visit));
		when(documentRepository.countByVisit(visit)).thenReturn(5L);
		VisitSummaryResponse response = visitServiceImpl.getVisitById(visitId);
		assertNotNull(response);
		verify(visitRepository).findById(visitId);
		verify(documentRepository).countByVisit(visit);
		assertEquals(visitId, response.getId());

	}

}
