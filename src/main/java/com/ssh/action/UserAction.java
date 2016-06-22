package com.ssh.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
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
		ActionContext.getContext().put("users", users);
		return "users";
	}
	
	public String edit(){
		this.model=userService.get(model.getId());
		return "edit";
	}
	
	public String update(){
		this.userService.updateEntity(model);
		return "userAction";
	}
	
	public String delete(){
		this.userService.deleteEntity(model);
		return "userAction";
	}

}
