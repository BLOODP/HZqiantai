package com.qiantai_business.po;

import java.util.Date;

public class ImagePo {
	
	private Integer image_id;
	private String image_name;
	private String image_type;
	private String image_description;
	private String image_path;
	private Date image_submitdate;
	public String getImage_name() {
		return image_name;
	}
	public void setImage_name(String image_name) {
		this.image_name = image_name;
	}
	public String getImage_type() {
		return image_type;
	}
	public void setImage_type(String image_type) {
		this.image_type = image_type;
	}
	public String getImage_description() {
		return image_description;
	}
	public void setImage_description(String image_description) {
		this.image_description = image_description;
	}
	public String getImage_path() {
		return image_path;
	}
	public void setImage_path(String image_path) {
		this.image_path = image_path;
	}
	public Date getImage_submitdate() {
		return image_submitdate;
	}
	public void setImage_submitdate(Date image_submitdate) {
		this.image_submitdate = image_submitdate;
	}
	public Integer getImage_id() {
		return image_id;
	}
	
	

} 
