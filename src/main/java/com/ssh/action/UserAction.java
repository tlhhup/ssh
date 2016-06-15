package com.ssh.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ssh.entity.User;
import com.ssh.service.UserService;

@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User> {

	private static final long serialVersionUID = 1L;

	@Resource
	private UserService userService;

	public String index() {
		List<User> users = userService.getAllEntity();
		ServletActionContext.getRequest().setAttribute("users", users);
		return "users";
	}

}
