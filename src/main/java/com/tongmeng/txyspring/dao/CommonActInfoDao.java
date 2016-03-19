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

@Repository
public class CommonActInfoDao {

	public enum SortOption {
		Default, OrderByStarttime, OrderByHot
	}

	private final int pageSize = 10;

	@Autowired(required = true)
	private SessionFactory sessionFactory;

	public List<CommonActInfo> listCommonInfoByActAndSch(int areacode, int subtype, int startPage, SortOption so) {

		Session session = sessionFactory.getCurrentSession();

		Criteria criteria = session.createCriteria(CommonActInfo.class).createAlias("actCode", "act")
				.createAlias("schCode", "area");

		if (areacode != 0) {
			if (areacode % 10000 == 0) {

				int schcode = subtype / 10000;
				criteria.add(Restrictions.ge("area.areaCode", schcode));
				criteria.add(Restrictions.lt("area.areaCode", schcode + 1));
			} else {
				criteria.add(Restrictions.eq("area.areaCode", areacode));
			}
		}

		if (subtype % 10000 == 0) {
			criteria.add(Restrictions.ge("act.actSubtype", subtype));
			criteria.add(Restrictions.lt("act.actSubtype", subtype + 10000));

		} else {
			criteria.add(Restrictions.eq("act.actSubtype", subtype));
		}

		switch (so) {
		case OrderByStarttime:
			criteria.addOrder(Order.desc("startDate"));
			break;
		case OrderByHot:
			criteria.addOrder(Order.desc("hot"));
			break;
		default:

		}

		criteria.setFirstResult(startPage * pageSize);
		criteria.setMaxResults(pageSize);

		List<CommonActInfo> cai_list = criteria.list();
		return cai_list;
	}

	public CommonActInfo getCommonActInfo(int id) {
		Session session = sessionFactory.getCurrentSession();

		CommonActInfo commonActInfo = (CommonActInfo) session.get(CommonActInfo.class, id);
		return commonActInfo;
	}

	public void addFavour(int id) {
		Session session = sessionFactory.getCurrentSession();

		CommonActInfo commonActInfo = (CommonActInfo) session.get(CommonActInfo.class, id);

		if (commonActInfo == null) {
			return;
		} else {
			commonActInfo.setNumFavo(commonActInfo.getNumFavo() + 1);
			return;
		}
	}

	
	public void minusFavour(int id) {
		Session session = sessionFactory.getCurrentSession();

		CommonActInfo commonActInfo = (CommonActInfo) session.get(CommonActInfo.class, id);

		if (commonActInfo == null) {
			return;
		} else {
			int nowFavo = commonActInfo.getNumFavo();
			if (nowFavo == 0) {
				return; // for caution
			}
			commonActInfo.setNumFavo(nowFavo - 1);
			return;
		}
	}

}
