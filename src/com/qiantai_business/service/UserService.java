package com.qiantai_business.service;

import java.util.List;

import com.qiantai_business.po.UserPo;
import com.qiantai_business.utl.LoginException;

public interface UserService {
	
	public void addUser(UserPo user);
	
	public List<UserPo> getUserPo();
	
	public UserPo getUserByNameAndPassword(String user_name,String password);
	
	UserPo AdminLogin(String user_name,String password) throws LoginException;
	
	public UserPo findUserByName(String user_name);
	
	public void updatePassword(String user_name,String newpassword);
	
	public void removeUser(String user_name);

}
