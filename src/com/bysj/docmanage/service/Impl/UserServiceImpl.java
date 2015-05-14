package com.bysj.docmanage.service.Impl;

import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bysj.docmanage.base.DaoSupportImpl;
import com.bysj.docmanage.dao.UserDao;
import com.bysj.docmanage.domain.User;
import com.bysj.docmanage.service.UserService;
import com.bysj.docmanage.util.MD5Utils;

@Service
@Transactional
public class UserServiceImpl extends DaoSupportImpl<User> implements
		UserService {

	public User findByLoginNameAndPassword(String loginName, String password) {

		// 使用密码的MD5摘要进行对比
		String md5Digest = MD5Utils.GetMD5Code(password);
		return (User) getSession().createQuery(//
				"FROM User u WHERE u.loginName=? AND u.password=?")//
				.setParameter(0, loginName)//
				.setParameter(1, md5Digest)//
				.uniqueResult();
	}

}
