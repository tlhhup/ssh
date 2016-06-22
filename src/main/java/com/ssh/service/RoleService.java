package com.ssh.service;

import java.util.List;
import java.util.Set;

import com.ssh.entity.Role;

public interface RoleService extends BaseService<Role> {

	/** 获取不再范围内的角色
	 * @param roles
	 * @return
	 */
	List<Role> findRoleNotInRange(Set<Role> roles);

}
