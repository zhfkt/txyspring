package com.tongmeng.txyspring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tongmeng.txyspring.dao.CommonActInfoDao;
import com.tongmeng.txyspring.dao.UserActCltDao;

@Service
public class UserService {
	
	
	@Autowired
	private CommonActInfoDao commonActInfoDao;
	
	@Autowired
	private UserActCltDao userActCltDao;
	
	@Transactional(readOnly = true)
	public boolean isFavoured(int id) {
		int userid = 1;
		return userActCltDao.isFavoured(id, userid);
	}
	
	@Transactional
	public void changeFavour(int id,String action)
	{
		int userid = 1;
		
		if(action.equals("add"))
		{		
			if(!userActCltDao.addFavour(id, userid))
			{
				return;
			}
			commonActInfoDao.addFavour(id);			
		}
		else if(action.equals("remove"))
		{
			if(!userActCltDao.isFavoured(id, userid))
			{
				return;
			}
			
			userActCltDao.removeFavoured(id,userid);
			commonActInfoDao.minusFavour(id);
			
		}
		
		
	}

}
