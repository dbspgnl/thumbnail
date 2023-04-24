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

	// 모든 항목 값1로 설정
	@GetMapping(value="/model/set")
	public ModelAndView modelInfoSet() {
		jsonService.modelInfoSet();
		ModelAndView mv = new ModelAndView("json/index");
		return mv;
    }

	// 첫번째 값 호출
	@GetMapping(value="/model/get")
	public ResponseEntity<?> modelInfoGet() {
		try {
			return new ResponseEntity<>(jsonService.modelInfoGet(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("서버 작업 실패", HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }

	// 입력한 내용대로 첫번째 값을 수정
	@PostMapping(value="/model/update")
	public ResponseEntity<?> modelInfoUpdate(
		@RequestParam Map<String, Object> formData
	) {
		try {
			return new ResponseEntity<>(jsonService.modelInfoUpdate(formData), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("서버 작업 실패", HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }

	@GetMapping(value="/tree")
	public ModelAndView tree() {
		ModelAndView mv = new ModelAndView("json/tree");
		return mv;
    }

	@GetMapping(value="/tree2")
	public ModelAndView tree2() {
		ModelAndView mv = new ModelAndView("json/tree2");
		return mv;
    }

	@GetMapping(value="/tree3")
	public ModelAndView tree3() {
		ModelAndView mv = new ModelAndView("json/tree3");
		return mv;
    }

	@PostMapping(value="/tree/update")
	public ResponseEntity<?> treeUpdate(
		@RequestParam Map<String, String> formData
	) {
		try {
			return new ResponseEntity<>(jsonService.treeUpdate(formData), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("서버 작업 실패", HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
	
}
