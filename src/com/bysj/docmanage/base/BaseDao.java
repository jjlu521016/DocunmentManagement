package com.bysj.docmanage.base;

import java.util.List;

//传过来一个实体（泛型）
public interface BaseDao<T> {

	/**
	 * 保存实体
	 * 
	 * @param entity
	 */
	void save(T entity);

	/**
	 * 删除实体
	 * 
	 * @param id
	 */
	void delete(Long id);

	/**
	 * 更新实体
	 * 
	 * @param entity
	 */
	void update(T entity);

	/**
	 * 按id查询
	 * 
	 * @param id
	 * @return
	 */
	T getById(Long id);

	/**
	 * 按id查询
	 * 
	 * @param ids
	 * @return
	 */
	List<T> getByIds(Long[] ids);

	/**
	 * 查询所有数据
	 * 
	 * @return
	 */
	List<T> findAll();

}
