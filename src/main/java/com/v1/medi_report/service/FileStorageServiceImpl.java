package com.v1.medi_report.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;

@Service
public class FileStorageServiceImpl  implements FileStorageService{

	 @Value("${medireport.upload-dir}")
	    private String uploadDir;

	    // Store file on disk and return full file path
	    public String storeFile(MultipartFile file) throws IOException {

	        if (file.isEmpty()) {
	            throw new IllegalArgumentException("Uploaded file is empty");
	        }

	        Path uploadPath = Paths.get(uploadDir);
	        if (!Files.exists(uploadPath)) {
	            Files.createDirectories(uploadPath);
	        }

	        String originalFilename = StringUtils.cleanPath(file.getOriginalFilename());
	        String extension = "";

	        int dotIndex = originalFilename.lastIndexOf('.');
	        if (dotIndex >= 0) {
	            extension = originalFilename.substring(dotIndex);
	        }

	        String storedFileName = "doc_" + System.currentTimeMillis() + extension;
	        Path targetPath = uploadPath.resolve(storedFileName);

	        Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);

	        return targetPath.toString();
	    }

	    // Load file bytes from disk
	    public byte[] loadFile(String filePath) throws IOException {
	        Path path = Paths.get(filePath);
	        if (!Files.exists(path)) {
	            throw new IllegalArgumentException("File not found: " + filePath);
	        }
	        return Files.readAllBytes(path);
}
}
