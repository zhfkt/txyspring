package com.tongmeng.txyspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tongmeng.txyspring.model.web.BackendCommonActInfo;
import com.tongmeng.txyspring.model.web.BackendSliders;

import com.tongmeng.txyspring.service.BackendService;



@Controller
@RequestMapping(value = "/backend")
public class BackendController {	
	
	@Autowired
	private BackendService backendService;

	@RequestMapping(value = "/actDelete", method = RequestMethod.GET)
	@ResponseBody
	public String actDelete(@RequestParam(value = "id", required = true) int id) {

		backendService.deleteActivitiy(id);
		
		return "success";
	}
	
	
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
	
	
	@RequestMapping(value = "/form_slider", method = RequestMethod.GET)
	public String form_slider_insert(ModelMap model) {

		model.put("form", new BackendSliders());
		return "backend/form-slider";
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
	
	
	@RequestMapping(value = "/form_slider", method = RequestMethod.POST)
	public String form_slider_insert(@ModelAttribute("form")  BackendSliders backendSliders) {
				
		backendService.insertBackendSliders(backendSliders);
		
		return "backend/form-slider";
	}
	
	
}