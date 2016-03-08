package com.tongmeng.txyspring.dao;


import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.springframework.stereotype.Repository;

import com.tongmeng.txyspring.model.Sliders;


@Repository
public class SlidersDao {

	private Session session;
	
	public SlidersDao()
	{		
		Configuration configuration = new Configuration().configure();
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		session = sessionFactory.openSession();
	}
	
	public List<Sliders> listSliders()
	{
		List<Sliders> sl_list = session.createCriteria(Sliders.class).list();
		return sl_list;
	}
	
}
