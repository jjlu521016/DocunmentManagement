<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>角色列表</title>
    <%@ include file="/WEB-INF/jsp/public/commons.jspf" %>
</head>
<body>
 
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 角色管理
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<div id="MainArea">
    <table cellspacing="0" cellpadding="0" class="TableStyle">
       
        <!-- 表头-->
        <thead>
            <tr align="CENTER" valign="MIDDLE" id="TableTitle">
            	<td width="200px">角色名称</td>
                <td width="300px">角色说明</td>
                <td>相关操作</td>
            </tr>
        </thead>

		<!--显示数据列表-->
        <tbody id="TableData" class="dataContainer" datakey="roleList">
        
        <s:iterator value="recordList">
			<tr class="TableDetail1 template">
				<td>${name}&nbsp;</td>
				<td width="70%">${description}&nbsp;</td>
				<td width="10%">
					<s:a action="role_delete?id=%{id}" onclick="return confirm('确定要删除吗？')">删除</s:a>
					<s:a action="role_editUI?id=%{id}">修改</s:a>
					<s:a action="role_setPermissionUI?id=%{id}">设置权限</s:a>
				</td>
			</tr>
        </s:iterator>
 
        </tbody>
    </table>
    
    <!-- 其他功能超链接 -->
    <div id="TableTail">
		<div id="TableTail_inside">
			<s:a action="role_addUI"><img src="${pageContext.request.contextPath}/style/images/createNew.png" /></s:a>
        </div>
    </div>
    
    <!-- 分页信息 -->
<%@ include  file="/WEB-INF/jsp/public/pageView.jspf"%>
<s:form action="user_list"></s:form>
</div>
</body>
</html>
