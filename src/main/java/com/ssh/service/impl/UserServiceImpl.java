package com.ssh.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ssh.dao.BaseDao;
import com.ssh.entity.Right;
import com.ssh.entity.Role;
import com.ssh.entity.User;
import com.ssh.service.UserService;

@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

	@Resource(name="userDao")
	@Override
	public void setDao(BaseDao<User> dao) {
		super.setDao(dao);
	}

	@Override
	public User validateUserInfo(String name,String password) {
		String hql="from User where userName=? and password=?";
		List<User> users = this.getEntityByHQL(hql, name,password);
		return users!=null?users.get(0):null;
	}

	@Override
	public Set<String> findUserRoles(String name) {
		String hql="from User where userName=?";
		Set<Role> roles = this.getEntityByHQL(hql, name).get(0).getRoles();
		roles.size();
		Set<String> result=new HashSet<String>();
		for(Role role:roles){
			result.add(role.getName());
		}
		return result;
	}

	@Override
	public Set<String> findUserPermission(String name) {
		String hql="from User where userName=?";
		Set<Role> roles = this.getEntityByHQL(hql, name).get(0).getRoles();
		roles.size();
		Set<String> result=new HashSet<String>();
		for(Role role:roles){
			for(Right right:role.getRights()){
				result.add(right.getRightName());
			}
		}
		return result;
	}
	
}
