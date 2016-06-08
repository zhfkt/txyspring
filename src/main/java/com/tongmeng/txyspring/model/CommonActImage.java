package com.tongmeng.txyspring.model;
// Generated 2016-6-8 14:16:35 by Hibernate Tools 4.3.1.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * CommonActImage generated by hbm2java
 */
@Entity
@Table(name = "common_act_image", catalog = "txyspring")
public class CommonActImage implements java.io.Serializable {

	private Integer id;
	private CommonActInfo commonActInfo;
	private String image;

	public CommonActImage() {
	}

	public CommonActImage(CommonActInfo commonActInfo) {
		this.commonActInfo = commonActInfo;
	}

	public CommonActImage(CommonActInfo commonActInfo, String image) {
		this.commonActInfo = commonActInfo;
		this.image = image;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "ID", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Act_ID", nullable = false)
	public CommonActInfo getCommonActInfo() {
		return this.commonActInfo;
	}

	public void setCommonActInfo(CommonActInfo commonActInfo) {
		this.commonActInfo = commonActInfo;
	}

	@Column(name = "Image", length = 65535)
	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}
