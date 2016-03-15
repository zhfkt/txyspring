package com.tongmeng.txyspring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tongmeng.txyspring.dao.CommonActInfoDao;
import com.tongmeng.txyspring.model.CommonActInfo;

@Service
public class ActivityService {

	
	@Autowired
	CommonActInfoDao caid;
	
	public List<CommonActInfo> listActivities(int type,int subtype,int sort,int p)
	{
		return caid.listCommonActInfo();		
	}
	
	public CommonActInfo getActivitiyDetail(int id)
	{
		return caid.getCommonActInfo(id);		
	}
}
