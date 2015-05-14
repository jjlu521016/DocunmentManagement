package com.bysj.docmanage.view.action;

import java.util.HashSet;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.bysj.docmanage.base.BaseAction;
import com.bysj.docmanage.domain.Department;
import com.bysj.docmanage.domain.Role;
import com.bysj.docmanage.domain.User;
import com.bysj.docmanage.util.DepartmentUtils;
import com.bysj.docmanage.util.MD5Utils;
import com.bysj.docmanage.util.QueryHelper;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User> {

	private Long departmentId;
	private Long[] roleIds;

	// 查询所有数据

	public String list() throws Exception {

//		List<User> userList = userService.findAll();
//
//		ActionContext.getContext().put("userList", userList);
		
		// 准备分页信息
		new QueryHelper(User.class, "r").preparePageBean(userService, pageNum, pageSize);
				
		return "list";
	}

	public String delete() throws Exception {

		userService.delete(model.getId());
		return "tolist";
	}

	public String add() throws Exception {
		// 封装到对象中
		// >> 设置所属部门
		model.setDepartment(departmentService.getById(departmentId));
		// >> 设置关联的岗位
		List<Role> roleList = roleService.getByIds(roleIds);
		model.setRoles(new HashSet<Role>(roleList));
		// 保存到数据库
		userService.save(model);
		return "tolist";

	}

	public String addUI() throws Exception {
		// 准备数据, departmentList
		List<Department> topList = departmentService.findTopList();
		List<Department> departmentList = DepartmentUtils
				.getAllDepartments(topList);
		ActionContext.getContext().put("departmentList", departmentList);

		// 准备数据, roleList
		List<Role> roleList = roleService.findAll();
		ActionContext.getContext().put("roleList", roleList);
		return "addUI";
	}

	public String edit() throws Exception {
		User user = userService.getById(model.getId());

		user.setLoginName(model.getLoginName());
		user.setName(model.getName());
		user.setEmail(model.getEmail());
		user.setGender(model.getGender());
		user.setPassword(model.getPassword());
		user.setPhoneNumber(model.getPhoneNumber());

		// >> 设置所属部门
		user.setDepartment(departmentService.getById(departmentId));
		// >> 设置关联的岗位
		List<Role> roleList = roleService.getByIds(roleIds);
		user.setRoles(new HashSet<Role>(roleList));

		userService.update(user);
		return "tolist";
	}
///////////////////////////////////////////////////////////////////////////////	
//	个人信息更改
	public String editInfoUI() throws Exception {
		

		// 准备回显的数据
		User user = userService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(user);
		
		return "editInfoUI";
	}
	
	public String editInfo() throws Exception {
		User user = userService.getById(model.getId());

		userService.update(model);
		return "editInfo";
	}
///////////////////////////////////////////////////////////////////////////////	
	public String editUI() throws Exception {
		// 准备数据, departmentList
		List<Department> topList = departmentService.findTopList();
		List<Department> departmentList = DepartmentUtils
				.getAllDepartments(topList);
		ActionContext.getContext().put("departmentList", departmentList);

		// 准备数据, roleList
		List<Role> roleList = roleService.findAll();
		ActionContext.getContext().put("roleList", roleList);

		// 准备回显的数据
		User user = userService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(user);
		if (user.getDepartment() != null) {
			departmentId = user.getDepartment().getId();
		}
		if (user.getRoles() != null) {
			roleIds = new Long[user.getRoles().size()];
			int index = 0;
			for (Role role : user.getRoles()) {
				roleIds[index++] = role.getId();
			}
		}
		return "editUI";
	}

	/** 初始化密码为123456 */
	public String initPassword() throws Exception {
		// 1，从数据库中取出原对象
		User user = userService.getById(model.getId());

		// 2，设置要修改的属性（要使用MD5摘要）
		String md5Digest = MD5Utils.GetMD5Code("123456");
		// String md5Digest = MD5Utils.md5Hex("123456");
		user.setPassword(md5Digest);
		// 3，更新到数据库
		userService.update(user);

		return "tolist";
	}

	
	/** 登录页面 */
	public String loginUI() throws Exception {
		return "loginUI";
	}

	/** 登录 */
	public String login() throws Exception {
		User user = userService.findByLoginNameAndPassword(model.getLoginName(), model.getPassword());
		if (user == null) {
			addFieldError("login", "用户名或密码不正确！");
			
			System.out.println("----loginUI---------"+ user);
			return "loginUI";
		} else {
			// 登录用户
			ActionContext.getContext().getSession().put("user", user);
			
			System.out.println("-----toINdex--------"+ user);
			return "toIndex";
		}
	}

	/** 注销 */
	public String logout() throws Exception { 
		ActionContext.getContext().getSession().remove("user");
		return "logout";
	}
	
	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public Long[] getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(Long[] roleIds) {
		this.roleIds = roleIds;
	}

}
