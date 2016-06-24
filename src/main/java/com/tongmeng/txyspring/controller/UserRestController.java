package com.tongmeng.txyspring.controller;

import java.util.List;

import javax.security.auth.login.CredentialException;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.annotation.JsonView;
import com.tongmeng.txyspring.model.ajax.ActInfoAjax;
import com.tongmeng.txyspring.model.ajax.AjaxJsonViews;
import com.tongmeng.txyspring.model.ajax.AjaxResponseBody;
import com.tongmeng.txyspring.model.ajax.AjaxResponseBody.RESPONSE_STATUS;
import com.tongmeng.txyspring.service.UserService;
import com.tongmeng.txyspring.service.identity.IdentityInterface;
import com.tongmeng.txyspring.service.identity.UserInfoSession;

import org.slf4j.Logger;


@RestController
@RequestMapping(value = "/api/user")
public class UserRestController {

	private static final Logger logger = LoggerFactory.getLogger(UserRestController.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserInfoSession currentUserSession;
		
	@JsonView(AjaxJsonViews.Public.class)
	@RequestMapping(value = "/AddFavor", method = RequestMethod.POST)
	public AjaxResponseBody<Void> changeFavour(
			@RequestParam(value = "id", required = true) int id,
			@RequestParam(value = "action", required = true) String action			
			) throws CredentialException {
		
		try
		{
			userService.changeFavour(id, action);	
		}
		catch(ConstraintViolationException e)
		{
			logger.warn("Parse and execute the SQL with failure (a foreign key constraint fails)");
			logger.warn(e.getMessage());
			throw new MethodArgumentTypeMismatchException(id, null, action, null, e);
		}

		
		return new AjaxResponseBody<Void>(RESPONSE_STATUS.SUCCESS);
	}	
	
	
	@JsonView(AjaxJsonViews.Public.class)
	@RequestMapping(value = "/GetFavorList", method = RequestMethod.GET)
	public AjaxResponseBody<List<ActInfoAjax>> getFavorList(
			@RequestParam(value = "type", required = false, defaultValue = "1") int type
			) throws CredentialException {
		

		List<ActInfoAjax> ajaxLstAct = userService.getFavorList(type);			
		return new AjaxResponseBody<List<ActInfoAjax> >(RESPONSE_STATUS.SUCCESS,ajaxLstAct);
	}
	
	
	//http://localhost:8080/txyspring/api/user/Login?ticket=1
	@RequestMapping(value = "/Login", method = RequestMethod.GET)
	public ModelAndView login(HttpServletRequest request) throws MissingServletRequestParameterException {
		
		IdentityInterface.UserIdentity userIdentity = userService.getIdentity(request);
		
		if(userIdentity.ifLogin())
		{
			int userId = userService.getUserIdByIdentity(userIdentity);
			
			// session setter and userInfoSession is session Scope
			currentUserSession.setUserId(userId);
		}
		else
		{
			currentUserSession.setUserId(UserInfoSession.NOT_LOGIN);
		}
		


		return new ModelAndView("redirect:/");
	}
	
	
	@JsonView(AjaxJsonViews.Public.class)
	@RequestMapping(value = "/ModifyUserName", method = RequestMethod.POST)
	public AjaxResponseBody<Void> modifyUserName(
			@RequestParam(value = "username", required = true) String username			
			) throws CredentialException {
		

		userService.modifyUserName(username);	
		return new AjaxResponseBody<Void>(RESPONSE_STATUS.SUCCESS);
	}		
	
	
}
