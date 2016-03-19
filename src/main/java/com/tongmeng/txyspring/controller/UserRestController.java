package com.tongmeng.txyspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.tongmeng.txyspring.ajaxmodel.AjaxJsonViews;
import com.tongmeng.txyspring.ajaxmodel.AjaxResponseBody;
import com.tongmeng.txyspring.ajaxmodel.AjaxResponseBody.RESPONSE_STATUS;
import com.tongmeng.txyspring.service.UserService;

@RestController
@RequestMapping(value = "/api/user")
public class UserRestController {

	@Autowired
	private UserService us;
	
	@JsonView(AjaxJsonViews.ActDetail.class)
	@RequestMapping(value = "/AddFavor", method = RequestMethod.GET)
	public AjaxResponseBody<Void> changeFavour(
			@RequestParam(value = "id", required = true) int id,
			@RequestParam(value = "action", required = true) String action			
			) {

		us.changeFavour(id, action);
		return new AjaxResponseBody<Void>(RESPONSE_STATUS.SUCCESS);
	}	
	
	
}
