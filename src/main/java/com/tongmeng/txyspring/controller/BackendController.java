package com.tongmeng.txyspring.controller;


import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.tongmeng.txyspring.backendmodel.BackendCommonActInfo;


@Controller
@RequestMapping(value = "/backend")
public class BackendController {	
	
	private static final Logger logger = LoggerFactory.getLogger(BackendController.class);

	
	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public String form(ModelMap model) {
		
		model.put("form", new BackendCommonActInfo());
		return "backend/form";
	}
	
	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public String form_insert(@ModelAttribute("form")  BackendCommonActInfo backendCommonActInfo, ModelMap model) {
		
		logger.error(backendCommonActInfo.getAuthor());
		
		List<MultipartFile> pictures = backendCommonActInfo.getPictures();
        if(pictures != null && pictures.size() != 0) {
            for(MultipartFile picture : pictures) {
            	logger.error( picture.getOriginalFilename() );
            }
        }
		
		return "index";
		//return "backend/form";
	}
	
	
	@RequestMapping(value = "/form_info", method = RequestMethod.GET)
	public String form_info(Locale locale, Model model) {

		return "backend/form-info";
	}
	
	
	
}