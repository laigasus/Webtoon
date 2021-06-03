package com.webtoon.controller;

import java.util.Enumeration;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(HttpSession session, Locale locale, Model model) {

		//// 저장된 세션을 모두 보여주는 코드입니다
		Enumeration<?> attrName = session.getAttributeNames();
		while (attrName.hasMoreElements()) {
			String attr = (String) attrName.nextElement();
			System.out.println(attr + " : " + session.getAttribute(attr));
		}
		////
		return "index";
	}

	
}
