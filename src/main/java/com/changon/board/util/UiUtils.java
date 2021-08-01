package com.changon.board.util;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import com.changon.board.constant.Method;

@Controller
public class UiUtils { // 공용 클래스(게시글 등록, 삭제, 수정 등의 작업 처리 여부 메시지 전송 관리)
	public String showMessageWithRedirect(
			@RequestParam(value = "message", required = false) String message, // 사용자에게 전달할 메시지
			@RequestParam(value = "redirectUri", required = false) String redirectUri, // 리다이렉트 uri
			@RequestParam(value = "method", required = false) Method method, // 상수를 처리하는 HTTP 요청 메서드
			@RequestParam(value = "params", required = false) Map<String, Object> params, Model model) { // 화면에 전달할 파라미터
		
		model.addAttribute("message", message);
		model.addAttribute("redirectUri", redirectUri);
		model.addAttribute("method", method);
		model.addAttribute("params", params);
		
		return "utils/message-redirect";
	}
}
