package com.ssh.service.impl;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ssh.dao.BaseDao;
import com.ssh.entity.Role;
import com.ssh.service.RoleService;
import com.ssh.utils.DataUtils;
import com.ssh.utils.ValidateUtils;

@Service("roleService")
public class RoleServiceImple extends BaseServiceImpl<Role> implements RoleService {

	@Resource(name = "roleDao")
	@Override
	public void setDao(BaseDao<Role> dao) {
		super.setDao(dao);
	}

	@Override
	public List<Role> findRoleNotInRange(Set<Role> roles) {
		if(ValidateUtils.isValidate(roles)){
			return this.getAllEntity();
		}else{
			String hql="from Role where id not in ("+DataUtils.extractEntityIds(roles)+")";
			return this.getEntityByHQL(hql);
		}
	}

}
