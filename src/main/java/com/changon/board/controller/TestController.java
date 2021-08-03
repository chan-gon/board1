package com.changon.board.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController // RestController가 붙은 메서드는 자동으로 ResponseBody 적용
public class TestController {
	
	/*
	 * ResponseBody는 Spring의 MessageConverter에 의해
	 * 화면(HTML)이 아닌 리턴 타입에 해당하는 데이터 자체를 반환한다.
	 */
	
	@RequestMapping(value = "/message")
	public Map<Integer, Object> testByResponseBody() {
		Map<Integer, Object> members = new HashMap<>();
		
		for(int i = 1; i <= 20; i++) {
			Map<String, Object> member = new HashMap<>();
			member.put("idx", i);
			member.put("nickname", i + "번 멤버");
			member.put("height", i + 20);
			member.put("weight", i + 30);
			members.put(i, member);
		}
		return members;
	}

}
