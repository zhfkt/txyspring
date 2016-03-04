package com.tongmeng.txyspring.ajaxmodel;

import com.fasterxml.jackson.annotation.JsonView;

public class AjaxResponseBody {
	
	@JsonView(AjaxJsonViews.Public.class)
	private int status;

	@JsonView(AjaxJsonViews.Public.class)
	private String msg;
	
	public AjaxResponseBody(int status)
	{
		setStatus(status);
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
			msg = "²ÎÊı´íÎó";
		}
		else if(status==500)
		{
			msg = "·şÎñÆ÷´íÎó";
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
