package com.tongmeng.txyspring.dao;


import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tongmeng.txyspring.model.hibernate.Sliders;


@Repository
public class SlidersDao {

	@Autowired(required=true)
    private SessionFactory sessionFactory;
		
	public List<Sliders> listSliders()
	{
		Session session = sessionFactory.getCurrentSession();
		
		List<Sliders> sl_list = session.createCriteria(Sliders.class).list();
		return sl_list;
	}
	

	public void saveSliders(Sliders sliders)
	{
		Session session = sessionFactory.getCurrentSession();

		session.save(sliders);
	}
	
}
