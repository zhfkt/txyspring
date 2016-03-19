package com.tongmeng.txyspring.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tongmeng.txyspring.model.CommonActInfo;
import com.tongmeng.txyspring.model.UserActClt;
import com.tongmeng.txyspring.model.UserAll;


@Repository
public class UserActCltDao {

	@Autowired(required = true)
	private SessionFactory sessionFactory;

	
	public void removeFavoured(int actid,int userid) {		
		
		Session session = sessionFactory.getCurrentSession();
				
		Query query = session.createQuery("delete UserActClt as uac where uac.commonActInfo.id = :actid and uac.userAll.id = :userid");
		query.setParameter("actid", actid);
		query.setParameter("userid", userid);
		 
		query.executeUpdate();		
	}
	
	public boolean addFavour(int actid,int userid) {

		if(isFavoured(actid,userid)) 
		{
			return false;
		}
			
		Session session = sessionFactory.getCurrentSession();
	
		CommonActInfo commonActInfo = new CommonActInfo();
		commonActInfo.setId(actid);
				
		UserAll userAll = new UserAll();
		userAll.setId(userid);
			
		UserActClt userActClt = new UserActClt(commonActInfo,userAll); 
		session.save(userActClt);
		
		return true;
	}
	
	public boolean isFavoured(int actid,int userid) {
		
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(UserActClt.class)
				.createAlias("commonActInfo", "common")
				.createAlias("userAll", "user");

		criteria.add(Restrictions.eq("common.id", actid));
		criteria.add(Restrictions.eq("userAll.id", userid));		
		
		List<UserActClt> listUseractclt = (List<UserActClt>) criteria.list();
		
		if(listUseractclt.isEmpty())
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	
	public List<CommonActInfo> getFavorListByUser(int userid)
	{
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(UserActClt.class)
				.createAlias("userAll", "user");

		criteria.add(Restrictions.eq("user.id", userid));				
		List<UserActClt> listUseractclt = (List<UserActClt>) criteria.list();
		
		List<CommonActInfo> listCommonActInfo = new ArrayList<CommonActInfo>();
		if(listUseractclt.isEmpty())
		{
			return listCommonActInfo;
		}
		else
		{
			for(UserActClt useractclt : listUseractclt)
			{
				listCommonActInfo.add(useractclt.getCommonActInfo());
			}
		}
			
		return listCommonActInfo;
				
	}
	
	
}
