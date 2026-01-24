package com.v1.medi_report.exception;

import java.time.LocalDateTime;
import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiErrorResponse {
	private boolean success;
    private String message;
    private LocalDateTime timestamp;
    private List<FieldErrorDTO> errors;
}
