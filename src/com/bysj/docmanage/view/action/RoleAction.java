package com.bysj.docmanage.view.action;

import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import sun.security.acl.PermissionImpl;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.sun.org.glassfish.gmbal.Description;
import com.bysj.docmanage.base.BaseAction;
import com.bysj.docmanage.domain.Permission;
import com.bysj.docmanage.domain.Role;
import com.bysj.docmanage.domain.User;
import com.bysj.docmanage.service.RoleService;
import com.bysj.docmanage.util.QueryHelper;

@Controller
@Scope("prototype")
public class RoleAction extends BaseAction<Role> {

	private Long[] permissionIds;

	public String list() throws Exception {
		//
//		List<Role> roleList = roleService.findAll();
//		//
//		ActionContext.getContext().put("roleList", roleList);
		new QueryHelper(Role.class, "r").preparePageBean(roleService, pageNum, pageSize);
		return "list";
	}

	public String delete() throws Exception {
		roleService.delete(model.getId());
		return "tolist";
	}

	public String add() throws Exception {
		// 封装到对象中

		roleService.save(model);
		return "tolist";
	}

	public String addUI() throws Exception {

		return "addUI";
	}

	public String edit() throws Exception {
		Role role = roleService.getById(model.getId());

		roleService.update(model);
		return "tolist";
	}

	public String editUI() throws Exception {
		// 准备回显的数据
		Role role = roleService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(role);
		return "editUI";
	}

	/**
	 * 设置权限信息 卢杰20150106
	 * 
	 * @return
	 * @throws Exception
	 */
	public String setPermissionUI() throws Exception {
		// 准备数据，得到role的id
		Role role = roleService.getById(model.getId());
		
		System.out.println("************-------"+role);
		ActionContext.getContext().getValueStack().push(role);
		
		
		if (role.getPermissions() != null) {
			permissionIds = new Long[role.getPermissions().size()];
			int index = 0;
			for (Permission permisson : role.getPermissions()) {
				permissionIds[index++] = permisson.getId();
			}
		}

		List<Permission> permissionList = permissionService.findAll();
		ActionContext.getContext().put("permissionList", permissionList);
		return "setPermissionUI";

	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public String setPermission() throws Exception {
		// 1，从数据库中获取原对象
		Role role = roleService.getById(model.getId());

		// 2，设置要修改的属性
		List<Permission> permissionList = permissionService
				.getByIds(permissionIds);
		role.setPermissions(new HashSet<Permission>(permissionList));

		// 3，更新到数据库
		roleService.update(role);

		return "tolist";
	}

	public Long[] getPermissionIds() {
		return permissionIds;
	}

	public void setPermissionIds(Long[] permissionIds) {
		this.permissionIds = permissionIds;
	}

}
