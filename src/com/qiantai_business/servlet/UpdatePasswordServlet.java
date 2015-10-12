package com.qiantai_business.servlet;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import net.sf.json.JSONObject;

import com.qiantai_business.po.UserPo;
import com.qiantai_business.service.UserService;

public class UpdatePasswordServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserService userService; 

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		
		JSONObject jsonObj = new JSONObject();
		
		UserPo user = (UserPo) request.getSession().getAttribute("user");
		String user_name = user.getUser_name();
		String newpassword = request.getParameter("newpasswprd");
		String repassword = request.getParameter("repassword");
		
		if(newpassword==null||repassword==null||!newpassword.equals(repassword)){
			jsonObj.put("status", 0);
			jsonObj.put("reason", "修改失败，两次密码不匹配");
		}else{
			userService.updatePassword(user_name, newpassword);
			jsonObj.put("status", 1);
			jsonObj.put("reason", "修改成功");
		}
		
		response.getWriter().write(jsonObj.toString());
		
	}

	
	public void init() throws ServletException {
		ServletContext servletContext = this.getServletContext();
		WebApplicationContext webAppContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		userService = webAppContext.getBean(UserService.class);
	}

}
