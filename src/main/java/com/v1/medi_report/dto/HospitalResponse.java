package com.v1.medi_report.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HospitalResponse {
	private Long id;
	private String name;
	private String address;
	private String contactNumber;
	private String email;

}
