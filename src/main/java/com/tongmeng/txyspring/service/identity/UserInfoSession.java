package com.tongmeng.txyspring.service.identity;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;


@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserInfoSession {

	public static final int NOT_LOGIN = 0;
	public static final String NOT_LOGIN_STRING = "未登录";
	
	private int userId = NOT_LOGIN;
	
	public int getUserId() {
		return userId;
	}

	//Multi-thread mutex protected in xml config.
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	public boolean isLogined()
	{
		if(userId == NOT_LOGIN)
		{
			return false;
		}
		else
		{
			return true;
		}
	}

}
