package com.qiantai_business.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qiantai_business.dao.NewsDao;
import com.qiantai_business.po.NewsPo;
import com.qiantai_business.service.NewsService;

@Service("newsService")
public class NewsServiceImpl implements NewsService {
	
	@Autowired
	private NewsDao newsDao;

	public void publishNews(NewsPo news) {

		newsDao.publishNews(news);
	}

	public List<NewsPo> getAllNews() {
		
		return newsDao.getAllNews();
	}

	public List<NewsPo> getNewsByType(String news_type) {
		return newsDao.getNewsByType(news_type);
	}

	public List<HashMap<String, Object>> getAllNewsIdAndTitle() {
		return newsDao.getAllNewsIdAndTitle();
	}

	public NewsPo getNewsById(int news_id) {
		return newsDao.getNewsById(news_id);
	}

}
