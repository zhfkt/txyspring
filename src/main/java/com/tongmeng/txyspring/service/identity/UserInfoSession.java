package com.tongmeng.txyspring.service.identity;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;


@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserInfoSession {
	
	private int userId = 0;

	public int getUserId() {
		return userId;
	}

	//Multi-thread mutex protected in xml config.
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
}
