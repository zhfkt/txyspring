package com.tongmeng.txyspring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tongmeng.txyspring.dao.CommonActInfoDao;
import com.tongmeng.txyspring.model.CommonActInfo;
import com.tongmeng.txyspring.dao.CommonActInfoDao.SortOption;
import com.tongmeng.txyspring.dao.UserActCltDao;;

@Service
public class ActivityService {

	@Autowired
	CommonActInfoDao commonActInfoDao;
	
	@Autowired
	private UserActCltDao userActCltDao;

	public List<CommonActInfo> listActivitiesByActCodeAndSchCode(int areacode, int subtype, int sort, int p) {

		SortOption so;

		if (sort == 1) {
			so = SortOption.OrderByStarttime;
		} else if (sort == 2) {
			so = SortOption.OrderByHot;
		} else {
			so = SortOption.Default;
		}

		return commonActInfoDao.listCommonInfoByActAndSch(areacode, subtype, p, so);
	}

	public CommonActInfo getActivitiyDetail(int id) {
		return commonActInfoDao.getCommonActInfo(id);
	}
	
	
	public boolean isFavoured(int id) {
		int userid = 1;
		return userActCltDao.isFavoured(id, userid);
	}
	
	
	
}


