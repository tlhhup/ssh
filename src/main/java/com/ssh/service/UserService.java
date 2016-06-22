package com.ssh.service;

import java.util.Set;

import com.ssh.entity.User;

public interface UserService extends BaseService<User> {
	
	/**
	 * 通过名称查询用户
	 * @param name
	 * @param password 
	 * @return
	 */
	User validateUserInfo(String name, String password);

	/** 查询用户所有的角色
	 * @param name
	 * @return
	 */
	Set<String> findUserRoles(String name);
	
	/** 查询过用户所有的权限
	 * @param name
	 * @return
	 */
	Set<String> findUserPermission(String name);
	
}
