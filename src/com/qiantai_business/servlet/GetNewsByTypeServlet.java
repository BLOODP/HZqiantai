package com.qiantai_business.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.qiantai_business.po.NewsPo;
import com.qiantai_business.service.NewsService;
import com.qiantai_business.utl.ReflectHandler;

public class GetNewsByTypeServlet extends HttpServlet {
	
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
		JSONArray jsonArray = new JSONArray();
		
		String news_type = request.getParameter("news_type");
		
		List<NewsPo> list = newsService.getNewsByType(news_type);
		for (NewsPo newsPo : list) {
			JSONObject jsonObj = new JSONObject();
			String[] fields = ReflectHandler.getFieldName(newsPo);
			for(int i=0;i<fields.length;i++){
				String fieldValue = ReflectHandler.getFieldValue(newsPo, fields[i]);
				jsonObj.put(fields[i], fieldValue);
			}
			jsonArray.add(jsonObj);
		}
		
		response.getWriter().write(jsonArray.toString());
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		ServletContext servletContet = this.getServletContext();
		WebApplicationContext webAppContext = WebApplicationContextUtils.getWebApplicationContext(servletContet);
		newsService = webAppContext.getBean(NewsService.class);
	}

}
