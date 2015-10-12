package com.qiantai_business.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.qiantai_business.dao.UserDao;
import com.qiantai_business.po.UserPo;
import com.qiantai_business.service.UserService;
import com.qiantai_business.utl.LoginException;

@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;
	
	

	public void addUser(UserPo user) {
		
		userDao.addUser(user);
	}

	public List<UserPo> getUserPo() {
		
		List<UserPo> list =userDao.getUserPo();
		
		return list;
	}

	public UserPo getUserByNameAndPassword(String user_name, String password) {
		
		Map<String,String> map = new HashMap<String, String>();
		map.put("user_name", user_name);
		map.put("password", password);
		
		return userDao.getUserByNameAndPassword(map);
	}

	public UserPo AdminLogin(String user_name, String password) throws LoginException {
		
		UserPo user = userDao.findUserByName(user_name);
		
		if (user == null) {
			throw new LoginException("用户不存在");
		} else if (!user.getPassword().equals(password)) {
			throw new LoginException("密码不匹配");
		}
		
		return user;
	}

	public UserPo findUserByName(String user_name) {
		return userDao.findUserByName(user_name);
	}


	public void updatePassword(String user_name, String newpassword) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("user_name", user_name);
		map.put("password", newpassword);
		
		userDao.updatePassword(map);
		
	}

	public void removeUser(String user_name) {
		
		userDao.removeUser(user_name);
		
	}

}
