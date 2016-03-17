package com.tongmeng.txyspring.ajaxmodel;

import com.fasterxml.jackson.annotation.JsonView;
import com.tongmeng.txyspring.model.CommonActInfo;

public class ActInfoAjax {

	@JsonView(AjaxJsonViews.Public.class)
	private String title;	
	
	@JsonView(AjaxJsonViews.Public.class)
	private int id;
	
	@JsonView(AjaxJsonViews.Public.class)
	private String image;	

	@JsonView(AjaxJsonViews.Public.class)
	private String startTime;
	
	@JsonView(AjaxJsonViews.Public.class)
	private String endTime;
	
	@JsonView(AjaxJsonViews.Public.class)
	private String location;
	
	@JsonView(AjaxJsonViews.Public.class)
	private int personNum;
	
	@JsonView(AjaxJsonViews.Public.class)
	private int type;
	
	@JsonView(AjaxJsonViews.Public.class)
	private int subType;
	
	@JsonView(AjaxJsonViews.Public.class)
	private double hot;
	
	@JsonView(AjaxJsonViews.Public.class)
	private boolean isActive;
	
	@JsonView(AjaxJsonViews.Public.class)
	private String effective;
	
	@JsonView(AjaxJsonViews.Public.class)
	private String salary;
	
	@JsonView(AjaxJsonViews.Public.class)
	private String releaseTime;
	
	
	public ActInfoAjax(CommonActInfo commonActInfo)
	{
		this.title = commonActInfo.getTitle();
		this.id = commonActInfo.getId();
		this.image = commonActInfo.getCovImgUri();
		//startTime = commonActInfo.getStartDate();
		//endTime = commonActInfo.getEndDate();
		this.location = commonActInfo.getLocation();
		this.personNum = commonActInfo.getPeopleNumber();
		this.type = commonActInfo.getActCode().getId().getActType();
		this.subType = commonActInfo.getActCode().getId().getActSubtype();
		this.hot = 	commonActInfo.getHot();
		//isActive
		//private String effective;
		this.salary = commonActInfo.getSalary();
		//private String releaseTime;
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


	public String getImage() {
		return image;
	}


	public void setImage(String image) {
		this.image = image;
	}


	public String getStartTime() {
		return startTime;
	}


	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}


	public String getEndTime() {
		return endTime;
	}


	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}


	public String getLocation() {
		return location;
	}


	public void setLocation(String location) {
		this.location = location;
	}


	public int getPersonNum() {
		return personNum;
	}


	public void setPersonNum(int personNum) {
		this.personNum = personNum;
	}


	public int getType() {
		return type;
	}


	public void setType(int type) {
		this.type = type;
	}


	public int getSubType() {
		return subType;
	}


	public void setSubType(int subType) {
		this.subType = subType;
	}


	public double getHot() {
		return hot;
	}


	public void setHot(int hot) {
		this.hot = hot;
	}


	public boolean isActive() {
		return isActive;
	}


	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}


	public String getEffective() {
		return effective;
	}


	public void setEffective(String effective) {
		this.effective = effective;
	}


	public String getSalary() {
		return salary;
	}


	public void setSalary(String salary) {
		this.salary = salary;
	}


	public String getReleaseTime() {
		return releaseTime;
	}


	public void setReleaseTime(String releaseTime) {
		this.releaseTime = releaseTime;
	}
	
	
}
