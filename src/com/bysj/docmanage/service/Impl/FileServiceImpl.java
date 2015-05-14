package com.bysj.docmanage.service.Impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bysj.docmanage.base.DaoSupportImpl;
import com.bysj.docmanage.dao.DepartmentDao;
import com.bysj.docmanage.domain.Department;
import com.bysj.docmanage.domain.Files;
import com.bysj.docmanage.service.DepartmentService;
import com.bysj.docmanage.service.FileService;

@Service
@Transactional
public class FileServiceImpl extends DaoSupportImpl<Files> implements FileService {

	//获取文件的存放路径
	@Override
	public String getPath(Long fileId) {
		String path = (String) getSession().createQuery(//
				"select filePath FROM Files f where f.fileId=?")//
				.setParameter(0, fileId)
				.uniqueResult();
		System.out.println("------------path="+path);
		return path;
	}
	//获取文件名
	@Override
	public String getFileName(Long fileId) {
		String fileName = (String) getSession().createQuery(//
				"select fileName FROM Files f where f.fileId=?")//
				.setParameter(0, fileId)
				.uniqueResult();
		System.out.println("------------fileName="+fileName);
		return fileName;
	}


}
