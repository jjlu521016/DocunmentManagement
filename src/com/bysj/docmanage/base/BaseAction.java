package com.bysj.docmanage.base;

import java.lang.reflect.ParameterizedType;

import javax.annotation.Resource;



import com.bysj.docmanage.domain.User;
import com.bysj.docmanage.service.DepartmentService;
import com.bysj.docmanage.service.FileService;
import com.bysj.docmanage.service.FolderService;
import com.bysj.docmanage.service.PermissionService;
import com.bysj.docmanage.service.RoleService;
import com.bysj.docmanage.service.UserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class BaseAction<T> extends ActionSupport implements ModelDriven<T> {

	public T model;
	
	public BaseAction(){
		try{
			ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
			
			//通过反射获取model的真实类型
			Class<T> clazz =(Class<T>) pt.getActualTypeArguments()[0];
			//通过反射创建model的真实类型
			model = clazz.newInstance();
		}
		catch (Exception e){
			throw new RuntimeException(e);
			
		}
		
	}

	public T getModel() {
		return model;
	}

	public void setModel(T model) {
		this.model = model;
	}
@Resource	
protected RoleService roleService;

@Resource
protected DepartmentService departmentService;

@Resource
protected UserService userService;

@Resource
protected PermissionService permissionService;


@Resource
protected FileService fileService;

@Resource
protected FolderService folderService;



/**
 * 获取当前用户
 */

protected User getCurrentUser(){
	return (User) ActionContext.getContext().getSession().get("user");
}

//分页相关

protected int pageNum =1;
protected int pageSize =10;
public int getPageNum() {
	return pageNum;
}

public void setPageNum(int pageNum) {
	this.pageNum = pageNum;
}

public int getPageSize() {
	return pageSize;
}

public void setPageSize(int pageSize) {
	this.pageSize = pageSize;
}
}
