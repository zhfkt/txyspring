package com.tongmeng.txyspring.controller;

import javax.security.auth.login.CredentialException;

import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.fasterxml.jackson.annotation.JsonView;
import com.tongmeng.txyspring.ajaxmodel.AjaxJsonViews;
import com.tongmeng.txyspring.ajaxmodel.AjaxResponseBody;
import com.tongmeng.txyspring.ajaxmodel.AjaxResponseBody.RESPONSE_STATUS;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;


@ControllerAdvice(annotations = RestController.class)
public class RestExceptionHandlerControllerAdvice {

	private static final Logger logger = LoggerFactory.getLogger(RestExceptionHandlerControllerAdvice.class);
	
    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @JsonView(AjaxJsonViews.Public.class)
    @ResponseBody
    public AjaxResponseBody<Void> MissingParException(Exception exception) {
    	    	
    	logger.error(exception.getMessage().toString());   	
    	return new AjaxResponseBody<Void>(RESPONSE_STATUS.PARAMETRR_ERROR);	 	
    	
    }    	
	
    /*
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @JsonView(AjaxJsonViews.Public.class)
    @ResponseBody
    public AjaxResponseBody<Void> exception(Exception exception, WebRequest request) {
    	    	
    	logger.error(exception.getMessage().toString());
    	logger.error(exception.getCause().toString());
    	return new AjaxResponseBody<Void>(RESPONSE_STATUS.SERVER_ERROR);   
    }
    */
    
    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @JsonView(AjaxJsonViews.Public.class)
    @ResponseBody
    public AjaxResponseBody<Void> MismatchParException(Exception exception, WebRequest request) {
    	    	
    	logger.error(exception.getMessage().toString());
    	//logger.error(exception.getCause().toString());

    	return new AjaxResponseBody<Void>(RESPONSE_STATUS.PARAMETRR_ERROR);	
    }    

    @ExceptionHandler(value = CredentialException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @JsonView(AjaxJsonViews.Public.class)
    @ResponseBody
    public AjaxResponseBody<Void> CredentialException(Exception exception, WebRequest request) {
    	    	
    	logger.error(exception.getMessage().toString());
    	return new AjaxResponseBody<Void>(RESPONSE_STATUS.NOT_LOGIN);	
    }    
    
}
