package com.bysj.docmanage.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.bysj.docmanage.domain.Department;
import com.bysj.docmanage.domain.Folder;

public class FolderUtils {

	/**
	 * 遍历文件夹树，把所有的文件夹遍历出来放到同一个集合中返回，并且其中所有文件夹的名称都修改了，以表示层次。
	 * 
	 * @param topList
	 * @return
	 */
	public static List<Folder> getAllFolders(List<Folder> topList) {
		List<Folder> list = new ArrayList<Folder>();
		walkFolderTreeList(topList, "文件夹：", list);
		return list;
	}

	/**
	 * 遍历文件夹树，把遍历出的文件夹信息放到指定的集合中
	 * 
	 * @param topList
	 */
	private static void walkFolderTreeList(Collection<Folder> topList,
			String prefix, List<Folder> list) {
		for (Folder top : topList) {
			// 顶点
			Folder copy = new Folder(); 
			// 使用副本，因为原对象在Session中
			copy.setId(top.getId());
			copy.setName(prefix + top.getName());
			list.add(copy); // 把副本添加到同一个集合中

			// 子树
			walkFolderTreeList(top.getChildren(), "　" + prefix, list); // 使用全角的空格
		}
	}
}
