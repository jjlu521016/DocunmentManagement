package com.bysj.docmanage.domain;

import java.util.HashSet;
import java.util.Set;

public class Permission {

	private Long id;
	private String url;
	private String name;
	private String description;

	private Set<Role> roles = new HashSet<Role>();
	private Permission parent;
	private Set<Permission> children = new HashSet<Permission>();

	/**
	 * 创建构造函数，便于初始化权限的时候使用
	 */
	public Permission() {
	}

	public Permission(String name, String url , Permission parent) {
		this.name = name;
		this.url = url;
		this.parent = parent;
	}
	
	// setter and getter
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Permission getParent() {
		return parent;
	}

	public void setParent(Permission parent) {
		this.parent = parent;
	}

	public Set<Permission> getChildren() {
		return children;
	}

	public void setChildren(Set<Permission> children) {
		this.children = children;
	}

}
