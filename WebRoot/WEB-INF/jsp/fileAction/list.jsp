<%@ page language="java" pageEncoding="UTF-8"%>
<html>
<head>
	<title>文档管理</title>
	<%@ include file="/WEB-INF/jsp/public/commons.jspf"%>	
	<script type="text/javascript" src="${pageContext.request.contextPath}/script/jquery_treeview/jquery.cookie.js"></script>
</head>

	<frameset rows="*" framespacing=0 border=0 frameborder="0">
	<frameset cols="45%,*" id="resize">
			<frame noresize name="folder" scrolling="yes" style="margin:0;padding:4;" src="${pageContext.request.contextPath}/file_left.action">
			&bspn;&bspn;
			<frame noresize name="filelist" scrolling="yes" src="${pageContext.request.contextPath}/file_right.action">
	</frameset>
	</frameset>

	<noframes>
</noframes></html>



