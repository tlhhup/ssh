package com.ssh.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ssh.dao.BaseDao;
import com.ssh.entity.User;
import com.ssh.service.UserService;

@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

	@Resource(name="userDao")
	@Override
	public void setDao(BaseDao<User> dao) {
		super.setDao(dao);
	}
	
}
