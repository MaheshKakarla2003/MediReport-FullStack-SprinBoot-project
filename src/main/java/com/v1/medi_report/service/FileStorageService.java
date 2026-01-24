package com.v1.medi_report.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {

	
	String storeFile(MultipartFile file) throws IOException;
	byte[] loadFile(String filePath) throws IOException;
}
