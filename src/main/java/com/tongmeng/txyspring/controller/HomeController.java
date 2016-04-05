package com.tongmeng.txyspring.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import com.tongmeng.txyspring.model.UserAll;
import com.tongmeng.txyspring.service.identity.UserInfoSession;

@Controller
public class HomeController {

	@Autowired
	private UserInfoSession userInfoSession;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {

		UserAll userAll = userInfoSession.getUserAll();

		int userId = 0;
		String oriId = "";

		if (userAll != null) {
			userId = userAll.getId();
			oriId = userAll.getOriId();
		} else {
			userId = 0;
			oriId = "Not login";
		}

		model.addAttribute("UserID", userId);
		model.addAttribute("oriId", oriId);

		return "index";
	}
}
