<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title>配置权限</title>
<%@ include file="/WEB-INF/jsp/public/commons.jspf"%>

<script language="javascript"
	src="${pageContext.request.contextPath}/script/jquery_treeview/jquery.treeview.js"></script>
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/style/blue/file.css" />
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/script/jquery_treeview/jquery.treeview.css" />

<script type="text/javascript">
	$(function() {
		// 指定事件处理函数
		$("[name=permissionIds]").click(function() {

			// 当选中或取消一个权限时，也同时选中或取消所有的下级权限
			$(this).siblings("ul").find("input").attr("checked", this.checked);

			// 当选中一个权限时，也要选中所有的直接上级权限
			if (this.checked == true) {
				$(this).parents("li").children("input").attr("checked", true);
			}

		});
	});
</script>
</head>
<html>
<body>


</body>
</html>

<!-- 标题显示 -->
<div id="Title_bar">
	<div id="Title_bar_Head">
		<div id="Title_Head"></div>
		<div id="Title">
			<!--页面标题-->
			<img border="0" width="13" height="13"
				src="${pageContext.request.contextPath}/style/images/title_arrow.gif" />
			配置权限
		</div>
		<div id="Title_End"></div>
	</div>
</div>

<div id=MainArea>
	<s:form action="role_setPermission">
		<s:hidden name="id"></s:hidden>
		<div class="ItemBlock_Title1">
			<div class="ItemBlock_Title1">
				<img border="0" width="4" height="7"
					src="${pageContext.request.contextPath}/style/blue/images/item_point.gif" />
				正在为[ <font color="red">${name} </font> ] 配置权限
			</div>
		</div>


		<!-- 表单内容显示 -->
		<div class="ItemBlockBorder">
			<div class="ItemBlock">
				<table cellpadding="0" cellspacing="0" class="mainForm">
					<!--表头-->
					<thead>
						<tr align="LEFT" valign="MIDDLE" id="TableTitle">
							<td width="300px" style="padding-left: 7px;">
								<!-- 如果把全选元素的id指定为selectAll，并且有函数selectAll()，就会有错。因为有一种用法：可以直接用id引用元素 -->
								<input type="checkbox" id="cbSelectAll"
								onClick="$('[name=permissionIds]').attr('checked', this.checked)" />
								<label for="cbSelectAll">全选</label>
							</td>
						</tr>
					</thead>

					<!--显示数据列表-->
					<tbody id="TableData">
						<tr class="TableDetail1">
							<!-- tree -->
							<td>

								<ul id="tree">
									<s:iterator value="#application.topPermissionList">

										<li><input type="checkbox" name="permissionIds"
											value="${id}" id="cb_${id}"
											<s:property value="%{id in permissionIds ? 'checked' : ''}"/> />
											<label for="cb_${id}"><span class="folder">${name}</span></label>
										</li>

										<ul>
											<s:iterator value="children">
												<li><input type="checkbox" name="permissionIds"
													value="${id}" id="cb_${id}"
													<s:property value="%{id in permissionIds ? 'checked' : ''}"/> />
													<label for="cb_${id}"><span class="folder">${name}</span></label>
													<ul>
														<s:iterator value="children">
															<li><input type="checkbox" name="permissionIds"
																value="${id}" id="cb_${id}"
																<s:property value="%{id in permissionIds ? 'checked' : ''}"/> />
																<label for="cb_${id}"><span class="folder">${name}</span></label>
															</li>
														</s:iterator>
													</ul></li>
											</s:iterator>
										</ul>
									</s:iterator>

								</ul>
							</td>
						</tr>

					</tbody>
				</table>
			</div>
		</div>

		<script language="javascript">
			$("#tree").treeview();
		</script>

		<!-- 表单操作 -->
		<div id="InputDetailBar">
			<input type="image"
				src="${pageContext.request.contextPath}/style/images/save.png" /> <a
				href="javascript:history.go(-1);"><img
				src="${pageContext.request.contextPath}/style/images/goBack.png" /></a>
		</div>

	</s:form>

</div>

