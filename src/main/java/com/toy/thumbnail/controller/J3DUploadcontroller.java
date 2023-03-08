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

import com.toy.thumbnail.service.J3DUploadService;

@Controller
@RequestMapping(value = "/api/file")
public class J3DUploadcontroller {

	@Autowired
	J3DUploadService j3DUploadService;

	@GetMapping(value="/3d")
	public ModelAndView main() {
		ModelAndView mv = new ModelAndView("3d/3d");
		return mv;
    }

	@GetMapping(value="/3d/obj")
	public ResponseEntity<?> obj() {
		try {
			return new ResponseEntity<>(j3DUploadService.getObjTest(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("서버 작업 실패", HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }

	@PostMapping(value="/upload/test")
	public ModelAndView uploadNormal(
		@RequestBody MultipartFile file
	) {
		j3DUploadService.fileTest(file);
		ModelAndView mv = new ModelAndView("3d/3d");
		return mv;
    }
	
}
