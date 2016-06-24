package com.tongmeng.txyspring.model.web;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class BackendCommonActInfo {

	private Integer actsubtype;
	private Integer campus;
	private String title;
	private String startDate;
	private String startTime;
	private String endDate;
	private String endTime;
	private String location;
	private Integer personNum;
	private String detail;
	private String tel;
	private String email;
	private String qq;
	private String author;
	private Integer needOrder;
	private List<MultipartFile> pictures;
	private MultipartFile coverImg;
	private String info;
	
	
	public Integer getActsubtype() {
		return actsubtype;
	}
	public void setActsubtype(Integer actsubtype) {
		this.actsubtype = actsubtype;
	}
	public Integer getCampus() {
		return campus;
	}
	public void setCampus(Integer campus) {
		this.campus = campus;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
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
	public Integer getPersonNum() {
		return personNum;
	}
	public void setPersonNum(Integer personNum) {
		this.personNum = personNum;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
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
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Integer getNeedOrder() {
		return needOrder;
	}
	public void setNeedOrder(Integer needOrder) {
		this.needOrder = needOrder;
	}
	public List<MultipartFile> getPictures() {
		return pictures;
	}
	public void setPictures(List<MultipartFile> pictures) {
		this.pictures = pictures;
	}
	public MultipartFile getCoverImg() {
		return coverImg;
	}
	public void setCoverImg(MultipartFile coverImg) {
		this.coverImg = coverImg;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	
}
