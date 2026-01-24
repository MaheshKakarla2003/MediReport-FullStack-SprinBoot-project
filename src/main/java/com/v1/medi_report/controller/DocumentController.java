package com.v1.medi_report.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;

import com.v1.medi_report.dto.DocumentSummaryResponse;
import com.v1.medi_report.dto.DocumentUploadRequest;
import com.v1.medi_report.dto.FileDownloadDTO;
import com.v1.medi_report.service.DocumentService;

import jakarta.validation.Valid;




@RestController
@RequestMapping("/api/documents")

public class DocumentController {
	
	@Autowired
	 private DocumentService documentService;


	    // UPLOAD DOCUMENT FOR VISIT
	    @PostMapping( value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE )
	    public ResponseEntity<DocumentSummaryResponse> uploadDocumentForVisit( @Valid @ModelAttribute DocumentUploadRequest request ) throws IOException {

	        DocumentSummaryResponse response = documentService.uploadForVisit(request);
	        return ResponseEntity.ok(response);
	    }

	    
	    // GETTING ALL DOCUMENT INFORMATION  FOR A PARTICULAR VISIT 
	    @GetMapping("/visit/{visitId}")
	    public ResponseEntity<List<DocumentSummaryResponse>> getDocumentsForVisit( @PathVariable Long visitId) {
	        List<DocumentSummaryResponse> documents = documentService.getDocumentsForVisit(visitId);
	        return ResponseEntity.ok(documents);
	    }
	    
	    
	    // 4) VIEW A SINGLE DOCUMENT FILE USING FILE REPOSITORY
	    @GetMapping("/{documentId}/file")
	    public ResponseEntity< ?> viewDocumentFile(@PathVariable Long documentId) throws IOException {

	            FileDownloadDTO dto = documentService.loadDocumentFile(documentId);

	            HttpHeaders headers = new HttpHeaders();
	            headers.setContentType(MediaType.parseMediaType(dto.getContentType()));
	            headers.set(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + dto.getFileName() + "\"");

	            return new ResponseEntity<>(dto.getContent(), headers, HttpStatus.OK);
	        
	    }
	        
	    // DOWLOAD A PARTICULAR FILE IN SYSTEM
	    @GetMapping("/{documentId}/download")
	    public ResponseEntity< ?> downloadDocumentFile(@PathVariable Long documentId) throws IOException {

	            FileDownloadDTO dto = documentService.loadDocumentFile(documentId);

	            HttpHeaders headers = new HttpHeaders();
	            headers.setContentType(MediaType.parseMediaType(dto.getContentType()));
	            headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dto.getFileName() + "\"");

	            return new ResponseEntity<>(dto.getContent(), headers, HttpStatus.OK);
	        
	    }
	    
       // DELETE A DOCUMENT
	   @DeleteMapping("/{id}")
	   public ResponseEntity<String> deleteDocument(@PathVariable long id)
	   {
		   String res= documentService.deleteDocument(id);
		   return ResponseEntity.status(HttpStatus.ACCEPTED).body(res);
	   }
	


}
