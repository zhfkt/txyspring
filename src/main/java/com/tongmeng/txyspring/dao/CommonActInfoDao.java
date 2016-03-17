package com.tongmeng.txyspring.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tongmeng.txyspring.model.ActCode;
import com.tongmeng.txyspring.model.ActCodeId;
import com.tongmeng.txyspring.model.CommonActInfo;
import com.tongmeng.txyspring.model.SchCode;

@Repository
public class CommonActInfoDao {
	
	public enum SortOption
	{
		Default,OrderByStarttime,OrderByHot		
	}

	private final int pageSize = 10;
	
	
	@Autowired(required = true)
	private SessionFactory sessionFactory;

	@Transactional(readOnly = true)
	public List<CommonActInfo> listCommonActInfoByActCodeAndSchCode(int type,int subtype,int startPage,SortOption so) {
		
		Session session = sessionFactory.getCurrentSession();

		Criteria criteria = session.createCriteria(CommonActInfo.class);
		Criteria actcri = criteria.createCriteria( "actCode","act").createCriteria( "act.id","actid");
		actcri.add( Restrictions.eq( "actid.type", type));
		//actcri.add( Restrictions.eq( "actid.subtype", subtype));		
		
		
		/*
		switch(so)
		{
			case OrderByStarttime: 
				criteria.addOrder(Order.desc("startDate"));
				break;
			case OrderByHot:
				criteria.addOrder(Order.desc("hot"));
				break;
			default:
				
		}
		
		criteria.setFirstResult(startPage*pageSize);
		criteria.setMaxResults(pageSize);
		*/

		List<CommonActInfo> cai_list = criteria.list();
		return cai_list;
	}

	@Transactional(readOnly = true)
	public CommonActInfo getCommonActInfo(int id) {
		Session session = sessionFactory.getCurrentSession();

		CommonActInfo cai = (CommonActInfo) session.get(CommonActInfo.class, id);
		return cai;
	}

}
