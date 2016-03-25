package com.tongmeng.txyspring.controller;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.tongmeng.txyspring.ajaxmodel.ActInfoAjax;
import com.tongmeng.txyspring.ajaxmodel.AjaxJsonViews;
import com.tongmeng.txyspring.ajaxmodel.AjaxResponseBody;
import com.tongmeng.txyspring.ajaxmodel.AjaxResponseBody.RESPONSE_STATUS;
import com.tongmeng.txyspring.model.CommonActInfo;
import com.tongmeng.txyspring.service.UserService;

import org.slf4j.Logger;

@RestController
@RequestMapping(value = "/api/user")
public class UserRestController {

	private static final Logger logger = LoggerFactory.getLogger(UserRestController.class);
	
	@Autowired
	private UserService us;
	
	@JsonView(AjaxJsonViews.Public.class)
	@RequestMapping(value = "/AddFavor", method = RequestMethod.GET)
	public AjaxResponseBody<Void> changeFavour(
			@RequestParam(value = "id", required = true) int id,
			@RequestParam(value = "action", required = true) String action			
			) {
		
		try
		{
			us.changeFavour(id, action);	
		}
		catch(ConstraintViolationException e)
		{
			logger.warn("Parse and execute the SQL with failure 'a foreign key constraint fails'");
			logger.warn(e.toString());
		}

		
		return new AjaxResponseBody<Void>(RESPONSE_STATUS.SUCCESS);
	}	
	
	
	@JsonView(AjaxJsonViews.ActInfo.class)
	@RequestMapping(value = "/GetFavorList", method = RequestMethod.GET)
	public  AjaxResponseBody<List<ActInfoAjax>> getFavorList() {
		
		List<CommonActInfo> lstAct = us.getFavorList();
		List<ActInfoAjax> ajaxLstAct = new ArrayList<ActInfoAjax>();

		for (CommonActInfo commonActInfo : lstAct) {
			//ajaxLstAct.add(new ActInfoAjax(commonActInfo));
		}		
		
		return new AjaxResponseBody<List<ActInfoAjax> >(RESPONSE_STATUS.SUCCESS,ajaxLstAct);
	}		
	
	
}
