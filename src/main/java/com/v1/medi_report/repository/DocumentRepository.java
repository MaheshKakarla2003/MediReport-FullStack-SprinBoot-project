package com.v1.medi_report.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.v1.medi_report.entity.Document;

import com.v1.medi_report.entity.Patient;
import com.v1.medi_report.entity.Visit;

public interface DocumentRepository extends JpaRepository<Document, Long>{

	 //  LIST DOCUMENT INFORMATION FOR A PATIENT MIGHT BE WE NOT USE THIS 
	List<Document> findByPatient(Patient patient);
	
	   
    // GETTING ALL DOCUMENT INFORMATION  FOR A PARTICULAR VISIT 
    List<Document> findByVisitOrderByReportDateDesc(Visit visit);

    //  COUNT OF DOCUMENTS  FOR THAT PARTCULAR VISIT ID
    long countByVisit(Visit visit);


}
