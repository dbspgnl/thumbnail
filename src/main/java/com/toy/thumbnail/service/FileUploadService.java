package com.toy.thumbnail.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadService {

	public Object imageUploadImage(MultipartFile file) {
		System.out.println(file.getOriginalFilename());
		return file.getOriginalFilename();
	}
	
}
