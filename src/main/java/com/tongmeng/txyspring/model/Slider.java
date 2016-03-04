package com.tongmeng.txyspring.model;

import java.net.URI;

import com.fasterxml.jackson.annotation.JsonView;
import com.tongmeng.txyspring.ajaxmodel.AjaxJsonViews;

public class Slider {
	
	@JsonView(AjaxJsonViews.Public.class)
	String title;
	
	@JsonView(AjaxJsonViews.Public.class)
	int id; 
	
	@JsonView(AjaxJsonViews.Public.class)
	URI image;
	
	public Slider(String title,int id,URI image)
	{
		setTitle(title);
		setId(id);
		setImage(image);
		
		
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public URI getImage() {
		return image;
	}


	public void setImage(URI image) {
		this.image = image;
	}

}
