package com.v1.medi_report.service;

import java.io.IOException;
import java.util.List;

import com.v1.medi_report.dto.FileDownloadDTO;
import com.v1.medi_report.dto.DocumentSummaryResponse;
import com.v1.medi_report.dto.DocumentUploadRequest;

public interface DocumentService {

	
	  // UPLOAD DOCUMENT FOR VISIT
    DocumentSummaryResponse uploadForVisit(DocumentUploadRequest request) throws IOException;

    // GETTING ALL DOCUMENT INFORMATION  FOR A PARTICULAR VISIT 
    List<DocumentSummaryResponse> getDocumentsForVisit(Long visitId);
    
    // 4) DOWNLOAD / VIEW A SINGLE DOCUMENT FILE USING FILE REPOSITORY
	FileDownloadDTO loadDocumentFile(Long documentId) throws IOException;

	 // 5) DELETE  A DOCUMENT
	String deleteDocument(long id); 
	




}
