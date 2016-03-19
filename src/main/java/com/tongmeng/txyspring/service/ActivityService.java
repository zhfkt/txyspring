package com.tongmeng.txyspring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import com.tongmeng.txyspring.dao.CommonActInfoDao;
import com.tongmeng.txyspring.model.CommonActInfo;
import com.tongmeng.txyspring.dao.CommonActInfoDao.SortOption;
import com.tongmeng.txyspring.dao.UserActCltDao;;

@Service
public class ActivityService {

	@Autowired
	private CommonActInfoDao commonActInfoDao;

	@Transactional(readOnly = true)
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

	@Transactional(readOnly = true)
	public CommonActInfo getActivitiyDetail(int id) {
		return commonActInfoDao.getCommonActInfo(id);
	}

	
	
	
	
}


