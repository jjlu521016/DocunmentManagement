package com.bysj.docmanage.service;

import java.util.List;

import com.bysj.docmanage.base.DaoSupport;
import com.bysj.docmanage.domain.Files;
import com.bysj.docmanage.domain.Role;

public interface FileService extends DaoSupport<Files>{

	String getPath(Long fileId);

	String getFileName(Long fileId);

}
