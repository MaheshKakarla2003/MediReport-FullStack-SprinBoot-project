package com.v1.medi_report.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VisitCreateRequest {

    @NotNull(message = "Patient ID is required")
    private Long patientId;

    @NotNull(message = "Hospital ID is required")
    private Long hospitalId;

    @NotBlank(message = "Disease / reason is required")
    private String diseaseName;

    @NotBlank(message = "Doctor name is required")
    private String doctorName;

    @NotNull(message = "Visit date is required")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate visitDate;
}
