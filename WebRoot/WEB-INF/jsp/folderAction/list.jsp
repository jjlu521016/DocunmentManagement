<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title>文件夹</title>
<%@ include file="/WEB-INF/jsp/public/commons.jspf"%>
</head>
<body>

	<div id="Title_bar">
		<div id="Title_bar_Head">
			<div id="Title_Head"></div>
			<div id="Title">
				<!--页面标题-->
				<img border="0" width="13" height="13"
					src="${pageContext.request.contextPath}/style/images/title_arrow.gif" />
				文件夹管理
			</div>
			<div id="Title_End"></div>
		</div>
	</div>

	<div id="MainArea">
		<table cellspacing="0" cellpadding="0" class="TableStyle">

			<!-- 表头-->
			<thead>
				<tr align=center valign=middle id=TableTitle>
					<td width="150px">文件夹名称</td>
					<td width="150px">上级文件夹名称</td>
					<td width="200px">相关说明</td>
					<td>相关操作</td>
				</tr>
			</thead>

			<!--显示数据列表-->
			<tbody id="TableData" class="dataContainer" datakey="foldertList">

				<s:iterator value="#foldertList">
					<tr class="TableDetail1 template">
						<td><s:a action="folder_list?parentId=%{id}">${name}</s:a>&nbsp;</td>
						<td width="20%">${parent.name}&nbsp;</td>
						<td width="50%">${description}&nbsp;</td>
						<td width="10%"><s:a
								action="folder_delete?id=%{id}&parentId=%{parent.id}"
								onclick="return window.confirm('这将删除所有的文件夹，您确定要删除吗？')">删除</s:a>
							<s:a action="folder_editUI?id=%{id}">修改</s:a></td>
					</tr>
				</s:iterator>

			</tbody>
		</table>

		<!-- 其他功能超链接 -->
		<div id="TableTail">
			<div id="TableTail_inside">
				<s:a action="folder_addUI">
					<img
						src="${pageContext.request.contextPath}/style/images/createNew.png" />
				</s:a>
				<s:a action="folder_list?parentId=%{#parent.parent.id}">
					<img
						src="${pageContext.request.contextPath}/style/blue/images/button/ReturnToPrevLevel.png" />
				</s:a>
			</div>
		</div>
	</div>

</body>
</html>
