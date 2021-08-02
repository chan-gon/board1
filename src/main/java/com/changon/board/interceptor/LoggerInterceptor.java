package com.changon.board.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoggerInterceptor extends HandlerInterceptorAdapter {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) // controller 접근 전에 실행
			throws Exception {
		logger.debug("===================================");
		logger.debug("============= BEGIN ===============");
		logger.debug("Request URI ====> " + request.getRequestURI());
		return super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, // controller 경유 후 view로 가기 전에 실행
			ModelAndView modelAndView) throws Exception {
		logger.debug("============== END ================");
		logger.debug("===================================");
	}
	
}
