package com.tongmeng.txyspring.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tongmeng.txyspring.model.CommonActInfo;

@Repository
public class CommonActInfoDao {

	public enum SortOption {
		OrderByStarttime, OrderByHot
	}

	private final int pageSize = 10;

	@Autowired(required = true)
	private SessionFactory sessionFactory;

	public List<CommonActInfo> listCommonInfoByActAndSch(int areacode, int subtype, int startPage,
			SortOption so) {

		Session session = sessionFactory.getCurrentSession();

		Criteria criteria = session.createCriteria(CommonActInfo.class);

		criteria.setFetchMode("schCode", FetchMode.JOIN);
		
		if (subtype % 10000 == 0) {
			criteria.add(Restrictions.ge("actCode.id", subtype));
			criteria.add(Restrictions.lt("actCode.id", subtype + 10000));

		} else {
			criteria.add(Restrictions.eq("actCode.id", subtype));
		}

		if (areacode != 0) {

			if (areacode % 10000 == 0) {
				criteria.add(Restrictions.ge("schCode.id", areacode));
				criteria.add(Restrictions.lt("schCode。id", areacode + 10000));
			} else {
				criteria.add(Restrictions.eq("schCode.id", areacode));
			}
		}

		switch (so) {
		case OrderByStarttime:
			criteria.addOrder(Order.desc("startDate"));
			break;
		case OrderByHot:
			criteria.addOrder(Order.desc("hot"));
			break;
		default:
			break;
		}

		criteria.setFirstResult(startPage * pageSize);
		criteria.setMaxResults(pageSize);

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
