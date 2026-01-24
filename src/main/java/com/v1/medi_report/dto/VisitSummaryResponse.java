package com.v1.medi_report.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VisitSummaryResponse {

    private Long id;               

    private String diseaseName;
    private String doctorName;
    private LocalDate visitDate;

    private Long patientId;
    private String patientName;

    private Long hospitalId;
    private String hospitalName;

    private Long reportCount;
}