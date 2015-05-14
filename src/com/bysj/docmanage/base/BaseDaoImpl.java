package com.bysj.docmanage.base;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

@SuppressWarnings("unchecked")
public abstract class BaseDaoImpl<T> implements BaseDao<T>
{

	@Resource
	private SessionFactory sessionFactory;
	private Class<T> clazz;

	public BaseDaoImpl()
	{
		// 使用反射技术得到T的真实类型
		ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass(); // 获取当前new的对象的
																							// 泛型的父类类型
		this.clazz = (Class<T>) pt.getActualTypeArguments()[0]; // 获取第一个类型参数的真实类型
		System.out.println("clazz ---> " + clazz);
	}

	/**
	 * 获取当前可用的Session对象
	 * 
	 * @return
	 */
	protected Session getSession()
	{
		return sessionFactory.getCurrentSession();
	}

	public void save(T entity)
	{
		getSession().save(entity);
	}

	public void update(T entity)
	{
		getSession().update(entity);
	}

	public void delete(Long id)
	{
		// 获取对象的id
		Object obj = getById(id);
		// 如果出入的id不为空则删除
		if (obj != null)
		{
			getSession().delete(obj);
		}
	}

	public T getById(Long id)
	{
		return (T) getSession().get(clazz, id);
	}

	public List<T> getByIds(Long[] ids)
	{
		return getSession().createQuery(//
				"FROM User WHERE id IN (:ids)")//
				.setParameterList("ids", ids)//
				.list();
	}

	public List<T> findAll()
	{
		return getSession().createQuery(//
				"FROM " + clazz.getSimpleName())//
				.list();
	}

}
