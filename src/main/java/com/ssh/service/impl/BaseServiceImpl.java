package com.ssh.service.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.springframework.cache.annotation.Cacheable;

import com.ssh.dao.BaseDao;
import com.ssh.service.BaseService;

@SuppressWarnings("unchecked")
public class BaseServiceImpl<T> implements BaseService<T> {

	private BaseDao<T> dao;
	private Class<T> clazz;

	public BaseServiceImpl() {
		ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
		clazz = (Class<T>) type.getActualTypeArguments()[0];
	}

	// 用户使用方法注入
	public void setDao(BaseDao<T> dao) {
		this.dao = dao;
	}

	@Override
	public void saveEntity(T t) {
		dao.saveEntity(t);
	}

	@Override
	public void deleteEntity(T t) {
		dao.deleteEntity(t);
	}

	@Override
	public void updateEntity(T t) {
		dao.updateEntity(t);
	}

	@Override
	public void saveOrupdateEntity(T t) {
		dao.saveOrupdateEntity(t);
	}

	@Override
	public void batchEntityByHQL(String hql, Object... objects) {
		dao.batchEntityByHQL(hql, objects);
	}

	@Override
	public void executeSql(String sql, Object... objects) {
		dao.executeSql(sql, objects);
	}

	@Override
	public T get(Integer id) {
		return dao.get(id);
	}

	@Override
	public T load(Integer id) {
		return dao.load(id);
	}

	@Override
	public List<T> getEntityByHQL(String hql, Object... objects) {
		return dao.getEntityByHQL(hql, objects);
	}

	@Override
	public Object getUniqueResult(String hql, Object... objects) {
		return dao.getUniqueResult(hql, objects);
	}

	@Cacheable("serviceCache")
	@Override
	public List<T> getAllEntity() {
		String hql="from "+this.clazz.getSimpleName();
		return dao.getEntityByHQL(hql);
	}

	@Cacheable("serviceCache")
	@Override
	public List<T> findEntityBySQl(String sql, Object... objects) {
		return dao.findEntityBySQl(sql, objects);
	}

}
