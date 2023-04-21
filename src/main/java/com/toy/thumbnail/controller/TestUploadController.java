package com.toy.thumbnail.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.toy.thumbnail.service.TestUploadService;

@Controller
@RequestMapping(value = "/api/test")
public class TestUploadController {

	@Autowired
	TestUploadService testUploadService;
	
	@GetMapping(value="/list")
	public ModelAndView list() {
		ModelAndView mv = new ModelAndView("test/index");
		return mv;
    }

	@GetMapping(value="/modal")
	public ModelAndView modal() {
		ModelAndView mv = new ModelAndView("test/modal");
		return mv;
    }

	@PostMapping(value="/upload/sameFileChk")
	public ResponseEntity<?> uploadImage(
		// @RequestHeader Map<String,String> headers,
		@RequestPart MultipartFile[] file, 
        @RequestParam(value="lastModified") String[] lastModified
	) {
		try {
			return new ResponseEntity<>(testUploadService.sameFileCheck(file, lastModified), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("서버 작업 실패", HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }


}
