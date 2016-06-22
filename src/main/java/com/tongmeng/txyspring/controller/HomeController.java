package com.tongmeng.txyspring.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tongmeng.txyspring.service.UserService;
import com.tongmeng.txyspring.service.identity.UserInfoSession;

@Controller
public class HomeController {

	@Autowired
	private UserInfoSession userInfoSession;
	
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {

		String userName = UserInfoSession.NOT_LOGIN_STRING;
		int userId = UserInfoSession.NOT_LOGIN;
		
		if(userInfoSession.isLogined())
		{
			userId = userInfoSession.getUserId();
			userName = userService.getUserName(userId);
		}
		

		model.addAttribute("UserID", userId);
		model.addAttribute("UserName", userName);

		return "index";
	}
	
}
