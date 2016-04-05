package com.tongmeng.txyspring.service.identity;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import com.tongmeng.txyspring.model.UserAll;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserInfoSession {

	private UserAll userAll = null;

	public UserAll getUserAll() {
		return userAll;
	}

	// Multi-thread mutex protected in xml config.
	public void setUserAll(UserAll userAll) {
		this.userAll = userAll;
	}

	public boolean isLogined() {
		if (userAll == null) {
			return false;
		} else {
			return true;
		}
	}

}
