package com.bysj.docmanage.view.action;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.apache.struts2.json.annotations.JSON;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.bysj.docmanage.base.BaseAction;
import com.bysj.docmanage.domain.Department;
import com.bysj.docmanage.domain.Files;
import com.bysj.docmanage.domain.Folder;
import com.bysj.docmanage.domain.FolderTree;
import com.bysj.docmanage.domain.PageBean;
import com.bysj.docmanage.domain.Role;
import com.bysj.docmanage.domain.User;
import com.bysj.docmanage.util.DepartmentUtils;
import com.bysj.docmanage.util.FolderUtils;
import com.bysj.docmanage.util.QueryHelper;
import com.bysj.docmanage.util.StringToolkit;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class FileAction extends BaseAction<Files> {

	private Long folderId;
	private static final long serialVersionUID = 1L;
	private Long parentId;

	private Long fid;

	private Map<String, Object> dataMap;

	public String list() throws Exception {

		return "list";
	}

	public String filelist() throws Exception {
		Folder folder = folderService.getById(folderId);
		ActionContext.getContext().put("folder", folder);
		new QueryHelper(Files.class, "f").addCondition("f.folder =?", folder)
				.preparePageBean(fileService, pageNum, pageSize);
		return "filelist";
	}

	public String batchAdd() throws Exception {

		return "batchAdd";
	}

	public String delete() throws Exception {
		fileService.delete(model.getFileId());
		return "tolist";
	}

	public String add() throws Exception {
		// 封装到对象中
		Files file = new Files();

		file.setFolder(folderService.getById(folderId));
		file.setAuthor(getCurrentUser());
		file.setSubmitdate(new Date());
		file.setFileName(model.getFileName());
		file.setFilePath(model.getFilePath());
		file.setTypes(model.getTypes());

		// 保存到数据库
		fileService.save(file);
		return "tolist";

	}

	public String addUI() throws Exception {
		// 准备数据, departmentList
		List<Folder> topList = folderService.findTopList();
		List<Folder> folderList = FolderUtils.getAllFolders(topList);
		ActionContext.getContext().put("folderList", folderList);

		return "addUI";
	}

	public String left() throws Exception {

		List<Folder> foldertList = null;

		if (parentId == null) {
			// 顶层文件夹
			foldertList = folderService.findTopList();
		} else {
			foldertList = folderService.findChildrenList(parentId);

			Folder parent = folderService.getById(parentId);
			ActionContext.getContext().put("parent", parent);
		}
		ActionContext.getContext().put("foldertList", foldertList);

		return "left";
	}

	public String right() throws Exception {
		new QueryHelper(Files.class, "f").preparePageBean(fileService, pageNum,
				pageSize);
		return "right";
	}

	/**
	 * 文件重命名
	 * 
	 * @return
	 * @throws Exception
	 */
	public String renameUI() throws Exception {
		getFolder();
		return "renameUI";

	}

	public String rename() throws Exception {
		Files files = fileService.getById(fid);
		String respath = files.getFilePath();
		
		int lastIndex = respath.lastIndexOf("\\");
		String documentpath = respath.substring(0, lastIndex + 1);
		String newFilePath = documentpath+model.getFileName();
//		String newFilePath = StringToolkit.formatPath(StringToolkit
//				.getParentPath(resFilePath) + "/" + newFileName);
		File resFile = new File(respath);
		File newFile = new File(newFilePath);
		resFile.renameTo(newFile);
		
		
		files.setAuthor(getCurrentUser());
		files.setFilelength(model.getFilelength());
		files.setFileName(model.getFileName());
		files.setFolder(folderService.getById(folderId));
		files.setTypes(model.getTypes());
		files.setFilePath(newFilePath);
		files.setSubmitdate(new Date());
		fileService.update(files);
		return "tolist";
	}

	/**
	 * 复制文件
	 * 
	 * @return
	 * @throws Exception
	 */
	public String copyUI() throws Exception {
		getFolder();
		return "copyUI";
	}

	public String copy() throws Exception {
		savefile();
		return "tolist";
	}

	/**
	 * 移动文件
	 * 
	 * @return
	 * @throws Exception
	 */
	public String removeUI() throws Exception {
		getFolder();
		return "removeUI";
	}

	public String remove() throws Exception {
		savefile();
		// 删除原数据
		fileService.delete(model.getFileId());
		return "tolist";
	}

	/**
	 * 文件移动或复制时获取文件和文件夹
	 */
	public void getFolder() {
		List<Folder> topList = folderService.findTopList();
		List<Folder> folderlist = FolderUtils.getAllFolders(topList);
		ActionContext.getContext().put("folderlist", folderlist);

		Files file = fileService.getById(fid);
		ActionContext.getContext().getValueStack().push(file);
		if (file.getFolder() != null) {
			folderId = file.getFolder().getId();
		}
	}

	public void savefile() {
		Files file = new Files();
		file.setFolder(folderService.getById(folderId));
		file.setAuthor(getCurrentUser());
		file.setFilelength(model.getFilelength());
		file.setFileName(model.getFileName());
		file.setFilePath(model.getFilePath());
		file.setTypes(model.getTypes());
		file.setSubmitdate(new Date());
		fileService.save(file);
	}


	@JSON(serialize = false)
	public Long getFolderId() {
		return folderId;
	}

	public void setFolderId(Long folderId) {
		this.folderId = folderId;
	}

	public Map<String, Object> getDataMap() {
		return dataMap;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Long getFid() {
		return fid;
	}

	public void setFid(Long fid) {
		this.fid = fid;
	}

}
