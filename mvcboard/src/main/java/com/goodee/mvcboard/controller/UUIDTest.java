package com.goodee.mvcboard.controller;

import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class UUIDTest {
	@GetMapping("uuidTest") //uuidTest경로로 들어올 때 실행
	public String uuidTest() {
		UUID uuid = UUID.randomUUID(); //무작위로 uuid를 생성하고 변수에 할당합니다
		log.debug(uuid.toString().replace("-", "")); // uuid 객체를 문자열로 변환하여 - 문자를 제거한 후, 디버그 로그에 출력
		return "";// 빈문자열을 반환
	}
}
// UUID.randomUUID().toString() : 2db3a8f3-b2c7-4b7f-8c85-75967e1e8e55