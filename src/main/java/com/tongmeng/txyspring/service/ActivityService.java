package com.tongmeng.txyspring.service;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.login.CredentialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tongmeng.txyspring.ajaxmodel.ActDetailAjax;
import com.tongmeng.txyspring.ajaxmodel.ActInfoAjax;
import com.tongmeng.txyspring.dao.CommonActInfoDao;
import com.tongmeng.txyspring.model.CommonActInfo;
import com.tongmeng.txyspring.service.identity.UserInfoSession;
import com.tongmeng.txyspring.dao.CommonActInfoDao.SortOption;

@Service
public class ActivityService {

	@Autowired
	private CommonActInfoDao commonActInfoDao;

	@Autowired
	private UserService userService;

	@Autowired
	private UserInfoSession userInfoSession;

	@Transactional(readOnly = true)
	public List<ActInfoAjax> listActivitiesByActCodeAndSchCode(int areacode, int subtype, int sort, int p, int type, boolean isExpired)
			throws CredentialException {

		List<ActInfoAjax> ajaxLstAct = new ArrayList<ActInfoAjax>();

		if (!userInfoSession.isLogined() && subtype >= 20000) {
			// return ajaxLstAct; // for temp remove to test
			// throw new CredentialException("Not login in
			// listActivitiesByActCodeAndSchCode when subtype>=20000 ");
		}

		SortOption so = SortOption.OrderByStarttime;

		if (sort == 2) {
			so = SortOption.OrderByHot;
		} else {

			if (subtype != 0) {
				so = SortOption.OrderByStarttime;
			} else {
				//default page
				so = SortOption.OrderByPubTime;
			}
		}

		if (subtype == 0) {
			subtype = type * 10000;
		}

		List<CommonActInfo> lstAct = commonActInfoDao.listCommonInfoByActAndSch(areacode, subtype, p, so, isExpired);
		for (CommonActInfo commonActInfo : lstAct) {
			ajaxLstAct.add(new ActInfoAjax(commonActInfo, userService.isFavoured(commonActInfo.getId())));
		}

		return ajaxLstAct;
	}

	@Transactional(readOnly = true)
	public ActDetailAjax getActivitiyDetail(int id) {

		ActDetailAjax actInfoAjax = null;

		CommonActInfo commonActInfo = commonActInfoDao.getCommonActInfo(id);
		if (commonActInfo != null) {
			boolean isFavoured = userService.isFavoured(id);
			actInfoAjax = new ActDetailAjax(commonActInfo, isFavoured);
		}

		return actInfoAjax;

	}

}
