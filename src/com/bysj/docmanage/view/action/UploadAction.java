package com.bysj.docmanage.view.action;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.bysj.docmanage.base.BaseAction;
import com.bysj.docmanage.domain.Files;
import com.bysj.docmanage.domain.Folder;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class UploadAction extends BaseAction<Files> {
	private static final long serialVersionUID = 4223737080271920987L;
	private Logger logger = Logger.getLogger(UploadAction.class);
	private File file;
	// 获取文件名
	private String fileFileName;
	
	private Long folderId;
	private Long fid;

	public String list() throws Exception {
		List<Folder> folderlist = folderService.findAll();
		List<Files> filelist = fileService.findAll();

		ActionContext.getContext().put("folderlist", folderlist);
		ActionContext.getContext().put("filelist", filelist);
		return "list";
	}

	/**
	 * 上传文件
	 * 
	 * @return
	 * @throws Exception
	 */
	public String uploadFile() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		System.out.println("........>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>="+folderId);
		System.out.println("........>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>="+request.getParameter("folderId"));
		//根据文件夹的id获取文件夹实体
		Folder folder = folderService.getById(folderId);

		
		String savePath = ServletActionContext.getServletContext().getRealPath("");
		  
		  savePath = savePath +"\\uploads\\"+ fileFileName;;
		  
		System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$---"
				+ savePath);


		// 获取文件的扩展名
		String newsuffix = "";
		// 获取文件的大小：以KB标识
		long kb = 1024;
		String fileLongth = String
				.format("%.1f KB", (float) file.length() / kb);
		if ((fileFileName != null) && (fileFileName.length() > 0)) {
			int dot = fileFileName.lastIndexOf(".");
			if ((dot > -1) && (dot < (fileFileName.length() - 1))) {
				newsuffix = fileFileName.substring(dot + 1);
			}
		}
		/**
		// System.out.println("------->>" + fileFileName);
		// System.out.println("-------newsuffix----" + newsuffix);
		// System.out.println("----------****-------savePath---" + savePath);
		// System.out.println("-----newfilepath--->" + newfilepath);
		// System.out.println("---------------------->>>" + fileLongth);
		 * 
		 */
		// 将文件信息保存到数据库里面
		Files files = new Files();
		files.setFileName(fileFileName);
		files.setTypes(newsuffix);
		files.setFilePath(savePath);
		files.setAuthor(getCurrentUser());
		files.setSubmitdate(new Date());
		files.setFilelength(fileLongth);
		files.setFolder(folder);
		fileService.save(files);

		// 下面为处理uploadify插件里面上传文件的信息
		Map<String, String> map = this.saveHasBillImg(savePath);
		String errorcode = map.get("errorcode");
		String errormsg = map.get("errormsg");
		logger.info("文件上传【" + fileFileName + "】结果" + errorcode + ":" + errormsg);
		String result = fileFileName + "^" + errorcode + "^" + errormsg;
		logger.info("返回的字符串为：" + result);
		HttpServletResponse response = ServletActionContext.getResponse();

		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = response.getWriter();
		pw.write(result);
		pw.flush();
		pw.close();
		return null;
	}

	public Map<String, String> saveHasBillImg(String savePath) throws IOException {
		Map<String, String> map = new HashMap<String, String>();
		map.put("errorcode", "1000");
		map.put("errormsg", "上传文件成功");
		try {
			logger.info("file=" + file);
			if (file == null || !file.exists() || !file.isFile()) {
				String msg = "文件未成功上传至服务器";
				map.put("errorcode", "1001");
				map.put("errormsg", msg);
				return map;
			}

			logger.info("newfilepath=" + savePath);
			System.out.println("0000000==file=" + file);
			System.out.println("0000000==savePath=" + savePath);
			// 将上传的文件保存到新路径里面
			FileUtils.copyFile(file, new File(savePath));
		} catch (Exception e) {
			String msg = "上传文件有异常";
			map.put("errorcode", "1002");
			map.put("errormsg", msg);
			logger.error(msg, e);
			System.out.println("--------------message-"+msg+"---error="+msg);
		}
		return map;
	}

	/**
	 * 删除上传的文件
	 * 
	 * @return
	 * @throws Exception
	 */
	public String deletefile() throws Exception {
		// String fileName = new
		// String(request.getParameter("fileName").getBytes("ISO-8859-1"),"utf-8");
		String path = fileService.getPath(fid);
		String fileName = fileService.getFileName(fid);
		// 由于存储的文件路径包含文件名，所以要把文件名去掉！
		// 取到最后一个“\\”
		int lastIndex = path.lastIndexOf("\\");
		String documentpath = path.substring(0, lastIndex + 1);
System.out.println("---------------documentpath="+documentpath);
		File dir = new File(documentpath);
		File file[] = dir.listFiles();
		for (int i = 0; i < file.length; i++) {
			System.out.println(";;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;"
					+ file[i].getName());
			System.out.println("ppppppppp=========" + fileName);
			if (file[i].getName().equals(fileName)) {
				// 删除本地文件
				file[i].delete();
				// 删除数据库的本条记录
				fileService.delete(fid);
				System.out.println("pppppppppppppppppppppp========="
						+ fileFileName);
			}
		}

		return "deletefile";
	}

	
	// Setter方法
	public void setFile(File file) {
		this.file = file;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public Long getFolderId() {
		return folderId;
	}

	public void setFolderId(Long folderId) {
		this.folderId = folderId;
	}

	public Long getFid() {
		return fid;
	}

	public void setFid(Long fid) {
		this.fid = fid;
	}

}