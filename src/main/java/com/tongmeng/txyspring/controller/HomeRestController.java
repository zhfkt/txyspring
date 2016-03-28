package com.tongmeng.txyspring.controller;


import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.annotation.JsonView;

import com.tongmeng.txyspring.ajaxmodel.AjaxJsonViews;
import com.tongmeng.txyspring.ajaxmodel.AjaxResponseBody;
import com.tongmeng.txyspring.ajaxmodel.SlidersAjax;
import com.tongmeng.txyspring.ajaxmodel.AjaxResponseBody.RESPONSE_STATUS;
import com.tongmeng.txyspring.service.SlidersService;;


@RestController
@RequestMapping(value = "/api/home")
public class HomeRestController {

	@Autowired
	private SlidersService slidersService;
	
	@JsonView(AjaxJsonViews.Public.class)
	@RequestMapping(value = "/GetSliders", method = RequestMethod.GET)
	public AjaxResponseBody<ArrayList<SlidersAjax> > GetSliders() {
		
		AjaxResponseBody<ArrayList<SlidersAjax> > AjaxResult = 
				new AjaxResponseBody<ArrayList<SlidersAjax> >(RESPONSE_STATUS.SUCCESS, slidersService.listSliders());
		return AjaxResult;

	}

}

