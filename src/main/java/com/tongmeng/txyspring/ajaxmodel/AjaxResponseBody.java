package com.tongmeng.txyspring.ajaxmodel;

import com.fasterxml.jackson.annotation.JsonView;

public class AjaxResponseBody<T> {
	
	@JsonView(AjaxJsonViews.Public.class)
	private int status;

	@JsonView(AjaxJsonViews.Public.class)
	private String msg;
	
	@JsonView(AjaxJsonViews.Public.class)
	T result;
		
	
	public AjaxResponseBody(int status,T result)
	{
		setStatus(status);
		setResult(result);
	}


	
	void setResult(T result) 
	{
		this.result = result;
	}
	
	void setStatus(int status) {

		String msg = "";
		
		if(status==200)
		{
			msg = "success";
		}
		else if(status==101)
		{
			msg = "Î´µÇÂ¼";
		}
		else if(status==102)
		{
			msg = "²ÎÊý´íÎó";
		}
		else if(status==500)
		{
			msg = "·þÎñÆ÷´íÎó";
		}
		else
		{
			status = 0;
			msg = "";
		}

		this.status = status;
		this.msg = msg;
	}
	
}
