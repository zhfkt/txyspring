package com.tongmeng.txyspring.ajaxmodel;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonView;
import com.tongmeng.txyspring.model.CommonActInfo;

import org.joda.time.Days;
import org.joda.time.DateTime;

public class ActInfoAjax {

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

	// GetActivities
	
	@JsonView(AjaxJsonViews.Public.class)
	private String image;

	@JsonView(AjaxJsonViews.Public.class)
	private int personNum;

	@JsonView(AjaxJsonViews.Public.class)
	private int type;

	@JsonView(AjaxJsonViews.Public.class)
	private int subType;

	@JsonView(AjaxJsonViews.Public.class)
	private int hot;
	
	@JsonView(AjaxJsonViews.Public.class)
	private int needOrder;

	@JsonView(AjaxJsonViews.Public.class)
	private int isActive;

	@JsonView(AjaxJsonViews.Public.class)
	private String effective;

	@JsonView(AjaxJsonViews.Public.class)
	private String releaseTime;
	
	@JsonView(AjaxJsonViews.Public.class)
	private String info;
	
	public ActInfoAjax(CommonActInfo commonActInfo, boolean isFavoured) {

		// Public 
		this.title = commonActInfo.getTitle();
		this.id = commonActInfo.getId();
		this.image = commonActInfo.getCovImgUri();
		this.location = commonActInfo.getSchCode().getDescription() + ": " + commonActInfo.getLocation();
		this.isFavor = isFavoured ? 1 : 0;
		this.author = commonActInfo.getOrganizer();
		this.startTime = commonActInfo.getStartDate().getTime();
		this.endTime = commonActInfo.getEndDate().getTime();
		
		final int hotThreshold = 300;
		
		// GetActivities
		this.personNum = commonActInfo.getPeopleNumber();
		this.subType = commonActInfo.getActCode().getActSubtype();
		this.type = this.subType / 10000;
		this.hot = commonActInfo.getHot() > hotThreshold ? 1 : 0;
		this.needOrder = commonActInfo.getIsReversed();
		this.isActive = this.endTime > new Date().getTime() ? 1 : 0;

		if (isActive == 0) {
			this.effective = "已过期";
		} else {
			Days toEndTime = Days.daysBetween(new DateTime(new Date()), new DateTime(commonActInfo.getEndDate()));
			int left_days = toEndTime.getDays();

			if (left_days > 365) {
				this.effective = "长期";
			} else {
				this.effective = "还剩" + left_days + "天";
			}
		}

		if(commonActInfo.getJobExtraInfo()==null)
		{
			this.info = "";
		}
		else
		{
			this.info = commonActInfo.getJobExtraInfo().getJobInfo();
		}

		Days toStartTime = Days.daysBetween(new DateTime(commonActInfo.getPubTime()), new DateTime(new Date()));
		int releasedays = toStartTime.getDays();
		if (releasedays < 1) {
			this.releaseTime = "刚刚";
		} else {
			this.releaseTime = "于" + releasedays + "天前发布";
		}
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

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public long getEndTime() {
		return endTime;
	}

	public void setEndTime(long endTime) {
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

	public int getHot() {
		return hot;
	}

	public void setHot(int hot) {
		this.hot = hot;
	}

	public int isActive() {
		return isActive;
	}

	public void setActive(int isActive) {
		this.isActive = isActive;
	}

	public String getEffective() {
		return effective;
	}

	public void setEffective(String effective) {
		this.effective = effective;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getReleaseTime() {
		return releaseTime;
	}

	public void setReleaseTime(String releaseTime) {
		this.releaseTime = releaseTime;
	}

}
