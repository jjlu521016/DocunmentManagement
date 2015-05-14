package com.bysj.docmanage.service;

import java.util.List;

import com.bysj.docmanage.base.DaoSupport;
import com.bysj.docmanage.domain.User;

public interface UserService extends DaoSupport<User>{

	User findByLoginNameAndPassword(String loginName, String password);

//	public List<User> finAll() ;
//
//		// TODO Auto-generated method stub
//	public void delete(Long id);
//
//	public void save(User model) ;
//
//	public User getById(Long id);
//
//	public void update(User model);



}
