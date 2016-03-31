package com.tongmeng.txyspring.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tongmeng.txyspring.service.identity.UserInfoSession;

@Controller
public class HomeController {

	
	@Autowired
	private UserInfoSession userInfoSession; 

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {

		int userId = userInfoSession.getUserId();
		model.addAttribute("UserID",userId);
		
		return "index";
	}


}

