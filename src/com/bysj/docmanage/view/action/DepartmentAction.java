package com.bysj.docmanage.view.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import com.bysj.docmanage.base.BaseAction;
import com.bysj.docmanage.domain.Department;
import com.bysj.docmanage.domain.Role;
import com.bysj.docmanage.service.DepartmentService;
import com.bysj.docmanage.util.DepartmentUtils;

@Controller
@Scope("prototype")
public class DepartmentAction extends BaseAction<Department> {
	// parentId
	private Long parentId;

	public String list() throws Exception {
		List<Department> departmentList = null;

		if (parentId == null) {
			// 顶层部门
			departmentList = departmentService.findTopList();
		} else {
			departmentList = departmentService.findChildrenList(parentId);

			Department parent = departmentService.getById(parentId);
			ActionContext.getContext().put("parent", parent);
		}
		ActionContext.getContext().put("departmentList", departmentList);

		
		return "list";
	}

	public String delete() throws Exception {
		departmentService.delete(model.getId());
		return "tolist";
	}

	public String add() throws Exception {
		// 封装信息到对象中
		// 根据Id查找对象
		Department parent = departmentService.getById(parentId);

		model.setParent(parent);

		departmentService.save(model);
		return "tolist";

	}

	public String addUI() throws Exception {
		// 准备数据，部门列表

		List<Department> topList = departmentService.findTopList();
		List<Department> departmentList = DepartmentUtils.getAllDepartments(topList);

		ActionContext.getContext().put("departmentList", departmentList);
		return "addUI";
	}

	public String edit() throws Exception {
		// 从数据库取出原对象
		Department department = departmentService.getById(model.getId());
		// 设置要修改的属性
		department.setName(model.getName());
		department.setDescription(model.getDescription());
		department.setParent(departmentService.getById(parentId));

		departmentService.update(department);
		return "tolist";
	}

	public String editUI() throws Exception {

		List<Department> topList = departmentService.findTopList();
		List<Department> departmentList = DepartmentUtils.getAllDepartments(topList);
		ActionContext.getContext().put("departmentList", departmentList);

		//准备回显数据
		Department department = departmentService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(department);

		if (department.getParent() != null) {
			parentId = department.getParent().getId();
		}
		return "editUI";
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

}
