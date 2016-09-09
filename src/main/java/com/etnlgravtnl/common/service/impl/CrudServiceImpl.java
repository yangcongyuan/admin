package com.etnlgravtnl.common.service.impl;

import com.etnlgravtnl.common.persistence.CrudDao;
import com.etnlgravtnl.common.persistence.DataEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * Service基类
 * @author guozheng
 * @version 2016-05-07
 */
@Transactional(readOnly = true)
public abstract class CrudServiceImpl<D extends CrudDao<T>, T extends DataEntity<T>> extends BaseServiceImpl {
	
	/**
	 * 持久层对象
	 */
	@Autowired
	protected D dao;
	
	/**
	 * 获取单条数据
	 * @param id
	 * @return
	 */
	public T get(String id) {

		return dao.get(id);
	}

	/**
	 * 获取单条数据
	 * @param entity
	 * @return
	 */
	public T get(T entity) {
		return dao.get(entity);
	}
	
	/**
	 * 查询列表数据
	 * @param entity
	 * @return
	 */
	public List<T> findList(T entity) {
		return dao.findList(entity);
	}

	/**
	 * 保存数据（插入或更新）
	 * @param entity
	 */
	@Transactional(readOnly = false)
	public void save(T entity) {
		if (entity.getIsNewRecord()){
			dao.insert(entity);
		}else{
			dao.update(entity);
		}
	}
	/**
	 * 保存数据（插入或更新）
	 * @param entity
	 */
	@Transactional(readOnly = false)
	public int saveInt(T entity) {
		if (entity.getIsNewRecord()){
			return dao.insert(entity);
		}else{
			return dao.update(entity);
		}
	}

	/**
	 * 保存数据（插入或更新）
	 * @param entity
	 */
	@Transactional(readOnly = false)
	public int update(T entity) {
		return dao.update(entity);
	}

	/**
	 * 删除数据
	 * @param entity
	 */
	@Transactional(readOnly = false)
	public void delete(T entity) {
		dao.delete(entity);
	}

}
