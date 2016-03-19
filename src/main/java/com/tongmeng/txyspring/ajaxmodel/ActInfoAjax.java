package com.tongmeng.txyspring.ajaxmodel;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonView;
import com.tongmeng.txyspring.model.CommonActInfo;
import com.tongmeng.txyspring.model.UserActClt;

import org.joda.time.Days;
import org.joda.time.DateTime;

public class ActInfoAjax {

	// public

	@JsonView(AjaxJsonViews.Public.class)
	private String title;

	@JsonView(AjaxJsonViews.Public.class)
	private int id;

	@JsonView(AjaxJsonViews.Public.class)
	private String image;

	@JsonView(AjaxJsonViews.Public.class)
	private String location;

	// GetActivities

	@JsonView(AjaxJsonViews.ActInfo.class)
	private long startTime;

	@JsonView(AjaxJsonViews.ActInfo.class)
	private long endTime;

	@JsonView(AjaxJsonViews.ActInfo.class)
	private int personNum;

	@JsonView(AjaxJsonViews.ActInfo.class)
	private int type;

	@JsonView(AjaxJsonViews.ActInfo.class)
	private int subType;

	@JsonView(AjaxJsonViews.ActInfo.class)
	private int hot;

	@JsonView(AjaxJsonViews.ActInfo.class)
	private int isActive;

	@JsonView(AjaxJsonViews.ActInfo.class)
	private String effective;

	@JsonView(AjaxJsonViews.ActInfo.class)
	private String salary;

	@JsonView(AjaxJsonViews.ActInfo.class)
	private String releaseTime;

	// GetDetail

	@JsonView(AjaxJsonViews.ActDetail.class)
	private long time;

	@JsonView(AjaxJsonViews.ActDetail.class)
	private int isFavor;

	@JsonView(AjaxJsonViews.ActDetail.class)
	private int favorNum;

	@JsonView(AjaxJsonViews.ActDetail.class)
	private String author;

	@JsonView(AjaxJsonViews.ActDetail.class)
	String tel;

	@JsonView(AjaxJsonViews.ActDetail.class)
	String email;

	@JsonView(AjaxJsonViews.ActDetail.class)
	String qq;

	@JsonView(AjaxJsonViews.ActDetail.class)
	String detail;

	@JsonView(AjaxJsonViews.ActDetail.class)
	String more;

	public ActInfoAjax(CommonActInfo commonActInfo, boolean isFavoured) {

		// GetDetail

		this.title = commonActInfo.getTitle();
		this.id = commonActInfo.getId();
		this.image = commonActInfo.getCovImgUri();
		this.location = commonActInfo.getSchCode().getDescription() + ": " + commonActInfo.getLocation();

		this.time = commonActInfo.getStartDate().getTime();
		this.isFavor = isFavoured ? 1 : 0;
		this.favorNum = commonActInfo.getNumFavo();
		this.author = commonActInfo.getOrganizer();
		this.tel = commonActInfo.getCtPerTel();
		this.email = commonActInfo.getCtPerMail();
		this.qq = commonActInfo.getCtPerQq();
		this.detail = commonActInfo.getIntro();
		this.more = commonActInfo.getOutLink();
	}

	public ActInfoAjax(CommonActInfo commonActInfo) {

		// GetActivities

		this.title = commonActInfo.getTitle();
		this.id = commonActInfo.getId();
		this.image = commonActInfo.getCovImgUri();
		this.startTime = commonActInfo.getStartDate().getTime();
		this.endTime = commonActInfo.getEndDate().getTime();
		this.location = commonActInfo.getSchCode().getDescription() + ": " + commonActInfo.getLocation();
		this.personNum = commonActInfo.getPeopleNumber();
		this.subType = commonActInfo.getActCode().getActSubtype();
		this.type = this.subType / 10000;
		this.hot = commonActInfo.getHot() > 10 ? 1 : 0;
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

		this.salary = commonActInfo.getSalary();

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
