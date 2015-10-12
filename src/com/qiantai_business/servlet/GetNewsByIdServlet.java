package com.qiantai_business.servlet;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.qiantai_business.po.NewsPo;
import com.qiantai_business.service.NewsService;
import com.qiantai_business.utl.ReflectHandler;

public class GetNewsByIdServlet extends HttpServlet {
	
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
		
		String id = request.getParameter("news_id");
		int news_id = Integer.parseInt(id);
		
		NewsPo news = newsService.getNewsById(news_id);
		
		String[] fieldsName = ReflectHandler.getFieldName(news);
		for (int i = 0; i < fieldsName.length; i++) {
			String fieldValue = ReflectHandler.getFieldValue(news, fieldsName[i]);
			jsonObj.put(fieldsName[i], fieldValue);
		}
		
		response.getWriter().write(jsonObj.toString());
		System.out.println(jsonObj.toString());
		
	}

	public void init() throws ServletException {
		ServletContext servletContext = this.getServletContext();
		WebApplicationContext webAppContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		newsService = webAppContext.getBean(NewsService.class);
	}

}
