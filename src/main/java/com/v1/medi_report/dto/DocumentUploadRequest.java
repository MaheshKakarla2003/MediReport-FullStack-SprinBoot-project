package com.v1.medi_report.dto;

import java.time.LocalDate;

import org.springframework.web.multipart.MultipartFile;

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
public class DocumentUploadRequest {
	@NotNull(message = "Visit ID is required")
    private Long visitId;

    @NotBlank(message = "Document type is required")
    private String docType;

    private String description;

    @NotNull(message = "Report date is required")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate reportDate;
    
    @NotNull(message="file is required")
    private MultipartFile file;
    
    @NotBlank(message = "file-name is required")
    private String fileName;
}
