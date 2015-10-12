package com.qiantai_business.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.qiantai_business.po.NewsPo;
import com.qiantai_business.po.UserPo;
import com.qiantai_business.service.NewsService;

public class PublishNewsServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private NewsService newsService;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		JSONObject jsonObj = new JSONObject();
		
		String news_title = request.getParameter("news_title");
		String news_content = request.getParameter("news_contet");
		String news_type = request.getParameter("news_type");
		
		UserPo user = (UserPo) request.getSession().getAttribute("user");
		String news_publisher = user.getUser_name();
		
		NewsPo news = new NewsPo();
		news.setNews_content(news_content);
		news.setNews_publisher(news_publisher);
		news.setNews_title(news_title);
		news.setNews_type(news_type);
		news.setNews_submitdate(new Date());
		newsService.publishNews(news);
		jsonObj.put("status", 1);
		jsonObj.put("reason", "发布成功");
		
		response.getWriter().write(jsonObj.toString());
		System.out.println(jsonObj.toString());
		
	}

	public void init() throws ServletException {
		ServletContext servletConetxt = this.getServletContext();
		WebApplicationContext webAppContext = WebApplicationContextUtils.getWebApplicationContext(servletConetxt);
		newsService = webAppContext.getBean(NewsService.class);
	}

}
