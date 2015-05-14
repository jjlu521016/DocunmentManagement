package com.bysj.docmanage;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bysj.docmanage.domain.User;

@Service("testService")
public class TestService
{

	@Resource
	private SessionFactory sessionFactory;

	// 开事务
	@Transactional
	public void saveTwoUsers()
	{
		Session session = sessionFactory.getCurrentSession();

		session.save(new User());
		// int a = 1 / 0; // 这行会抛异常
		session.save(new User());
	}
}
