package com.bysj.docmanage.service;

import java.util.List;

import com.bysj.docmanage.base.DaoSupport;
import com.bysj.docmanage.domain.Permission;
import java.util.Collection;

public interface PermissionService extends DaoSupport<Permission> {

	List<Permission> findTopList();

	Collection<String> getAllPermissionUrls();

}
