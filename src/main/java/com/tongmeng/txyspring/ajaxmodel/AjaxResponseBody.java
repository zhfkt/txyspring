package com.tongmeng.txyspring.ajaxmodel;

import com.fasterxml.jackson.annotation.JsonView;

public class AjaxResponseBody<T> {

	public enum RESPONSE_STATUS 
	{  
		  SUCCESS, NOT_LOGIN, PARAMETRR_ERROR, SERVER_ERROR,NOT_DEFINED 
	}

	@JsonView(AjaxJsonViews.Public.class)
	private int status;

	@JsonView(AjaxJsonViews.Public.class)
	private String msg;
	
	@JsonView(AjaxJsonViews.Public.class)
	T result;
		
	public AjaxResponseBody(RESPONSE_STATUS status)
	{
		setStatus(status);	
	}
	
	
	public AjaxResponseBody(RESPONSE_STATUS status,T result)
	{
		setStatus(status);
		setResult(result);
	}
	
	void setResult(T result) 
	{
		this.result = result;
	}
	
	void setStatus(RESPONSE_STATUS status) {
	
		switch(status)
		{
			case SUCCESS:
				
				this.msg = "success";		
				this.status = 200;
				break;
				
			case NOT_LOGIN:
				this.msg = "未登录";
				this.status = 101;
				break;
				
			case PARAMETRR_ERROR:
				this.msg = "参数错误";
				this.status = 102;
				break;
				
			case SERVER_ERROR:
				this.msg = "服务器错误";
				this.status = 500;
				break;
				
			default:	
				this.msg = "";
				this.status = 0;
				
		}
	}
	
}
