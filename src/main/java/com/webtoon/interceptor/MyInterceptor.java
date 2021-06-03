package com.webtoon.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MyInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		HttpSession session= request.getSession(false);
		
		String session_user_email = (String)session.getAttribute("session_user_email");
		String session_user_nick = (String)session.getAttribute("session_user_nick");

		
		
		System.out.println("session user email  " + session_user_email);
		System.out.println(" session_user_nick  " + session_user_nick);
		System.out.println("MyInterCeptor - postHandel");


		modelAndView.addObject("session_user_nick", session_user_nick);  //변수명 변경해야할수도
		modelAndView.addObject("session_user_email", session_user_email);
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {



		
	}
}