package com.qiantai_business.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.qiantai_business.po.UserPo;
import com.qiantai_business.service.ImageService;
import com.qiantai_business.service.UserService;
import com.qiantai_business.utl.FileHandler;

public class TestServlet extends HttpServlet {
	
	private UserService userService;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
//		request.setCharacterEncoding("UTF-8");
		String user_name = request.getParameter("user_name");
		String str = new String(user_name.getBytes("ISO-8859-1"), "utf-8");
		System.out.println("user_name:::::::"+str);
		
		UserPo user = new UserPo();
		user.setUser_name("何光勤");
		user.setUser_class(0);
		user.setPassword("1236");
		userService.addUser(user);
		
		String pass = request.getParameter("pass");
		System.out.println("pass::::"+pass);
/*		
		String path = this.getServletContext().getRealPath("/WEB-INF/image")+"\\"+"�˾";
		System.out.println("path:"+path);
		FileHandler fileHandler = new FileHandler();
		fileHandler.createFile(path);*/
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		String user_name = request.getParameter("user_name");
		String password = request.getParameter("pass");
		
		System.out.println("dopost user_name;;;;;;"+user_name);
		System.out.println("password::"+password);
		
		UserPo user = new UserPo();
		user.setPassword(password);
		user.setUser_name(user_name);
		user.setUser_class(1);
		userService.addUser(user);
		
	}

	public void init() throws ServletException {
		ServletContext servletContext = this.getServletContext();
		WebApplicationContext webAppContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		userService = webAppContext.getBean(UserService.class);
	}

}
