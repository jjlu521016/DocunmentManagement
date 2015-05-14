<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title>文件重命名</title>
<%@ include file="/WEB-INF/jsp/public/commons.jspf"%>

<script type="text/javascript">
</script>
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
				移动文件
			</div>
			<div id="Title_End"></div>
		</div>
	</div>

	<!--显示表单内容-->
	<div id="MainArea">

		<s:form action="file_rename?fid=%{fileId}">
				<s:hidden name="fileId"></s:hidden>
			<s:hidden name="filePath"></s:hidden>
			<s:hidden name="types"></s:hidden>
			<s:hidden name="filelength"></s:hidden>
			<!-- 表单内容显示 -->
			<div class="ItemBlockBorder">
				<div class="ItemBlock">
					<table cellpadding="0" cellspacing="0" class="mainForm">
						<tr>
							<td width="100">文件名：</td>
							<td><s:textfield name="fileName" cssClass="InputStyle" /> *</td>
						</tr>
					<tr><td width="20%">所在文件夹：</td>
                    <td> 
                    <s:select name="folderId" cssClass="selectStyle" list="#folderlist" 
                    listKey="id" listValue="name" headerKey="" headerValue="==请选择文件夹==" >
                    </s:select>
                    </tr>
					</table>
				</div>
			</div>
			<!-- 表单操作 -->
			<div id="InputDetailBar">
				<input type="image"
					src="${pageContext.request.contextPath}/style/images/save.png" /> <a
					href="javascript:history.go(-1);"><img
					src="${pageContext.request.contextPath}/style/images/goBack.png" /></a>
			</div>
		</s:form>
	</div>

</body>
</html>
