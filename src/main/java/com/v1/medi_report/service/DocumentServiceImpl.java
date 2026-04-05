package com.v1.medi_report.service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.v1.medi_report.dto.DocumentSummaryResponse;
import com.v1.medi_report.dto.DocumentUploadRequest;
import com.v1.medi_report.dto.FileDownloadDTO;
import com.v1.medi_report.dto.Mapper;
import com.v1.medi_report.entity.Document;
import com.v1.medi_report.entity.Visit;
import com.v1.medi_report.exception.NotFoundException;
import com.v1.medi_report.repository.DocumentRepository;
import com.v1.medi_report.repository.VisitRepository;

@Service
public class DocumentServiceImpl implements DocumentService {

	@Autowired
	private DocumentRepository documentRepository;
	@Autowired
	private FileStorageService fileStorageService;
	@Autowired
	private VisitRepository visitRepository;

	// UPLOAD DOCUMENT FOR VISIT
	@Override
	public DocumentSummaryResponse uploadForVisit(DocumentUploadRequest request) throws IOException {

		Visit visit = visitRepository.findById(request.getVisitId()).orElseThrow(
				() -> new NotFoundException("There is no such data available on this id " + request.getVisitId()));

		MultipartFile file = request.getFile();

		if (file.isEmpty()) {
			throw new NotFoundException("File is empty or missing");
		}

		// Store file
		String filePath = fileStorageService.storeFile(file);

		Document saved = documentRepository
				.save(Mapper.toDocumentEntity(request, visit, filePath, file.getContentType()));

		return Mapper.toDocumentSummaryResponse(saved);
	}

	// GETTING ALL DOCUMENT INFORMATION FOR A PARTICULAR VISIT
	@Override
	public List<DocumentSummaryResponse> getDocumentsForVisit(Long visitId) {
		Visit visit = visitRepository.findById(visitId)
				.orElseThrow(() -> new NotFoundException("Invalid visitId: " + visitId));

		List<Document> docs = documentRepository.findByVisitOrderByReportDateDesc(visit);

		return docs.stream().map(Mapper::toDocumentSummaryResponse).collect(Collectors.toList());
	}

	// 4) VIEW A SINGLE DOCUMENT FILE USING FILE REPOSITORY
	@Override
	public FileDownloadDTO loadDocumentFile(Long documentId) throws IOException {

		Document doc = documentRepository.findById(documentId)
				.orElseThrow(() -> new NotFoundException("Document not found: " + documentId));

		byte[] bytes = fileStorageService.loadFile(doc.getFilePath());

		String contentType = doc.getFileType() != null ? doc.getFileType() : MediaType.APPLICATION_OCTET_STREAM_VALUE;

		String fileName = doc.getFileName() != null ? doc.getFileName() : "document_" + doc.getId();

		return new FileDownloadDTO(bytes, contentType, fileName);
	}

	// 5) DELETE A DOCUMENT
	@Override
	public String deleteDocument(long id) {

		Document doc = documentRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Document not found: " + id));

		documentRepository.delete(doc);
		return "document is deleted successfully";
	}

}
