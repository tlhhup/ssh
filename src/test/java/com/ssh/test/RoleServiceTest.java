package com.ssh.test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ssh.entity.Right;
import com.ssh.entity.Role;
import com.ssh.service.RightService;
import com.ssh.service.RoleService;

public class RoleServiceTest {

	private ApplicationContext act;

	@Before
	public void before(){
		act = new ClassPathXmlApplicationContext("beans.xml");
	}
	
	@Test
	public void saveRole(){
		RoleService roleService=(RoleService) act.getBean("roleService");
		Role role=new Role();
		role.setDescription("创建用户的权限");
		role.setName("普通管理员");
		role.setIsAvailable(true);
		
		Set<Right> rights=new HashSet<Right>();
		Right right=new Right();
		right.setId(1);
		rights.add(right);

		role.setRights(rights);
		
		roleService.saveEntity(role);
	}
	
	@Test
	public void updateRole(){
		RightService rightService = act.getBean(RightService.class);
		List<Right> rights = rightService.findRightNotInRange(null);
		
		RoleService roleService=(RoleService) act.getBean("roleService");
		Role role = roleService.get(1);
		role.setRights(new HashSet<>(rights));
		roleService.saveOrupdateEntity(role);
	}
	
}
