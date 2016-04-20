package com.tongmeng.txyspring.dao;

import org.hibernate.Criteria;
import org.hibernate.Query;
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

	public UserAll insertUserByOriId(int areaCode, String oriId) {
				
		Session session = sessionFactory.getCurrentSession();

		SchCode schCode = new SchCode(areaCode);
		UserAll userAll = new UserAll(schCode, oriId);			
		session.save(userAll);
		
		return userAll;
	}
	
	public void modifyUserName(String username,int userId)
	{
		Session session = sessionFactory.getCurrentSession();
		
		Query query = session.createQuery("update UserAll as userAll set userAll.nickName = :username "
				+ "where userAll.id = :userid");
		query.setParameter("username", username);
		query.setParameter("userid", userId);
		if(query.executeUpdate()!=1)
		{
			throw new IllegalArgumentException("Inconsistent update result with userId: " + userId);
		}		
	}
	

}
