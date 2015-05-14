package com.bysj.docmanage.view.action;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import sun.misc.BASE64Encoder;

import com.bysj.docmanage.base.BaseAction;
import com.bysj.docmanage.domain.Files;
import com.bysj.docmanage.service.FileService;

/** 
 * 
 */
@Controller
@Scope("prototype")
public class DownloadFileAction extends BaseAction<Files> {

	String filename;// 要下载的文件名
	@Resource
	FileService fileService;

	Long fid;

	public String execute() throws Exception {
		System.out.println("下载文件----" + filename);
		return SUCCESS;
	}

	public InputStream getInputStream() throws IOException {

		String filepath = fileService.getPath(fid);

		System.out.println("**************************************filepath****"
				+ filepath);
		File file = new File(filepath);
		System.out.println("******************************************" + file);
		return new FileInputStream(file);

	}

	public void setFilename(String filename) throws IOException {
		this.filename = new String(filename.getBytes("ISO-8859-1"), "utf-8");
	}

	public String getFilename() throws IOException {
		// 获取浏览器的类型
		String agent = ServletActionContext.getRequest()
				.getHeader("user-agent");
		return encode(filename, agent);
	}

	// 关于浏览器的解码问题
	public String encode(String fileName, String agent) throws IOException {
		// Firefox
		if (agent.contains("Firefox")) {
			fileName = "=?UTF-8?B?"
					+ new BASE64Encoder().encode(fileName.getBytes("utf-8"))
					+ "?=";

		} else {
			// IE等
			fileName = URLEncoder.encode(fileName, "utf-8");
		}
		return fileName;
	}

	public Long getFid() {
		return fid;
	}

	public void setFid(Long fid) {
		this.fid = fid;
	}

}