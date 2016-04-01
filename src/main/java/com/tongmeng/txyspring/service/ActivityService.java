package com.tongmeng.txyspring.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tongmeng.txyspring.ajaxmodel.ActDetailAjax;
import com.tongmeng.txyspring.ajaxmodel.ActInfoAjax;
import com.tongmeng.txyspring.dao.CommonActInfoDao;
import com.tongmeng.txyspring.model.CommonActInfo;
import com.tongmeng.txyspring.dao.CommonActInfoDao.SortOption;

@Service
public class ActivityService {

	@Autowired
	private CommonActInfoDao commonActInfoDao;

	@Autowired
	private UserService us;

	@Transactional(readOnly = true)
	public List<ActInfoAjax> listActivitiesByActCodeAndSchCode(int areacode, int subtype, int sort, int p) {

		SortOption so = SortOption.OrderByStarttime;

		if (sort == 2) {
			so = SortOption.OrderByHot;
		} else {
			so = SortOption.OrderByStarttime;
		}

		List<CommonActInfo> lstAct = commonActInfoDao.listCommonInfoByActAndSch(areacode, subtype, p, so);

		List<ActInfoAjax> ajaxLstAct = new ArrayList<ActInfoAjax>();
		for (CommonActInfo commonActInfo : lstAct) {
			ajaxLstAct.add(new ActInfoAjax(commonActInfo, us.isFavoured(commonActInfo.getId())));
		}

		return ajaxLstAct;
	}

	@Transactional(readOnly = true)
	public ActDetailAjax getActivitiyDetail(int id) {

		ActDetailAjax actInfoAjax = null;

		CommonActInfo commonActInfo = commonActInfoDao.getCommonActInfo(id);
		if (commonActInfo != null) {
			boolean isFavoured = us.isFavoured(id);
			actInfoAjax = new ActDetailAjax(commonActInfo, isFavoured);
		}

		return actInfoAjax;

	}

}
