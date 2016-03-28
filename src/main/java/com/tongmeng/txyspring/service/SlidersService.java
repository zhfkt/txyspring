package com.tongmeng.txyspring.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tongmeng.txyspring.ajaxmodel.SlidersAjax;
import com.tongmeng.txyspring.dao.SlidersDao;
import com.tongmeng.txyspring.model.Sliders;

@Service
public class SlidersService {

	
	@Autowired
	private SlidersDao slidersDao;
	
	
	@Transactional(readOnly = true)
	public ArrayList<SlidersAjax> listSliders() {
		
		ArrayList<SlidersAjax> slidersAjaxList = new ArrayList<SlidersAjax>();
		
		List<Sliders> sliderDaoList = slidersDao.listSliders();
		for(Sliders sliders: sliderDaoList)
		{
			slidersAjaxList.add(new SlidersAjax(sliders));
		}
		
		
		return slidersAjaxList;
	}
		
}
