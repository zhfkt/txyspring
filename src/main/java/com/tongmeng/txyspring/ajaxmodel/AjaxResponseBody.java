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
			msg = "未登录";
		}
		else if(status==102)
		{
			msg = "参数错误";
		}
		else if(status==500)
		{
			msg = "服务器错误";
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
