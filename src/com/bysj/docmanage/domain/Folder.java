package com.bysj.docmanage.domain;

import java.util.HashSet;
import java.util.Set;

public class Folder {
	private Long id;
	private String name;
	private String folderpath;
	private String description;
	private Folder parent;
	
	private Set<Folder> children = new HashSet<Folder>();
	
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

	public String getFolderpath() {
		return folderpath;
	}
	public void setFolderpath(String folderpath) {
		this.folderpath = folderpath;
	}
	public Folder getParent() {
		return parent;
	}
	public void setParent(Folder parent) {
		this.parent = parent;
	}
	public Set<Folder> getChildren() {
		return children;
	}
	public void setChildren(Set<Folder> children) {
		this.children = children;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}


}
