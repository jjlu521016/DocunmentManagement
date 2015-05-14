package com.bysj.docmanage.service;

import java.util.List;

import com.bysj.docmanage.base.DaoSupport;
import com.bysj.docmanage.domain.Department;

public interface DepartmentService {

	List<Department> findAll();

	void delete(Long id);

	void save(Department model);

	Department getById(Long id);

	void update(Department department);

	List<Department> findTopList();

	List<Department> findChildrenList(Long parentId);

}
