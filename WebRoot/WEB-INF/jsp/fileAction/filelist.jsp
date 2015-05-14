<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>文件列表</title>
<%@ include file="/WEB-INF/jsp/public/commons.jspf"%>
<script type="text/javascript"> </script>
</head>
<body>

	<div id="Title_bar">
		<div id="Title_bar_Head">
			<div id="Title_Head"></div>
			<div id="Title">
				<!--页面标题-->
				<img border="0" width="13" height="13"
					src="${pageContext.request.contextPath}/style/images/title_arrow.gif" />
				<font color="red">【${folder.name}】目录下的所有文件</font>
				  <a href="javascript:location.reload();"><input type="button"
			value="刷新"></a>
			</div>
			 
			<div id="Title_End"></div>
			
		</div>
	</div>

	<div id="MainArea">
		<table cellspacing="0" cellpadding="0" class="TableStyle">

			<!-- 表头-->
			<thead>
				<tr align="CENTER" valign="MIDDLE" id="TableTitle">
					<td width="32%">文件名称</td>
<!-- 					<td width="8%">文件类型</td> -->
					<td width="10%">文件大小</td>
					<td width="10%">上传者</td>
					<td width="20%">上传时间</td>
					<td width="30%">相关操作</td>
				</tr>
			</thead>

			<!--显示数据列表-->
			<tbody id="TableData" class="dataContainer" datakey="filelist">
				<s:hidden name="folderId"></s:hidden>
				<s:iterator value="recordList">
				<s:hidden name="fileId"></s:hidden>
					<tr class="TableDetail1 template">
						<td>${fileName}&nbsp;</td>
<%-- 						<td>${types}&nbsp;</td> --%>
						<td>${filelength}&nbsp;</td>
						<td>${author.name}&nbsp;</td>
						<td>${submitdate}&nbsp;</td>
						<td width="10%"><s:a
								action="upload_deletefile?fid=%{fileId}"
								onclick="return confirm('确定要删除吗？')">删除</s:a> <s:a
								action="downloadFile?fid=%{fileId}&filename=%{fileName}">下载</s:a>
							<s:a action="file_removeUI?fid=%{fileId}">移动</s:a> <s:a
								action="file_copyUI?fid=%{fileId}">复制</s:a> 
							<s:a action="file_renameUI?fid=%{fileId}">重命名</s:a>	
						</td>
					</tr>
				</s:iterator>

			</tbody>
		</table>

		<!-- 其他功能超链接 -->
		<div id="TableTail">
			<div id="TableTail_inside">
				<s:a action="file_addUI?folderId=%{folderId}">
					<img
						src="${pageContext.request.contextPath}/style/images/createNew.png" />
				</s:a>
			</div>
		</div>

		<!-- 分页信息 -->
		<%@ include file="/WEB-INF/jsp/public/pageView.jspf"%>
		<s:form action="file_filelist"></s:form>
	</div>
</body>
</html>