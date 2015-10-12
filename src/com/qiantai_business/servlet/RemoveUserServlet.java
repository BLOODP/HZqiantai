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

public class RemoveUserServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserService userService; 

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		
		JSONObject jsonObj = new JSONObject();
		
		UserPo user = (UserPo) request.getSession().getAttribute("user");
		
		int user_class = user.getUser_class();
		String user_name = request.getParameter("user_name");
		
		if(user_class==0)
		{
			jsonObj.put("status", 0);
			jsonObj.put("status", "删除失败，用户权限不足");
			
		}else if(user_class==1&&user_name!=null){
			userService.removeUser(user_name);
			jsonObj.put("status", 1);
			jsonObj.put("status", "删除成功");
		}
		
		response.getWriter().write(jsonObj.toString());
		
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		ServletContext servletContext = this.getServletContext();
		WebApplicationContext webAppContext = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
		userService = webAppContext.getBean(UserService.class);
	}

}
