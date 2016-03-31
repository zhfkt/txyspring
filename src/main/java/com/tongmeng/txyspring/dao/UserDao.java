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

import com.tongmeng.txyspring.model.UserAll;

@Repository
public class UserDao {

	@Autowired(required = true)
	private SessionFactory sessionFactory;

	
	public UserAll selectUser(int actid,int userid) {		
			
		return null;
	}
	
	public void insertUser(int actid,int userid) {


	}
	

	
}
