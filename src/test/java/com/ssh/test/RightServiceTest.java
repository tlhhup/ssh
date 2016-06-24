package com.ssh.test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ssh.entity.Right;
import com.ssh.service.RightService;

public class RightServiceTest {

	private ApplicationContext act;

	@Before
	public void before(){
		act = new ClassPathXmlApplicationContext("beans.xml");
	}
	
	@Test
	public void saveRight(){
		RightService rightService = act.getBean(RightService.class);
		Right right=new Right();
		right.setRightName("user:create");
		right.setDescription("创建用户");
		right.setIsAvailable(true);
		rightService.saveEntity(right);
	}
	
	@Test
	public void updateRight(){
		RightService rightService = act.getBean(RightService.class);
		Right right=new Right();
		right.setRightName("user:update");
		right.setDescription("更新用户");
		right.setIsAvailable(true);
		rightService.saveEntity(right);
	}
	
	@Test
	public void deleteRight(){
		RightService rightService = act.getBean(RightService.class);
		Right right=new Right();
		right.setRightName("user:delete");
		right.setDescription("删除用户");
		right.setIsAvailable(true);
		rightService.saveEntity(right);
	}
	
}
