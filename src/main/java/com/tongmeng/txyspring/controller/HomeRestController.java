package com.tongmeng.txyspring.controller;


import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.annotation.JsonView;

import com.tongmeng.txyspring.ajaxmodel.AjaxJsonViews;
import com.tongmeng.txyspring.ajaxmodel.AjaxSliders;
import com.tongmeng.txyspring.dao.SlidersDao;


@RestController
public class HomeRestController {

	@Autowired
	private SlidersDao sd;
	
	@JsonView(AjaxJsonViews.Public.class)
	@RequestMapping(value = "/api/home/GetSliders", method = RequestMethod.GET)
	public AjaxSliders GetSliders(Locale locale, Model model) {
		
		AjaxSliders AjaxResult = new AjaxSliders(200, sd.listSliders());
		return AjaxResult;
	}

}

