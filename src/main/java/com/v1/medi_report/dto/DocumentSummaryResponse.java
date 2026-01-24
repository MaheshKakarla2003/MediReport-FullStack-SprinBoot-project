package com.v1.medi_report.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DocumentSummaryResponse {
    
	   private Long id;             // documentId
	    private Long visitId;

	    private String docType;
	    private String description;
	    private String fileName;
	    @JsonFormat(pattern = "yyyy-MM-dd")
	    private LocalDate reportDate;

	    private Long patientId;
	    private String patientName;

	    private Long hospitalId;
	    private String hospitalName;

	    private String diseaseName;
	    private String doctorName;
}
