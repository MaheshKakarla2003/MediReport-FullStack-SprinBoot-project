package com.v1.medi_report.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PatientResponse {
               
	private Long id;
    private String fullName;
    private String phoneNumber;
    private Integer age;
    private String gender;
    private long hospitalId;
    private long aadhaar;
}
