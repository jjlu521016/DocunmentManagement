<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
<title>用户信息</title>
<%@ include file="/WEB-INF/jsp/public/commons.jspf"%>
</head>
<body>

	<!-- 标题显示 -->
	<div id="Title_bar">
		<div id="Title_bar_Head">
			<div id="Title_Head"></div>
			<div id="Title">
				<!--页面标题-->
				<img border="0" width="13" height="13"
					src="${pageContext.request.contextPath}/style/images/title_arrow.gif" />
				用户信息
			</div>
			<div id="Title_End"></div>
		</div>
	</div>

	<!--显示表单内容-->
	<div id=MainArea>

		<s:form action="user_%{id == null ? 'add' : 'edit'}">
			<s:hidden name="id"></s:hidden>
			<s:hidden name="password"></s:hidden>
			
			<s:hidden name="departmentId"></s:hidden>
			<s:hidden name="roleIds"></s:hidden>
						
						
			<div class="ItemBlock_Title1">
				<!-- 信息说明 -->
				<div class="ItemBlock_Title1">
					<img border="0" width="4" height="7"
						src="${pageContext.request.contextPath}/style/blue/images/item_point.gif" />
					用户信息
				</div>
			</div>

			<!-- 表单内容显示 -->
			<div class="ItemBlockBorder">
				<div class="ItemBlock">
					<table cellpadding="0" cellspacing="0" class="mainForm">

						<tr>
							<td>登录名</td>
							<td><s:textfield name="loginName" cssClass="InputStyle" />
								<font color="red">*</font></td>
						</tr>
						<tr>
							<td>姓名</td>
							<td><s:textfield name="name" cssClass="InputStyle" /> <font
								color="red">*</font></td>
						</tr>
						<tr>
							<td>性别</td>
							<td><s:radio name="gender" list="{'男', '女'}"></s:radio></td>
						</tr>
						<tr>
							<td>联系电话</td>
							<td><s:textfield name="phoneNumber" cssClass="InputStyle" /></td>
						</tr>
						<tr>
							<td>E-mail</td>
							<td><s:textfield name="email" cssClass="InputStyle" /></td>
						</tr>
						<tr>
							<td>备注</td>
							<td><s:textarea name="description" cssClass="TextareaStyle"></s:textarea></td>
						</tr>
					</table>
				</div>
			</div>

			<!-- 表单操作 -->
			<div id="InputDetailBar">
				<input type="image"
					src="${pageContext.request.contextPath}/style/images/save.png" />
				<a href="javascript:history.go(-1);"><img
					src="${pageContext.request.contextPath}/style/images/goBack.png" /></a>
			</div>
		</s:form>
	</div>

</body>
</html>
