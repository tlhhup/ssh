package com.ssh.service;

import java.util.List;

public interface BaseService<T> {
	
	public void saveEntity(T t);

	public void deleteEntity(T t);

	public void updateEntity(T t);

	public void saveOrupdateEntity(T t);

	public void batchEntityByHQL(String hql, Object... objects);

	// 执行原生的sql
	public void executeSql(String sql, Object... objects);

	public T get(Integer id);

	public T load(Integer id);

	public List<T> getEntityByHQL(String hql, Object... objects);

	public Object getUniqueResult(String hql, Object... objects);

	public List<T> getAllEntity();

	// 原生sql查询
	public List<T> findEntityBySQl(String sql, Object... objects);
}
