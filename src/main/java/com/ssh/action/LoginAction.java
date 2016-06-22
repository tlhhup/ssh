package com.ssh.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ssh.entity.User;

@Controller
@Scope("prototype")
public class LoginAction extends BaseAction<User> {

	private static final long serialVersionUID = 1L;

	public String login(){
		return "login";
	}
	
	public String logout(){
		return "login";
	}
	
}
