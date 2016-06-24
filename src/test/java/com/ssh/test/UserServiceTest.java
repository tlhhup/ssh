package com.ssh.test;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.DigestUtils;

import com.ssh.entity.Role;
import com.ssh.entity.User;
import com.ssh.service.RoleService;
import com.ssh.service.UserService;

public class UserServiceTest {

	private ApplicationContext act;

	@Before
	public void before() {
		act = new ClassPathXmlApplicationContext("beans.xml");
	}

	@Test
	public void queryUser() {
		UserService userServices = (UserService) act.getBean("userService");
		Set<String> roles = userServices.findUserRoles("admin");
		Set<String> permissions = userServices.findUserPermission("admin");
		System.out.println("拥有的角色："+roles);
		System.out.println("拥有的权限："+permissions);
	}

	@Test
	public void saveUser() {
		UserService userServices = (UserService) act.getBean("userService");
		User user = new User();
		user.setAddress("成都");
		user.setUserName("李四");
		user.setPassword("111111");
		userServices.saveEntity(user);
	}

	@Test
	public void updateUser() {
		UserService userServices = (UserService) act.getBean("userService");
		User user = userServices.get(6);

		RoleService roleService = act.getBean(RoleService.class);
		Role role = roleService.get(2);
		Set<Role> roles = new HashSet<Role>();
		roles.add(role);

		user.setRoles(roles);

		userServices.saveOrupdateEntity(user);
	}
	
	@Test
	public void update(){
		UserService userServices = (UserService) act.getBean("userService");
		User user = userServices.get(6);
		user.setUserName("lisi");
		user.setPassword(DigestUtils.md5DigestAsHex("admin".getBytes()));
		userServices.updateEntity(user);
	}
	
	@Test
	public void query(){
		UserService userServices = (UserService) act.getBean("userService");
		User user = userServices.validateUserInfo("admin", DigestUtils.md5DigestAsHex("admin".getBytes()));
		System.out.println(user);
	}

}
