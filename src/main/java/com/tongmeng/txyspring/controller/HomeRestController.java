package com.tongmeng.txyspring.controller;


import java.util.ArrayList;
import java.util.Locale;
import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.annotation.JsonView;

import com.tongmeng.txyspring.ajaxmodel.AjaxJsonViews;
import com.tongmeng.txyspring.ajaxmodel.AjaxSliders;
import com.tongmeng.txyspring.model.Sliders;

@RestController
public class HomeRestController {

	@JsonView(AjaxJsonViews.Public.class)
	@RequestMapping(value = "/api/home/GetSliders", method = RequestMethod.GET)
	public AjaxSliders GetSliders(Locale locale, Model model) {
		ArrayList<Sliders> sl_arr = new ArrayList<Sliders>();
		for (int i = 0; i < 2; i++) {
			try {
				Sliders sl = new Sliders("title", new URI("http://www.baidu.com").toString());
				sl_arr.add(sl);
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
			
		}

		AjaxSliders AjaxResult = new AjaxSliders(200, sl_arr);
		return AjaxResult;
	}

}

