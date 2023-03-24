package com.toy.thumbnail.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.toy.thumbnail.service.JsonService;

@Controller
@RequestMapping(value = "/api/json")
public class JsonController {

	@Autowired
	JsonService jsonService;

	@GetMapping(value="")
	public ModelAndView list() {
		ModelAndView mv = new ModelAndView("json/index");
		return mv;
    }

	@GetMapping(value="/history/set")
	public ResponseEntity<?> historySave() {
		try {
			return new ResponseEntity<>(jsonService.historySave("철봉", "코딩", "스파이더맨"), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("서버 작업 실패", HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }

	@GetMapping(value="/history/get")
	public ResponseEntity<?> historyGet() {
		try {
			return new ResponseEntity<>(jsonService.historyGet(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("서버 작업 실패", HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }

	@GetMapping(value="/model/set")
	public ModelAndView modelInfoSave() {
		jsonService.modelInfoSave();
		ModelAndView mv = new ModelAndView("json/index");
		return mv;
    }

	@GetMapping(value="/model/get")
	public ResponseEntity<?> modelInfoGet() {
		try {
			return new ResponseEntity<>(jsonService.modelInfoSave(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("서버 작업 실패", HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }

	@PostMapping(value="/model/json")
	public ResponseEntity<?> modelInfoJsonGet(
		@RequestParam Map<String, Object> formData
	) {
		try {
			return new ResponseEntity<>(jsonService.modelInfoJsonGet(formData), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("서버 작업 실패", HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
	
}
