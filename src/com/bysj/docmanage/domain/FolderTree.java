package com.bysj.docmanage.domain;

import java.util.HashSet;
import java.util.Set;

import org.apache.struts2.json.annotations.JSON;

public class FolderTree {
	private Long id;
	private String name;
	private Integer pid;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public FolderTree() {
	}
	public FolderTree(Long id, String name, Integer pid) {
		super();
		this.id = id;
		this.name = name;
		this.pid = pid;
	}
	

	


}
