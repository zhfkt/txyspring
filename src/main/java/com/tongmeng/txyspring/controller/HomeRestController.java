package com.tongmeng.txyspring.controller;


import java.util.Locale;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.annotation.JsonView;

import com.tongmeng.txyspring.ajaxmodel.AjaxJsonViews;
import com.tongmeng.txyspring.ajaxmodel.AjaxResponseBody;
import com.tongmeng.txyspring.ajaxmodel.AjaxResponseBody.RESPONSE_STATUS;
import com.tongmeng.txyspring.dao.SlidersDao;
import com.tongmeng.txyspring.model.Sliders;;


@RestController
public class HomeRestController {

	@Autowired
	private SlidersDao sd;
	
	@JsonView(AjaxJsonViews.Public.class)
	@RequestMapping(value = "/api/home/GetSliders", method = RequestMethod.GET)
	public AjaxResponseBody<List<Sliders> > GetSliders(Locale locale, Model model) {
		
		AjaxResponseBody<List<Sliders> > AjaxResult = 
				new AjaxResponseBody<List<Sliders> >(RESPONSE_STATUS.SUCCESS, sd.listSliders());
		return AjaxResult;

	}

}

