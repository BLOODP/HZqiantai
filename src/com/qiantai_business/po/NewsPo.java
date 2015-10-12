package com.qiantai_business.po;

import java.util.Date;

public class NewsPo {
	
	private Integer news_id;
	
	private String news_title;
	
	private String news_content;
	
	private String news_type;
	
	private Date news_submitdate;
	
	private String news_publisher;

	public Integer getNews_id() {
		return news_id;
	}

	public void setNews_id(Integer news_id) {
		this.news_id = news_id;
	}

	public String getNews_title() {
		return news_title;
	}

	public void setNews_title(String news_title) {
		this.news_title = news_title;
	}

	public String getNews_content() {
		return news_content;
	}

	public void setNews_content(String news_content) {
		this.news_content = news_content;
	}

	public String getNews_type() {
		return news_type;
	}

	public void setNews_type(String news_type) {
		this.news_type = news_type;
	}

	public Date getNews_submitdate() {
		return news_submitdate;
	}

	public void setNews_submitdate(Date news_submitdate) {
		this.news_submitdate = news_submitdate;
	}

	public String getNews_publisher() {
		return news_publisher;
	}

	public void setNews_publisher(String news_publisher) {
		this.news_publisher = news_publisher;
	}
	
	

}
