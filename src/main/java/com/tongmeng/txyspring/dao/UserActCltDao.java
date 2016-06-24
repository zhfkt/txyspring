package com.tongmeng.txyspring.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tongmeng.txyspring.model.hibernate.CommonActInfo;
import com.tongmeng.txyspring.model.hibernate.UserActClt;
import com.tongmeng.txyspring.model.hibernate.UserAll;


@Repository
public class UserActCltDao {

	@Autowired(required = true)
	private SessionFactory sessionFactory;

	
	public boolean removeFavoured(int actid,int userid) {		
			
		Session session = sessionFactory.getCurrentSession();
				
		Query query = session.createQuery("delete UserActClt as uac where uac.commonActInfo.id = :actid and uac.userAll.id = :userid");
		query.setParameter("actid", actid);
		query.setParameter("userid", userid);
		 
		if(query.executeUpdate()!=0)
		{
			return true;
		}
		else
		{
			return false;
		}
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
		Criteria criteria = session.createCriteria(UserActClt.class);

		criteria.add(Restrictions.eq("commonActInfo.id", actid));
		criteria.add(Restrictions.eq("userAll.id", userid));		
		

		if(criteria.uniqueResult()==null)
		{
			return false;
		}
		else
		{
			return true;
		}
		
	}
	
	
	public List<CommonActInfo> getFavorListByUser(int userid,int subtype)
	{
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(UserActClt.class).createAlias("commonActInfo", "commonAct").
				setFetchMode("commonActInfo", FetchMode.JOIN).
				setFetchMode("commonActInfo.schCode", FetchMode.JOIN);
		// call lazy fetch to eager

		criteria.add(Restrictions.eq("userAll.id", userid));		
		criteria.add(Restrictions.ge("commonAct.actCode.id", subtype));
		criteria.add(Restrictions.lt("commonAct.actCode.id", subtype + 10000));		
		
		List<UserActClt> listUseractclt = criteria.list();
		
		List<CommonActInfo> listCommonActInfo = new ArrayList<CommonActInfo>();
		for(UserActClt useractclt : listUseractclt)
		{
			//Hibernate.initialize(useractclt.getCommonActInfo());
			listCommonActInfo.add(useractclt.getCommonActInfo());
		}
			
		return listCommonActInfo;
				
	}
	
	
}
