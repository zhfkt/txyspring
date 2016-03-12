package com.tongmeng.txyspring.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.fasterxml.jackson.annotation.JsonView;
import com.tongmeng.txyspring.ajaxmodel.AjaxJsonViews;
import com.tongmeng.txyspring.ajaxmodel.AjaxResponseBody;
import com.tongmeng.txyspring.ajaxmodel.AjaxResponseBody.RESPONSE_STATUS;


import org.springframework.http.HttpStatus;

@ControllerAdvice(annotations = RestController.class)
public class RestExceptionHandlerControllerAdvice {

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @JsonView(AjaxJsonViews.Public.class)
    @ResponseBody
    public AjaxResponseBody<Void> exception(Exception exception, WebRequest request) {
    	  	return new AjaxResponseBody<Void>(RESPONSE_STATUS.SERVER_ERROR);
    }
	
}
