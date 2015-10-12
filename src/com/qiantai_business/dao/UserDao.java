package com.qiantai_business.dao;

import java.util.List;
import java.util.Map;

import com.qiantai_business.po.UserPo;

public interface UserDao {
	
	public void addUser(UserPo user);
	
	public List<UserPo> getUserPo();
	
	public UserPo getUserByNameAndPassword(Map<String, String> map);
	
	public UserPo findUserByName(String user_name);
	
	public void updatePassword(Map<String, String> map);
	
	public void removeUser(String user_name);

}
