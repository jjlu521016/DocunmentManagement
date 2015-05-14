package com.bysj.docmanage.view.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.bysj.docmanage.base.BaseAction;
import com.bysj.docmanage.domain.Department;
import com.bysj.docmanage.domain.Files;
import com.bysj.docmanage.domain.Folder;
import com.bysj.docmanage.domain.FolderTree;
import com.bysj.docmanage.util.DepartmentUtils;
import com.bysj.docmanage.util.FolderUtils;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class FolderAction extends BaseAction<Folder> {
	private Long parentId;

	/**
	 * 显示文件夹列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
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

		return "list";
	}

	/**
	 * 删除
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		folderService.delete(model.getId());
		return "tolist";
	}

	/**
	 * 添加界面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String addUI() throws Exception {
		List<Folder> topList = folderService.findTopList();
		List<Folder> folderlist = FolderUtils.getAllFolders(topList);
		ActionContext.getContext().put("folderlist", folderlist);

		return "addUI";

	}

	/**
	 * 添加
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		// 封装信息到对象中
		// 根据Id查找对象
		Folder parent = folderService.getById(parentId);

		model.setParent(parent);

		folderService.save(model);
		return "tolist";

	}

	/**
	 * 修改
	 * 
	 * @return
	 * @throws Exception
	 */
	public String edit() throws Exception {
		// 从数据库取出原对象
		Folder folder = folderService.getById(model.getId());
		// 设置要修改的属性
		folder.setName(model.getName());
	//	folder.setDescription(model.getDescription());
		folder.setParent(folderService.getById(parentId));

		folderService.update(folder);
		return "tolist";
	}

	/**
	 * 修改界面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String editUI() throws Exception {

		List<Folder> topList = folderService.findTopList();
		List<Folder> folderlist = FolderUtils.getAllFolders(topList);
		ActionContext.getContext().put("folderlist", folderlist);

		// 准备回显数据
		Folder folder = folderService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(folder);

		if (folder.getParent() != null) {
			parentId = folder.getParent().getId();
		}
		return "editUI";
	}

	/**
	 * setter and getter
	 * 
	 * @return
	 */

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

}
