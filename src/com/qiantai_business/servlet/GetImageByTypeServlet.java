package com.qiantai_business.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.qiantai_business.po.ImagePo;
import com.qiantai_business.service.ImageService;
import com.qiantai_business.utl.ReflectHandler;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class GetImageByTypeServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ImageService imageService;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		JSONArray jsonArray = new JSONArray();
		
		String image_type = request.getParameter("image_type");
		String page = request.getParameter("pageNum");
		
		Integer count = 9;//设置每一页图片的数量
		int allCountByType = imageService.getAllNumsByType(image_type);
		int pageCount = allCountByType/count;//页数
		JSONObject jsonO = new JSONObject();
		jsonO.put("pageCount", pageCount);
		jsonArray.add(jsonO);
		
		Integer pageNum;
		if(page==null){
			pageNum = 1;
		}else{
			pageNum = Integer.parseInt(page);//获取当前页码
		}
		Integer row = pageNum * count;//从那一行开始查询
		
		
//		List<ImagePo> list = imageService.getImageInfoByType(image_type);
		List<ImagePo> list = imageService.getImageInfoByTypeAndPage(image_type,row, count);
		for (ImagePo imagePo : list) {
			JSONObject jsonObj = new JSONObject();
			
			String[] fieldsName = ReflectHandler.getFieldName(imagePo);
			for(int i=0;i<fieldsName.length;i++){
				String fieldValue = ReflectHandler.getFieldValue(imagePo, fieldsName[i]);
				jsonObj.put(fieldsName[i], fieldValue);
			}
			jsonArray.add(jsonObj);
		}
		response.getWriter().write(jsonArray.toString());
	}
	public void init() throws ServletException {
		ServletContext servletContext = this.getServletContext();
		WebApplicationContext webAppContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		imageService = webAppContext.getBean(ImageService.class);
	}

}
