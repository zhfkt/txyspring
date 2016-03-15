package com.tongmeng.txyspring.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tongmeng.txyspring.model.CommonActInfo;

@Repository
public class CommonActInfoDao {

	@Autowired(required = true)
	private SessionFactory sessionFactory;

	public CommonActInfoDao() {
	}

	@Transactional(readOnly = true)
	public List<CommonActInfo> listCommonActInfo() {
		Session session = sessionFactory.getCurrentSession();

		List<CommonActInfo> cai_list = (List<CommonActInfo>) session.createCriteria(CommonActInfo.class).list();
		return cai_list;
	}

	@Transactional(readOnly = true)
	public CommonActInfo getCommonActInfo(int id) {
		Session session = sessionFactory.getCurrentSession();

		CommonActInfo cai = (CommonActInfo) session.get(CommonActInfo.class, id);
		return cai;
	}

}
