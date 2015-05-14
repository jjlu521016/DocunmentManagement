package com.bysj.docmanage.service.Impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bysj.docmanage.base.DaoSupportImpl;
import com.bysj.docmanage.dao.RoleDao;
import com.bysj.docmanage.domain.Role;
import com.bysj.docmanage.service.RoleService;

@Service
@Transactional
public class RoleServiceImpl extends DaoSupportImpl<Role> implements RoleService {

//	@Resource
//	private RoleDao roleDao;
//
//	@Override
//	public void save(Role role) {
//		roleDao.save(role);
//
//	}
//
//	@Override
//	public List<Role> findAll() {
//		return roleDao.findAll();
//	}
//
//	@Override
//	public void delete(Long id) {
//		roleDao.delete(id);
//
//	}
//
//	@Override
//	public Role getById(Long id) {
//		// TODO Auto-generated method stub
//		return roleDao.getById(id);
//	}
//
//	@Override
//	public void update(Role role) {
//		roleDao.update(role);
//		
//	}

}
