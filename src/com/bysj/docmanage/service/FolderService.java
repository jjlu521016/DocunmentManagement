package com.bysj.docmanage.service;

import java.util.List;

import com.bysj.docmanage.base.DaoSupport;
import com.bysj.docmanage.domain.Department;
import com.bysj.docmanage.domain.Folder;
import com.bysj.docmanage.domain.FolderTree;
import com.bysj.docmanage.domain.Role;

public interface FolderService extends DaoSupport<Folder>{
	List<Folder> findTopList();

	List<Folder> findChildrenList(Long parentId);

	


}
