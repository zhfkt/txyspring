package com.tongmeng.txyspring.model;
// Generated 2016-4-2 14:20:28 by Hibernate Tools 4.3.1.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * SchCode generated by hbm2java
 */
@Entity
@Table(name = "sch_code", catalog = "txyspring")
public class SchCode implements java.io.Serializable {

	private int areaCode;
	private String description;
	private Set<CommonActInfo> commonActInfos = new HashSet<CommonActInfo>(0);
	private Set<UserAll> userAlls = new HashSet<UserAll>(0);

	public SchCode() {
	}

	public SchCode(int areaCode) {
		this.areaCode = areaCode;
	}

	public SchCode(int areaCode, String description, Set<CommonActInfo> commonActInfos, Set<UserAll> userAlls) {
		this.areaCode = areaCode;
		this.description = description;
		this.commonActInfos = commonActInfos;
		this.userAlls = userAlls;
	}

	@Id

	@Column(name = "Area_Code", unique = true, nullable = false)
	public int getAreaCode() {
		return this.areaCode;
	}

	public void setAreaCode(int areaCode) {
		this.areaCode = areaCode;
	}

	@Column(name = "Description", length = 100)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "schCode")
	public Set<CommonActInfo> getCommonActInfos() {
		return this.commonActInfos;
	}

	public void setCommonActInfos(Set<CommonActInfo> commonActInfos) {
		this.commonActInfos = commonActInfos;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "schCode")
	public Set<UserAll> getUserAlls() {
		return this.userAlls;
	}

	public void setUserAlls(Set<UserAll> userAlls) {
		this.userAlls = userAlls;
	}

}
