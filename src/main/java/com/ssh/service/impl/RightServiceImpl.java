package com.ssh.service.impl;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ssh.dao.BaseDao;
import com.ssh.entity.Right;
import com.ssh.service.RightService;
import com.ssh.utils.DataUtils;
import com.ssh.utils.ValidateUtils;

@Service("rightService")
public class RightServiceImpl extends BaseServiceImpl<Right> implements RightService {

	@Resource(name = "rightDao")
	@Override
	public void setDao(BaseDao<Right> dao) {
		super.setDao(dao);
	}

	@Override
	public List<Right> findRightNotInRange(Set<Right> rights) {
		if (!ValidateUtils.isValidate(rights)) {
			return this.getAllEntity();
		} else {
			String hql = "from Right where id not in (" + DataUtils.extractEntityIds(rights) + ")";
			return this.getEntityByHQL(hql);
		}
	}

}
