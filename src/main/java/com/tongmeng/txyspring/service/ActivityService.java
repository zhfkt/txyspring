package com.tongmeng.txyspring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tongmeng.txyspring.dao.CommonActInfoDao;
import com.tongmeng.txyspring.model.CommonActInfo;
import com.tongmeng.txyspring.dao.CommonActInfoDao.SortOption;;

@Service
public class ActivityService {

	@Autowired
	CommonActInfoDao caid;

	public List<CommonActInfo> listActivitiesByActCodeAndSchCode(int type, int subtype, int sort, int p) {

		SortOption so;

		if (sort == 1) {
			so = SortOption.OrderByStarttime;
		} else if (sort == 2) {
			so = SortOption.OrderByHot;
		} else {
			so = SortOption.Default;
		}

		return caid.listCommonActInfoByActCodeAndSchCode(type, subtype, p, so);
	}

	public CommonActInfo getActivitiyDetail(int id) {
		return caid.getCommonActInfo(id);
	}
}
