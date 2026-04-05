package com.v1.medi_report.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.v1.medi_report.entity.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {

	@Query(value = """
			SELECT *
			FROM patient p
			WHERE p.phone_number = :phoneNumber
			  AND p.id IN (
			      SELECT MIN(id)
			      FROM patient
			      WHERE phone_number = :phoneNumber
			      GROUP BY aadhaar
			  )
			""", nativeQuery = true)
	List<Patient> findUniquePatientsByPhoneNumber(@Param("phoneNumber") String phoneNumber);

	@Query(value = """
			SELECT DISTINCT hospital_id
			FROM patient
			WHERE phone_number = :phoneNumber
			  AND aadhaar = :aadhaar
			""", nativeQuery = true)
	List<Long> findHospitalIdsByPhoneAndAadhaar(@Param("aadhaar") Long aadhaar,
			@Param("phoneNumber") String phoneNumber);

	Page<Patient> findByHospitalId(long id, Pageable pageable);

	List<Patient> findByHospitalIdAndPhoneNumber(long id, String phoneNumber);

	boolean existsByAadhaarAndHospitalId(long aadhaar, long hospitalId);

	Optional<Patient> findByAadhaarAndHospitalId(Long aadhaar, Long id);

}
