package com.tongmeng.txyspring.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tongmeng.txyspring.model.SchCode;
import com.tongmeng.txyspring.model.UserAll;

@Repository
public class UserDao {

	@Autowired(required = true)
	private SessionFactory sessionFactory;

	public UserAll selectUserByOriId(int areaCode, String oriId) {

		areaCode = (areaCode / 10000) * 10000;

		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(UserAll.class);

		criteria.add(Restrictions.ge("schCode.id", areaCode));
		criteria.add(Restrictions.lt("schCode.id", areaCode + 10000));
		criteria.add(Restrictions.eq("oriId", oriId));
		
		UserAll user = (UserAll)criteria.uniqueResult();
		return user;
	}

	public UserAll insertOrSelectUser(int areaCode, String oriId) {
		
		UserAll user = selectUserByOriId(areaCode, oriId);
		
		if(user != null)
		{
			return user;
		}
		
		Session session = sessionFactory.getCurrentSession();

		UserAll userAll = new UserAll(new SchCode(areaCode), oriId);			
		session.save(userAll);
		
		return userAll;
	}

}
