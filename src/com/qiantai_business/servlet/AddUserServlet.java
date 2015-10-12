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

public class AddUserServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8772080505362094673L;
	private UserService userService;


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("UTF-8");
		
		JSONObject jsnObj = new JSONObject();
		
		String user_name = request.getParameter("user_name");
		String password = request.getParameter("password");
		String userclass = request.getParameter("user_class");
		
		UserPo userpo = userService.findUserByName(user_name);
		
		if(userpo!=null){
			jsnObj.put("status", 0);
			jsnObj.put("reason", "用户名已存在，请重新输入");
		}else if(user_name!=null&&password!=null&&userclass!=null){
			int user_class = Integer.parseInt(userclass);
			
			UserPo user = new UserPo();
			user.setUser_name(user_name);
			user.setPassword(password);
			user.setUser_class(user_class);
			userService.addUser(user);
			
			jsnObj.put("status", 1);
			jsnObj.put("reason", "添加成功");
		}
		
		response.getWriter().write(jsnObj.toString());
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		ServletContext servletContext = this.getServletContext();
		WebApplicationContext webAppContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		userService = webAppContext.getBean(UserService.class);
		
	}

}
