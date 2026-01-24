package com.v1.medi_report.service;


import com.v1.medi_report.dto.VisitCreateRequest;
import com.v1.medi_report.dto.VisitSummaryResponse;

import java.util.List;

public interface VisitService {

    // CREATING A VISIT THAT CONTAINS BOTH PATIENT AND HOSPITAL IDS
    VisitSummaryResponse createVisit(VisitCreateRequest request);

    List<VisitSummaryResponse> getVisitsForPatientAndHospital(Long patientId, Long hospitalId);

    // Optional helper if needed later
    VisitSummaryResponse getVisitById(Long visitId);

	String deleteVisit(long id);
}

