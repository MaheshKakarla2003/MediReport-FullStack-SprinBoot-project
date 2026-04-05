package com.v1.medi_report.testing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.v1.medi_report.dto.HospitalResponse;
import com.v1.medi_report.dto.PatientCreateRequest;
import com.v1.medi_report.dto.PatientResponse;
import com.v1.medi_report.entity.Hospital;
import com.v1.medi_report.entity.Patient;
import com.v1.medi_report.entity.Visit;
import com.v1.medi_report.repository.HospitalRepository;
import com.v1.medi_report.repository.PatientRepository;
import com.v1.medi_report.repository.VisitRepository;
import com.v1.medi_report.service.PatientServiceImpl;

@ExtendWith(MockitoExtension.class)
class PatientServiceImplTest {

	@Mock
	private PatientRepository patientRepository;
	@Mock
	private HospitalRepository hospitalRepository;
	@Mock
	private VisitRepository visitRepository;

	@InjectMocks
	private PatientServiceImpl patientService;

	@Test
	public void testCreatePatient_Success() {
		PatientCreateRequest request = new PatientCreateRequest();
		request.setAadhaar(123456789012L);
		request.setHospitalId(1L);
		request.setAge(25);
		request.setFullName("Mahesh");
		request.setPhoneNumber("9876543210");
		request.setGender("Male");

		Patient patient = new Patient();
		patient.setId(1l);

		Mockito.when(patientRepository.existsByAadhaarAndHospitalId(request.getAadhaar(), request.getHospitalId()))
				.thenReturn(false);
		Mockito.when(patientRepository.save(any(Patient.class))).thenReturn(patient);

		PatientResponse response = patientService.createPatient(request);
		assertNotNull(response);
		Mockito.verify(patientRepository).save(any(Patient.class));
	}

	@Test
	void testDeletePatient_Success() {

		long patientId = 1L;

		Visit visit = new Visit();
		List<Visit> visits = List.of(visit);

		Patient patient = new Patient();
		patient.setId(patientId);

		when(visitRepository.findByPatientId(patientId)).thenReturn(visits);

		when(patientRepository.findById(patientId)).thenReturn(Optional.of(patient));

		String result = patientService.deletePatient(patientId);

		assertEquals("Patient deleted successfully", result);

		verify(visitRepository).deleteAll(visits);
		verify(patientRepository).delete(patient);
	}

	@Test
	public void deletePatient_Failure() {
		when(patientRepository.findById(1l)).thenReturn(Optional.empty());
		assertThrows(IllegalArgumentException.class, () -> patientService.deletePatient(1l));

	}

	@Test
	void testGetHospitalsByMobileAndAadhaar() {

		long aadhaar = 123456789012L;
		String mobile = "9876543210";

		List<Long> hospitalIds = List.of(1L, 2L);

		Hospital hospital1 = new Hospital();
		Hospital hospital2 = new Hospital();

		List<Hospital> hospitals = List.of(hospital1, hospital2);

		when(patientRepository.findHospitalIdsByPhoneAndAadhaar(aadhaar, mobile)).thenReturn(hospitalIds);

		when(hospitalRepository.findByIdIn(hospitalIds)).thenReturn(hospitals);

		List<HospitalResponse> result = patientService.getHospitalsByMobileAndAadhaar(aadhaar, mobile);

		assertEquals(2, result.size());
		verify(hospitalRepository).findByIdIn(hospitalIds);
	}
}
