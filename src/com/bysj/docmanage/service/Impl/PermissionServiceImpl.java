package com.bysj.docmanage.service.Impl;

import java.util.Collection;
import java.util.List;

import org.springframework.dao.support.DaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bysj.docmanage.base.DaoSupportImpl;
import com.bysj.docmanage.domain.Permission;
import com.bysj.docmanage.service.PermissionService;


@Service
@Transactional
@SuppressWarnings("unchecked")
public class PermissionServiceImpl extends DaoSupportImpl<Permission> implements PermissionService {
	
	
	public List<Permission> findTopList(){
		
		return getSession().createQuery( //
				"from Permission p where p.parent is null order by id"//
				).list();
	}
	
	public Collection<String> getAllPermissionUrls() {
		return getSession().createQuery(//
				"SELECT DISTINCT p.url FROM Permission p WHERE p.url IS NOT NULL order by id")//
				.list();
	}


}
