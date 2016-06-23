package com.tongmeng.txyspring.ajaxmodel;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonView;
import com.tongmeng.txyspring.model.CommonActImage;
import com.tongmeng.txyspring.model.CommonActInfo;

public class ActDetailAjax {
	
	// public

	@JsonView(AjaxJsonViews.Public.class)
	private String title;

	@JsonView(AjaxJsonViews.Public.class)
	private int id;

	@JsonView(AjaxJsonViews.Public.class)
	private String location;
	
	@JsonView(AjaxJsonViews.Public.class)
	private int isFavor;	
	
	@JsonView(AjaxJsonViews.Public.class)
	private String author;
	
	@JsonView(AjaxJsonViews.Public.class)
	private long startTime;

	@JsonView(AjaxJsonViews.Public.class)
	private long endTime;

	// GetDetail
	
	@JsonView(AjaxJsonViews.Public.class)
	private ArrayList<String> images;

	@JsonView(AjaxJsonViews.Public.class)
	private int favorNum;

	@JsonView(AjaxJsonViews.Public.class)
	String tel;

	@JsonView(AjaxJsonViews.Public.class)
	String email;

	@JsonView(AjaxJsonViews.Public.class)
	String qq;

	@JsonView(AjaxJsonViews.Public.class)
	String detail;

	@JsonView(AjaxJsonViews.Public.class)
	String more;
	
	public ActDetailAjax(CommonActInfo commonActInfo, boolean isFavoured) {

		// Public 
		this.title = commonActInfo.getTitle();
		this.id = commonActInfo.getId();
		this.location = commonActInfo.getSchCode().getDescription() + ": " + commonActInfo.getLocation();
		this.isFavor = isFavoured ? 1 : 0;
		this.author = commonActInfo.getOrganizer();
		this.startTime = commonActInfo.getStartDate().getTime();
		this.endTime = commonActInfo.getEndDate().getTime();
		
		// GetDetail	
		
		this.images = new ArrayList<String>();
		for(CommonActImage commonActImages: commonActInfo.getCommonActImages())
		{
			this.images.add(commonActImages.getImage());
		}
		
		this.favorNum = commonActInfo.getNumFavo();
		this.tel = commonActInfo.getCtPerTel();
		this.email = commonActInfo.getCtPerMail();
		this.qq = commonActInfo.getCtPerQq();
		this.detail = commonActInfo.getIntro();
		this.more = commonActInfo.getOutLink();
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

	public ArrayList<String> getImages() {
		return images;
	}

	public void setImages(ArrayList<String> images) {
		this.images = images;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getIsFavor() {
		return isFavor;
	}

	public void setIsFavor(int isFavor) {
		this.isFavor = isFavor;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long time) {
		this.startTime = time;
	}

	public long getEndTime() {
		return endTime;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}
	
	public int getFavorNum() {
		return favorNum;
	}

	public void setFavorNum(int favorNum) {
		this.favorNum = favorNum;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getMore() {
		return more;
	}

	public void setMore(String more) {
		this.more = more;
	}
}
