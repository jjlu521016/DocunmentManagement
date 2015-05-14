package com.bysj.docmanage.service.Impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bysj.docmanage.base.DaoSupportImpl;
import com.bysj.docmanage.dao.RoleDao;
import com.bysj.docmanage.domain.Department;
import com.bysj.docmanage.domain.Folder;
import com.bysj.docmanage.domain.FolderTree;
import com.bysj.docmanage.domain.Role;
import com.bysj.docmanage.service.FolderService;
import com.bysj.docmanage.service.RoleService;

@Service
@Transactional
public class FolderServiceImpl extends DaoSupportImpl<Folder> implements
		FolderService {

	@Resource
	private SessionFactory sessionFactory;

	@Override
	public List<Folder> findTopList() {

		return sessionFactory.getCurrentSession().createQuery(//
				"from Folder f where f.parent is null ")//
				.list();//
	}

	@Override
	public List<Folder> findChildrenList(Long parentId) {
		return sessionFactory.getCurrentSession().createQuery(//
				"from Folder f where f.parent.id=? ")//
				.setParameter(0, parentId)//
				.list();//

	}



	// @Override
	// public List<Folder> findAll() {
	// // TODO Auto-generated method stub
	// return departmentDao.findAll();
	// }

}
