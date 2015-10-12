package com.qiantai_business.service;

import java.util.HashMap;
import java.util.List;

import com.qiantai_business.po.NewsPo;

public interface NewsService {
	
	public void publishNews(NewsPo news);
	
	public List<NewsPo> getAllNews();
	
	public List<NewsPo> getNewsByType(String news_type);
	
	public List<HashMap<String, Object>> getAllNewsIdAndTitle();
	
	public NewsPo getNewsById(int news_id);

}
