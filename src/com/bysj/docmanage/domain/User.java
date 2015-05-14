package com.bysj.docmanage.domain;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.opensymphony.xwork2.ActionContext;

/**
 * 用户
 * 
 * @author tyg
 * 
 */
public class User {

	private Long id;
	// 专业或部门
	private Department department;
	// 角色
	private Set<Role> roles = new HashSet<Role>();

	private String loginName; // 登录名
	private String password; // 密码
	private String name; // 真实姓名
	private String gender; // 性别
	private String phoneNumber; // 电话号码
	private String email; // 电子邮件
	private String description; // 说明
	// 20150106添加的字段
	private String photo;// 用户头像

	/**
	 * 判断本用户是否有指定名称的权限
	 * 
	 * @param name
	 * @return
	 */
	public boolean hasPermissionByName(String name) {
		// 超级管理有所有的权限
		if (isAdmin()) {
			return true;
		}

		// 普通用户要判断是否含有这个权限
		for (Role role : roles) {
			for (Permission permission : role.getPermissions()) {
				if (permission.getName().equals(name)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 判断本用户是否有指定URL的权限
	 */
	@SuppressWarnings("unchecked")
	public boolean hasPermissionByUrl(String PermUrl) {
		// 超级管理有所有的权限
		if (isAdmin()) {
			return true;
		}

		// 去掉后面的参数,
		int pos = PermUrl.indexOf("?");
		// 如果字符中不含有“？”，则pos=-1，否则返回的值大于-1.
		if (pos > -1) {
			PermUrl = PermUrl.substring(0, pos);
		}
		// 如果获取的字符含有“UI”字符，则将“UI”去掉
		if (PermUrl.endsWith("UI")) {
			PermUrl = PermUrl.substring(0, PermUrl.length() - 2);
		}

		// 如果本URL不需要控制，则登录用户就可以使用
		Collection<String> allPermissionUrls = (Collection<String>) ActionContext
				.getContext().getApplication().get("allPermissionUrls");
		if (!allPermissionUrls.contains(PermUrl)) {
			return true;
		} else {
			// 普通用户要判断是否含有这个权限
			for (Role role : roles) {
				for (Permission permission : role.getPermissions()) {
					// 与判断名称权限的判断不同，防止空指针异常。
					if (PermUrl.equals(permission.getUrl())) {
						return true;
					}
				}
			}
			return false;
		}
	}

	/**
	 * 判断本用户是否是超级管理员
	 * 
	 * @return
	 */
	public boolean isAdmin() {
		return "admin".equals(loginName);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

}
