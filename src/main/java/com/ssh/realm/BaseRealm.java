package com.ssh.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import com.ssh.entity.User;
import com.ssh.service.UserService;

public class BaseRealm extends AuthorizingRealm {

	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * 授权-->角色和权限
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		String userName = (String) arg0.getPrimaryPrincipal();
		
		//注入用户的角色和权限
		SimpleAuthorizationInfo authorizationInfo=new SimpleAuthorizationInfo();
		authorizationInfo.setRoles(userService.findUserRoles(userName));
		authorizationInfo.setStringPermissions(userService.findUserPermission(userName));
		return authorizationInfo;
	}

	/**
	 * 身份验证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken arg0) throws AuthenticationException {
		String userName = (String) arg0.getPrincipal();// 获取身份信息
		String password=new String((char[])arg0.getCredentials());
		// 查询用户的信息
		User user = userService.validateUserInfo(userName,password);
		if (user == null) {
			throw new UnknownAccountException();
		}
		
		// 用户不可用
		if (user.getIsLocked()) {
			throw new LockedAccountException();
		}

		SimpleAuthenticationInfo authorizationInfo = new SimpleAuthenticationInfo(user.getUserName(),
				user.getPassword(), ByteSource.Util.bytes(user.getCredentialsSalt()), getName());
		return authorizationInfo;
	}

}
