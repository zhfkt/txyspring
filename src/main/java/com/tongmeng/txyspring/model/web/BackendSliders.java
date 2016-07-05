package com.tongmeng.txyspring.model.web;

import org.springframework.web.multipart.MultipartFile;

public class BackendSliders {
	
	private Integer id;
	private String link;
	private MultipartFile pictures;
	private String title;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public MultipartFile getPictures() {
		return pictures;
	}
	public void setPictures(MultipartFile pictures) {
		this.pictures = pictures;
	}

	
}
