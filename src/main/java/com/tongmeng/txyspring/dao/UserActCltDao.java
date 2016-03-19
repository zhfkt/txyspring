package com.tongmeng.txyspring.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tongmeng.txyspring.model.CommonActInfo;
import com.tongmeng.txyspring.model.UserActClt;


@Repository
public class UserActCltDao {

	@Autowired(required = true)
	private SessionFactory sessionFactory;

	@Transactional(readOnly = true)
	public boolean isFavoured(int actid,int userid) {
		Session session = sessionFactory.getCurrentSession();

		
		Criteria criteria = session.createCriteria(UserActClt.class)
				.createAlias("commonActInfo", "common")
				.createAlias("userAll", "user");

		criteria.add(Restrictions.eq("common.id", actid));
		criteria.add(Restrictions.eq("userAll.id", userid));		
		
		List<UserActClt> listUseractclt = (List<UserActClt>) criteria.list();
		
		if(listUseractclt.size()==0)
		{
			return false;
		}
		else
		{
			return true;
		}
		
					
	}
	
}
