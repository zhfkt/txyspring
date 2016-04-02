package com.tongmeng.txyspring.ajaxmodel;

import com.fasterxml.jackson.annotation.JsonView;
import com.tongmeng.txyspring.model.Sliders;

public class SlidersAjax {
	
	@JsonView(AjaxJsonViews.Public.class)
	private String title;
	
	@JsonView(AjaxJsonViews.Public.class)
	private String imagePath;
	
	@JsonView(AjaxJsonViews.Public.class)
	private String link;
	
	@JsonView(AjaxJsonViews.Public.class)
	private Integer actId;

	public SlidersAjax(Sliders sliders) {
		this.title = sliders.getTitle();
		this.imagePath = sliders.getImagePath();
		this.link = sliders.getLink();
		
		if(sliders.getCommonActInfo()!=null)
		{
			this.actId = sliders.getCommonActInfo().getId();
		}
		
		
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImagePath() {
		return this.imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getLink() {
		return this.link;
	}

	public void setLink(String link) {
		this.link = link;
	}
	
}
