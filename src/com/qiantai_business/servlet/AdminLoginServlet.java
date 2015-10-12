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

import com.qiantai_business.po.UserPo;
import com.qiantai_business.service.UserService;
import com.qiantai_business.utl.LoginException;

public class AdminLoginServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7361821805517635134L;
	private UserService userService;

	/**
	 * Constructor of the object.
	 */
	public AdminLoginServlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("UTF-8");
		
		String user_name = request.getParameter("user_name");
		String password =request.getParameter("password");
		
		JSONObject jsonObj = new JSONObject();
		
		try {
			UserPo user = userService.AdminLogin(user_name, password);
			request.getSession(true).setAttribute("user", user);
			jsonObj.put("status", 1);
			jsonObj.put("reason", "登录成功");
			jsonObj.put("user_name", user.getUser_name());
			
		} catch (LoginException e) {
			jsonObj.put("status", 0);
			jsonObj.put("reason", e.getMessage());
		}
		
		response.getWriter().write(jsonObj.toString());
		
	}

	public void init() throws ServletException {
		ServletContext servletContext = this.getServletContext();
		WebApplicationContext webAppContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		userService = webAppContext.getBean(UserService.class);
	}

}
