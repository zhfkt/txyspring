package com.tongmeng.txyspring.service;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.login.CredentialException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tongmeng.txyspring.ajaxmodel.ActInfoAjax;
import com.tongmeng.txyspring.dao.CommonActInfoDao;
import com.tongmeng.txyspring.dao.UserActCltDao;
import com.tongmeng.txyspring.dao.UserDao;
import com.tongmeng.txyspring.model.CommonActInfo;
import com.tongmeng.txyspring.model.UserAll;
import com.tongmeng.txyspring.service.identity.IdentityInterface;
import com.tongmeng.txyspring.service.identity.UserInfoSession;

@Service
public class UserService {
	

	@Autowired
	private CommonActInfoDao commonActInfoDao;

	@Autowired
	private UserActCltDao userActCltDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private UserInfoSession currentUserSession;

	@Transactional(readOnly = true)
	public boolean isFavoured(int id) {

		if (!currentUserSession.isLogined()) {
			return false;
		}
		int userId = currentUserSession.getUserId();

		return userActCltDao.isFavoured(id, userId);
	}

	@Transactional
	public void changeFavour(int id, String action) throws CredentialException {

		if (!currentUserSession.isLogined()) {
			throw new CredentialException("Not login in changeFavour");
		}
		int userId = currentUserSession.getUserId();

		if (action.equals("add")) {
			if (!userActCltDao.addFavour(id, userId)) {
				return;
			}
			commonActInfoDao.addFavour(id);
		} else if (action.equals("remove")) {
			if (!userActCltDao.removeFavoured(id, userId)) {
				return;
			}

			commonActInfoDao.minusFavour(id);
		}

		return;
	}

	@Transactional(readOnly = true)
	public List<ActInfoAjax> getFavorList(int type) throws CredentialException {

		if (!currentUserSession.isLogined()) {
			throw new CredentialException("Not login in getFavorList");
		}
		int userId = currentUserSession.getUserId();

		List<CommonActInfo> lstAct = null;

		if (type == 1) {
			lstAct = userActCltDao.getFavorListByUser(userId, 10000);
		} else if (type == 2) {
			lstAct = userActCltDao.getFavorListByUser(userId, 20000);
		} else {
			lstAct = userActCltDao.getFavorListByUser(userId, 10000);
		}

		List<ActInfoAjax> ajaxLstAct = new ArrayList<ActInfoAjax>();
		// for the logic , always true here
		for (CommonActInfo commonActInfo : lstAct) {
			ajaxLstAct.add(new ActInfoAjax(commonActInfo, true));
		}

		return ajaxLstAct;
	}

	@Transactional
	public int insertNewLoginId(HttpServletRequest request) {

		IdentityInterface identity = IdentityInterface.getSchFactory(request);

		IdentityInterface.SCHCODE schCode = identity.getSchCode();
		String oriId = identity.getOriId(request);

		if (oriId.equals("")) {
			return 0;
		}

		
		UserAll user = userDao.insertUserByOriId(schCode.getValue(), oriId);
		return user.getId();	
		

	}
	
	@Transactional(readOnly = true)
	public int selectLoginId(HttpServletRequest request) {
		
		IdentityInterface identity = IdentityInterface.getSchFactory(request);

		IdentityInterface.SCHCODE schCode = identity.getSchCode();
		String oriId = identity.getOriId(request);

		if (oriId.equals("")) {
			return 0;
		}
		
		
		UserAll user = userDao.selectUserByOriId(schCode.getValue(), oriId);
		
		if(user != null)
		{
			return user.getId();
		}
		else
		{
			return 0;
		}
	
	}
	
	@Transactional
	public void modifyUserName(String username) throws CredentialException {

		if (!currentUserSession.isLogined()) {
			throw new CredentialException("Not login in modifyUserName");
		}
		int userId = currentUserSession.getUserId();

		userDao.modifyUserName(username, userId);
	}

}
