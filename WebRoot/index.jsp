<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	//重定向到loginUI页面，

	response.sendRedirect(request.getContextPath()+ "/user_loginUI.action");
// 		response.sendRedirect(request.getContextPath()+ "/home_index.action");
%>
