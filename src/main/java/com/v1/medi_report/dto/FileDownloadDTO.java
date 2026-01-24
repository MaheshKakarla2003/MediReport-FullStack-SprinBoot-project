package com.v1.medi_report.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileDownloadDTO {
        
	private byte[] content;
    private String contentType;
    private String fileName;
}
