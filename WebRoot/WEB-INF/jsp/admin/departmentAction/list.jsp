<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title>部门列表</title>
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
				院系、专业管理
			</div>
			<div id="Title_End"></div>
		</div>
	</div>

	<div id="MainArea">
		<table cellspacing="0" cellpadding="0" class="TableStyle">

			<!-- 表头-->
			<thead>
				<tr align=center valign=middle id=TableTitle>
					<td width="150px">院系、专业名称</td>
					<td width="150px">上级院系名称</td>
					<td width="200px">相关说明</td>
					<td>相关操作</td>
				</tr>
			</thead>

			<!--显示数据列表-->
			<tbody id="TableData" class="dataContainer" datakey="departmentList">

				<s:iterator value="#departmentList">
					<tr class="TableDetail1 template">
						<td><s:a action="department_list?parentId=%{id}">${name}</s:a>&nbsp;</td>
						<td width="20%">${parent.name}&nbsp;</td>
						<td width="50%">${description}&nbsp;</td>
						<td width="10%"><s:a
								action="department_delete?id=%{id}&parentId=%{parent.id}"
								onclick="return window.confirm('这将删除所有的下级部门，您确定要删除吗？')">删除</s:a>
							<s:a action="department_editUI?id=%{id}">修改</s:a></td>
					</tr>
				</s:iterator>

			</tbody>
		</table>

		<!-- 其他功能超链接 -->
		<div id="TableTail">
			<div id="TableTail_inside">
				<s:a action="department_addUI">
					<img
						src="${pageContext.request.contextPath}/style/images/createNew.png" />
				</s:a>
				<s:a action="department_list?parentId=%{#parent.parent.id}">
					<img
						src="${pageContext.request.contextPath}/style/blue/images/button/ReturnToPrevLevel.png" />
				</s:a>
			</div>
		</div>
	</div>

</body>
</html>
