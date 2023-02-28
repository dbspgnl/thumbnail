package com.toy.thumbnail.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.toy.thumbnail.service.FileUploadService;

@Controller
@RequestMapping(value = "/api/file")
public class FileUploadController {

	@Autowired
	FileUploadService fileUploadService;
	
	@GetMapping(value="/list")
	public ModelAndView hello() {
		ModelAndView mav = new ModelAndView("index");
		return mav;
    }

	@PostMapping(value="/upload/normal")
	public ResponseEntity<?> uploadNormal(
		@RequestBody MultipartFile file
	) {
		try {
			return new ResponseEntity<>(file.getOriginalFilename(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("서버 작업 실패", HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }

	@PostMapping(value="/upload/image")
	public ResponseEntity<?> uploadImage(
		@RequestBody MultipartFile file
	) {
		try {
			return new ResponseEntity<>(fileUploadService.imageUploadImage(file), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("서버 작업 실패", HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }

}
