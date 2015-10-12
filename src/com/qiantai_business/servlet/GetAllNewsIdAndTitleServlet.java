package com.qiantai_business.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.qiantai_business.service.NewsService;

public class GetAllNewsIdAndTitleServlet extends HttpServlet {
	
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
		List<HashMap<String, Object>> maplist = newsService.getAllNewsIdAndTitle();
		
		for (Map<String, Object> map : maplist) {
			JSONObject jsonObj = new JSONObject();
			for(Entry<String, Object> entry:map.entrySet())
			{
				if(entry.getKey().toLowerCase().endsWith("submitdate")){
					SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
					String date = sdf.format(entry.getValue());
					jsonObj.put(entry.getKey().toLowerCase(),date);

				}else{
					
					jsonObj.put(entry.getKey().toLowerCase(),entry.getValue());
				}
//				System.out.println(entry.getKey()+": "+entry.getValue());
			}
			jsonArray.add(jsonObj);
		}
		response.getWriter().write(jsonArray.toString());
		System.out.println(jsonArray.toString());
	}

	public void init() throws ServletException {
		
		WebApplicationContext webAppContext = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		newsService = webAppContext.getBean(NewsService.class);
		
	}

}
