package com.tongmeng.txyspring.model;
// Generated 2016-3-19 15:52:39 by Hibernate Tools 4.3.1.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Sliders generated by hbm2java
 */
@Entity
@Table(name = "sliders", catalog = "txyspring")
public class Sliders implements java.io.Serializable {

	private Integer id;
	private String title;
	private String imagePath;
	private String link;

	public Sliders() {
	}

	public Sliders(String title, String imagePath, String link) {
		this.title = title;
		this.imagePath = imagePath;
		this.link = link;
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

	@Column(name = "Title", nullable = false, length = 100)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "Image_Path", nullable = false, length = 65535)
	public String getImagePath() {
		return this.imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	@Column(name = "Link", nullable = false, length = 65535)
	public String getLink() {
		return this.link;
	}

	public void setLink(String link) {
		this.link = link;
	}

}
