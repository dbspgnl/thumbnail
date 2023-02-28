package com.toy.thumbnail.service;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadService {

	public String imageUploadImage(MultipartFile file) {
		try {
			Path path = Paths.get("d:","data", file.getOriginalFilename());
			file.transferTo(new File(path.toString()));
			return "/api/file/download/"+file.getOriginalFilename();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ResponseEntity<?> download(String fileName) {
		try {
			Path path = Paths.get( "d:","data",fileName);
			System.out.println(path.toString());
			Resource resource = new FileSystemResource(path);
			return ResponseEntity
				.status(HttpStatus.OK)
				.contentType(MediaType.APPLICATION_OCTET_STREAM)
				.header(HttpHeaders.CONTENT_DISPOSITION, ContentDisposition.attachment()
					.filename(fileName, StandardCharsets.UTF_8)
					.build()
					.toString())
				.body(resource);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
