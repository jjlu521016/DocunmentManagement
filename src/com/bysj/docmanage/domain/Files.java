package com.bysj.docmanage.domain;

import java.util.Date;

public class Files {
	private Long fileId;
	private String fileName;
	private String remark;
	private String filePath;
	private User author;
	private Date submitdate;
	private String types;
	private String filelength;

	private Folder folder;

	public Long getFileId() {
		return fileId;
	}

	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public Date getSubmitdate() {
		return submitdate;
	}

	public void setSubmitdate(Date submitdate) {
		this.submitdate = submitdate;
	}

	public String getTypes() {
		return types;
	}

	public void setTypes(String types) {
		this.types = types;
	}

	public Folder getFolder() {
		return folder;
	}

	public void setFolder(Folder folder) {
		this.folder = folder;
	}

	public String getFilelength() {
		return filelength;
	}

	public void setFilelength(String filelength) {
		this.filelength = filelength;
	}

}
