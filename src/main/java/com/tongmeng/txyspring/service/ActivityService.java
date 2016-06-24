package com.tongmeng.txyspring.service;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.login.CredentialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tongmeng.txyspring.dao.CommonActInfoDao;
import com.tongmeng.txyspring.service.identity.UserInfoSession;
import com.tongmeng.txyspring.dao.CommonActInfoDao.SortOption;
import com.tongmeng.txyspring.model.ajax.ActDetailAjax;
import com.tongmeng.txyspring.model.ajax.ActInfoAjax;
import com.tongmeng.txyspring.model.hibernate.CommonActInfo;

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
		
		if (subtype == 0) {
			subtype = type * 10000;
		}
		
		if (!userInfoSession.isLogined() && subtype >= 20000) {
			// return ajaxLstAct; // for temp remove to test
			// throw new CredentialException("Not login in
			// listActivitiesByActCodeAndSchCode when subtype>=20000 ");
		}
		
		SortOption so = SortOption.NoOrder;

		if (sort == 2) {
			so = SortOption.OrderByHot;
		} 
		else //sort == 1 || sort == 0 || else
		{
			if (subtype < 20000 && subtype >= 10000 ) {
				
				
				if(isExpired)
				{
					so = SortOption.OrderByEndTime;
				}
				else
				{					
					so = SortOption.OrderByStartCombineCurrentTime;
				}
			} else if(subtype >= 20000){
				
				so = SortOption.OrderByPubTime;
				
			}
			
		}
				
		List<CommonActInfo> lstAct = commonActInfoDao.listCommonInfoByActAndSch(areacode, subtype, p, so, isExpired);
		
		if(so == SortOption.OrderByStartCombineCurrentTime && p==0)
		{
			List<CommonActInfo> lstTodayAct = commonActInfoDao.listTodayCommonInfoByActAndSch(areacode, subtype);				

			for (CommonActInfo commonActInfo : lstTodayAct) {
				boolean isFavoured = userService.isFavoured(commonActInfo.getId());
				ajaxLstAct.add(new ActInfoAjax(commonActInfo, isFavoured));
			}				
			
			for (CommonActInfo commonActInfo : lstAct) {
				
				if(!lstTodayAct.contains(commonActInfo))
				{
					boolean isFavoured = userService.isFavoured(commonActInfo.getId());
					ajaxLstAct.add(new ActInfoAjax(commonActInfo, isFavoured));
				}	
			}
		}
		else
		{
			for (CommonActInfo commonActInfo : lstAct) {
				boolean isFavoured = userService.isFavoured(commonActInfo.getId());
				ajaxLstAct.add(new ActInfoAjax(commonActInfo, isFavoured));
			}
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
