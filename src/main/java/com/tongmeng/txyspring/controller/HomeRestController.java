package com.tongmeng.txyspring.controller;


import java.util.ArrayList;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tongmeng.txyspring.model.ajax.AjaxJsonViews;
import com.tongmeng.txyspring.model.ajax.AjaxResponseBody;
import com.tongmeng.txyspring.model.ajax.SlidersAjax;
import com.tongmeng.txyspring.model.ajax.AjaxResponseBody.RESPONSE_STATUS;
import com.tongmeng.txyspring.service.SlidersService;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping(value = "/api/home")
public class HomeRestController {

	
	private static final Logger logger = LoggerFactory.getLogger(HomeRestController.class);
	
	@Autowired
	private SlidersService slidersService;
	
	@JsonView(AjaxJsonViews.Public.class)
	@RequestMapping(value = "/GetSliders", method = RequestMethod.GET)
	public AjaxResponseBody<ArrayList<SlidersAjax> > GetSliders() {
		
		AjaxResponseBody<ArrayList<SlidersAjax> > AjaxResult = 
				new AjaxResponseBody<ArrayList<SlidersAjax> >(RESPONSE_STATUS.SUCCESS, slidersService.listSliders());
		return AjaxResult;

	}


	public static class AuthTongjiJson
	{
		@JsonView(AjaxJsonViews.Public.class)
		private String type;

		@JsonView(AjaxJsonViews.Public.class)
		private String result;
		 
		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getResult() {
			return result;
		}

		public void setResult(String result) {
			this.result = result;
		}


	}
	
	
	@JsonView(AjaxJsonViews.Public.class)
	@RequestMapping(value = "/test_return", method = RequestMethod.GET)
	public AuthTongjiJson testMyReturn() {
		AuthTongjiJson authTongjiJson =  new AuthTongjiJson();
		
		authTongjiJson.setResult("1112");
		authTongjiJson.setType("error");
		
		return authTongjiJson;
	}
		
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String testMy(Locale locale, Model model) {

		OkHttpClient client = new OkHttpClient();
		String url= "http://115.28.68.32:8080/api/home/test_return";
		
		Request request = new Request.Builder()
	      .url(url)
	      .build();

		Response response;
		try {
			response = client.newCall(request).execute();
			String authJson = response.body().string();
			
			ObjectMapper mapper = new ObjectMapper();
			//JSON from String to Object
			AuthTongjiJson authTongjiJson = mapper.readValue(authJson, AuthTongjiJson.class);
			
			if(authTongjiJson.getType().equals("error"))
			{
				//throw illagal(authTongjiJson.getType().getType())
			}
			
			
	    	logger.error(authTongjiJson.getType());
	    	logger.error(authTongjiJson.getResult());
			
		} 
		
		/*catch(ConnectException e)
		 {
		  	//throw ConnectException("login failed due to accessing TONGJI cloud")
		 }
		 */		
		
		catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(e.toString());
			
			//throw Exception(authTongjiJson.getType().getType())
			
			return "failed";
		}
		


		return "success";
	}

}

