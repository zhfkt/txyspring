package com.tongmeng.txyspring.dao;


import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tongmeng.txyspring.model.Sliders;


@Repository
public class SlidersDao {

	@Autowired(required=true)
    private SessionFactory sessionFactory;
		
	
	@Transactional(readOnly=true)
	public List<Sliders> listSliders()
	{
		Session session = sessionFactory.getCurrentSession();
		
		List<Sliders> sl_list = (List<Sliders>) session.createCriteria(Sliders.class).list();
		return sl_list;
	}
	
}
