package com.tongmeng.txyspring.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tongmeng.txyspring.model.CommonActImage;
import com.tongmeng.txyspring.model.CommonActInfo;
import com.tongmeng.txyspring.model.JobExtraInfo;

@Repository
public class CommonActInfoDao {

	public enum SortOption {
		NoOrder, OrderByStartCombineCurrentTime, OrderByHot, OrderByPubTime, OrderByEndTime
	}

	private final int pageSize = 10;

	@Autowired(required = true)
	private SessionFactory sessionFactory;

	public List<CommonActInfo> listCommonInfoByActAndSch(int areacode, int subtype, int startPage,
			SortOption so, boolean isExpired) {

		Session session = sessionFactory.getCurrentSession();

		Criteria criteria = session.createCriteria(CommonActInfo.class);

		criteria.createCriteria("jobExtraInfo", JoinType.LEFT_OUTER_JOIN);
		criteria.createCriteria("schCode", JoinType.INNER_JOIN);
		//criteria.setFetchMode("schCode", FetchMode.JOIN).setFetchMode("", FetchMode.EAGER);
		
		if (subtype % 10000 == 0) {
			criteria.add(Restrictions.ge("actCode.id", subtype));
			criteria.add(Restrictions.lt("actCode.id", subtype + 10000));

		} else {
			criteria.add(Restrictions.eq("actCode.id", subtype));
		}

		if (areacode != 0) {

			if (areacode % 10000 == 0) {
				criteria.add(Restrictions.ge("schCode.id", areacode));
				criteria.add(Restrictions.lt("schCode.id", areacode + 10000));
			} else {
				criteria.add(Restrictions.eq("schCode.id", areacode));
			}
		}

		switch (so) {
		case OrderByStartCombineCurrentTime:
			criteria.addOrder(Order.desc("startDate"));
			break;
		case OrderByHot:
			criteria.addOrder(Order.desc("hot"));
			break;
		case OrderByPubTime:
			criteria.addOrder(Order.desc("pubTime"));
			break;
		case OrderByEndTime:
			criteria.addOrder(Order.desc("endDate"));
			break;	
		case NoOrder:
			break;
		default:
			break;
		}
		
		if(isExpired)
		{
			criteria.add(Restrictions.lt("endDate", new Date()));
		}
		else
		{
			criteria.add(Restrictions.ge("endDate", new Date()));
		}
		

		criteria.setFirstResult(startPage * pageSize);
		criteria.setMaxResults(pageSize);

		List<CommonActInfo> cai_list = criteria.list();
		
		return cai_list;
	}
	
	public List<CommonActInfo> listTodayCommonInfoByActAndSch(int areacode, int subtype) {

		Session session = sessionFactory.getCurrentSession();

		Criteria criteria = session.createCriteria(CommonActInfo.class);

		criteria.createCriteria("jobExtraInfo", JoinType.LEFT_OUTER_JOIN);
		criteria.createCriteria("schCode", JoinType.INNER_JOIN);
	
		if (subtype % 10000 == 0) {
			criteria.add(Restrictions.ge("actCode.id", subtype));
			criteria.add(Restrictions.lt("actCode.id", subtype + 10000));

		} else {
			criteria.add(Restrictions.eq("actCode.id", subtype));
		}

		if (areacode != 0) {

			if (areacode % 10000 == 0) {
				criteria.add(Restrictions.ge("schCode.id", areacode));
				criteria.add(Restrictions.lt("schCode.id", areacode + 10000));
			} else {
				criteria.add(Restrictions.eq("schCode.id", areacode));
			}
		}

		criteria.addOrder(Order.desc("startDate"));
		
		Date todayEnd = DateUtils.addMilliseconds(DateUtils.ceiling(new Date(), Calendar.DATE), -1);
		Date todayStart =  DateUtils.truncate(new Date(), Calendar.DATE);
		criteria.add(Restrictions.lt("startDate", todayEnd));
		criteria.add(Restrictions.ge("startDate", todayStart));
		
		List<CommonActInfo> cai_list = criteria.list();
		
		return cai_list;
	}	

	public CommonActInfo getCommonActInfo(int id) {
		Session session = sessionFactory.getCurrentSession();

		CommonActInfo commonActInfo = (CommonActInfo) session.get(CommonActInfo.class, id);
		
		if(commonActInfo==null)
		{
			return null;
		}
		
		Hibernate.initialize(commonActInfo.getSchCode());
		Hibernate.initialize(commonActInfo.getCommonActImages());

		return commonActInfo;
	}
	
	public void saveCommonActInfo(CommonActInfo commonActInfo) {

		Session session = sessionFactory.getCurrentSession();

		session.save(commonActInfo);
		
	}	
	
	public void saveCommonActInfo(CommonActImage commonActImage ) {

		Session session = sessionFactory.getCurrentSession();

		session.save(commonActImage);
		
	}	
	
	public void saveJobExtraInfo(JobExtraInfo jobExtraInfo)
	{
		Session session = sessionFactory.getCurrentSession();

		session.save(jobExtraInfo);
	}

	public void addFavour(int id) {
		Session session = sessionFactory.getCurrentSession();

		Query query = session.createQuery("update CommonActInfo as commonActInfo set commonActInfo.numFavo = commonActInfo.numFavo + 1 "
				+ "where commonActInfo.id = :commonActInfoId");
		query.setParameter("commonActInfoId", id);
		query.executeUpdate();		

	}

	public void minusFavour(int id) {
		
		Session session = sessionFactory.getCurrentSession();

		Query query = session.createQuery("update CommonActInfo as commonActInfo set commonActInfo.numFavo = commonActInfo.numFavo - 1 "
				+ "where commonActInfo.numFavo > 0 and commonActInfo.id = :commonActInfoId");
		query.setParameter("commonActInfoId", id);
		query.executeUpdate();
		
		
	}

}
