package com.v1.medi_report.repository;


import com.v1.medi_report.entity.Hospital;
import com.v1.medi_report.entity.Patient;
import com.v1.medi_report.entity.Visit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VisitRepository extends JpaRepository<Visit, Long> {

   //GETTING ALL VISITS FOR A PARTICULAR PATIENT IN A PARTICULAR HOSPITAL
    List<Visit> findByPatientAndHospitalOrderByVisitDateDesc(Patient patient, Hospital hospital);
   

	List<Visit> findByPatientId(long patientId);


	List<Visit> findByHospitalId(long hospitalId);



}