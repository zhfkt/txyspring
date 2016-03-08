package com.tongmeng.txyspring.controller;


import java.util.List;
import java.util.Locale;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.annotation.JsonView;

import com.tongmeng.txyspring.ajaxmodel.AjaxJsonViews;
import com.tongmeng.txyspring.ajaxmodel.AjaxSliders;
import com.tongmeng.txyspring.model.Sliders;
import com.tongmeng.txyspring.dao.SlidersDao;

@CrossOrigin
@RestController
public class HomeRestController {

	@JsonView(AjaxJsonViews.Public.class)
	@RequestMapping(value = "/api/home/GetSliders", method = RequestMethod.GET)
	public AjaxSliders GetSliders(Locale locale, Model model) {
		
		List<Sliders> sl_arr = new SlidersDao().listSliders();
		AjaxSliders AjaxResult = new AjaxSliders(200, sl_arr);
		return AjaxResult;
	}

}

