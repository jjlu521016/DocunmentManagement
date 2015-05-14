<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<title>TopMenu</title>
<%@ include file="/WEB-INF/jsp/public/commons.jspf"%>
<LINK href="${pageContext.request.contextPath}/style/blue/top.css"
	type=text/css rel=stylesheet>

<script type="text/javascript">
	
</script>
<style type="text/css">
#messageArea {
	color: white;
	font-size: 14px;
	font-weight: bold;
}
</style>


</head>

<body CLASS=PageBody leftmargin=0 topmargin=0 marginwidth=0
	marginheight=0>
	<div id="Head1">
		<div id="Logo">
			<iframe name="autoRefashion" src="" width="0" height="0"></iframe>
			<a id="msgLink" href="javascript:void(0)"></a> <font color="#0000CC"
				style="color: #F1F9FE; font-size: 28px; font-family: Arial Black, Arial">简单文档管理系统</font>
			<!--<img border="0" src="css/blue/images/logo.png" />-->

		</div>
		<div id="Head1Right">
			<div id="Head1Right_UserName">
				<img border="0" width="13" height="14"
					src="${pageContext.request.contextPath}/style/images/top/user.gif" />
				您好，<b>${user.name }</b>
			</div>

			<div id="Head1Right_UserDept"></div>
			<div id="Head1Right_UserSetup">

				<a href="#"><img border="0" width="13" height="14"
					src="${pageContext.request.contextPath}/style/images/top/user_setup.gif" />个人设置</a>
			</div>
			<div id="Head1Right_Time"></div>
		</div>
		<div id="Head1Right_SystemButton">
			<a href="${pageContext.request.contextPath}/user_logout.action"
				target="_parent"> <img width="78" height="20" alt="退出系统"
				src="${pageContext.request.contextPath}/style/blue/images/top/logout.gif" />
			</a>
		</div>
		<!-- 		<div id="Head1Right_Button"> -->
		<!-- 			<a target="desktop" href="javascript:void(0)"><img width="65" -->
		<!-- 				height="20" alt="显示桌面" -->
		<%-- 				src="${pageContext.request.contextPath}/style/blue/images/top/desktop.gif" /></a> --%>
		<!-- 		</div> -->
	</div>



	<div id="Head2_FunctionList" style="text-align: left">
		<a href="javascript: window.parent.right.location.reload(true);">刷新</a>
	</div>
	</div>

</body>

</html>
