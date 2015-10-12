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

import com.qiantai_business.po.ImagePo;
import com.qiantai_business.service.ImageService;
import com.qiantai_business.utl.ReflectHandler;

public class GetImageByIdServlet extends HttpServlet {

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
		JSONObject jsonObj = new JSONObject();
		
		String id = request.getParameter("image_id");
		int image_id = Integer.parseInt(id);
		ImagePo image = imageService.getImageById(image_id);
		
		String[] fieldsName = ReflectHandler.getFieldName(image);
		for (int i = 0; i < fieldsName.length; i++) {
			String fieldValue = ReflectHandler.getFieldValue(image, fieldsName[i]);
			jsonObj.put(fieldsName[i], fieldValue);
		}
		
		response.getWriter().write(jsonObj.toString());
		System.out.println(jsonObj.toString());
	}

	public void init() throws ServletException {
		ServletContext servletContext = this.getServletContext();
		WebApplicationContext webAppContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		imageService = webAppContext.getBean(ImageService.class);
	}

}
