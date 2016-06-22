package com.ssh.service;

import java.util.List;
import java.util.Set;

import com.ssh.entity.Right;


public interface RightService extends BaseService<Right> {

	/** 查询不再范围内的权限
	 * @param rights
	 * @return
	 */
	List<Right> findRightNotInRange(Set<Right> rights);

}
