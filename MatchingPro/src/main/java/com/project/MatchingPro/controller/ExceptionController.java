package com.project.MatchingPro.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.project.MatchingPro.config.exception.NotDataToDB;

@ControllerAdvice	//전역관리, 내 프로젝트 전체
@RestController
public class ExceptionController {

	//value = 어떤 오류 떳을때 이 함수를 실행할 것인지 설정 
		@ExceptionHandler(value=NotDataToDB.class)
		public String db(Exception e) {
			return e.getMessage();
		}
}
