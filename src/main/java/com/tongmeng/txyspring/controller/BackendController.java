package com.tongmeng.txyspring.controller;


import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tongmeng.txyspring.backendmodel.BackendCommonActInfo;
import com.tongmeng.txyspring.service.BackendService;



@Controller
@RequestMapping(value = "/backend")
public class BackendController {	
	
	private static final Logger logger = LoggerFactory.getLogger(BackendController.class);

	@Autowired
	private BackendService backendService;
	
	
	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public String form(ModelMap model) {
		
		model.put("form", new BackendCommonActInfo());
		return "backend/form";
	}
	
	
	@RequestMapping(value = "/form_info", method = RequestMethod.GET)
	public String form_info(ModelMap model) {

		model.put("form", new BackendCommonActInfo());
		return "backend/form-info";
	}
	
	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public String form_insert(@ModelAttribute("form")  BackendCommonActInfo backendCommonActInfo) {
				
		backendService.insertBackendCommonActInfo(backendCommonActInfo);
		
		return "backend/form";
	}
	
	
	@RequestMapping(value = "/form_info", method = RequestMethod.POST)
	public String form_info_insert(@ModelAttribute("form")  BackendCommonActInfo backendCommonActInfo) {
				
		backendService.insertBackendCommonActInfo(backendCommonActInfo);
		
		return "backend/form-info";
	}
	
	
	
}