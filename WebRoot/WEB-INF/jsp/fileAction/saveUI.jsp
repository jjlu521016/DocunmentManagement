<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>上传实例</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/js/uploadify/css/uploadify.css" />
<script language="javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.8.3.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/uploadify/js/jquery.uploadify.js"></script>
<!-- 部分英文提示已经改成汉语的 -->
<script type="text/javascript">
	$(function() {
		$("#file")
				.uploadify(
						{
							'height' : 20,
							'width' : 80,
							'rollover' : true,//鼠标移到浏览按钮上后，会有翻转效果
							'buttonText' : '<font color="#FFFFFF">浏览文件</font>',
							'multi' : true,//是否支持多文件上传,默认为true
							'fileTypeDesc' : '支持类型', //文件类型描述   
							'fileTypeExts' : '*.*', //上传文件类型   
							'fileSizeLimit' : '3GB', //文件最大大小   1M
							'uploadLimit' : 999,//可同时上传文件个数
							'fileObjName' : 'file', //后台接受文件对象名，保持一致   
							'swf' : '${pageContext.request.contextPath}/uploadify/uploadify.swf?nowtime='
									+ (new Date()).getTime(), //确保上传一次后因为缓存问题不能再用
							'uploader' : '${pageContext.request.contextPath}/upload_uploadFile.action;jsessionid=${pageContext.session.id}',//配置表单上传请求路径，后面加jsessionid是处理session失效问题
							'auto' : false,//自动提交  
							'successTimeout' : 6000,//若文件太大可以设置以下步骤：1.此处配置时间长点；2.修改struts.xml，添加struts.multipart.maxSize的配置；3.修改服务器端可上传文件大小的限制，在文件/usr/local/nginx/conf/nginx.conf中修改client_max_body_size
							'removeCompleted' : true,
							// 上传时发生错误事件
							'onUploadError' : function(file, errorCode,
									errorMsg) {
								var msg = "服务器故障。";
								switch (errorCode) {
								case -100:
									msg = "上传的文件数量已经超出系统限制的"
											+ $('#file_upload').uploadify(
													'settings',
													'queueSizeLimit') + "个文件。";
									break;
								case -110:
									msg = "文件 ["
											+ file.name
											+ "] 大小超出系统限制的"
											+ $('#file_upload')
													.uploadify('settings',
															'fileSizeLimit')
											+ "大小。";
									break;
								case -120:
									msg = "文件 [" + file.name + "] 大小异常。";
									break;
								case -130:
									msg = "文件 [" + file.name + "] 类型不正确。";
									break;
								case -280:
									return;
								}
								alert(msg);
							},
							'onFallback' : function() { //flash报错触发   
								alert("请您先安装flash控件");
							},
							'onSelectError' : function(file, errorCode,
									errorMsg) { //file选择失败后触发   
								alert(errorMsg);
							},
							'onUploadStart' : function(file) {
								$("#file").uploadify("settings", "formData", {
									'folderId' : $("input:hidden").val()
								});
								//在onUploadStart事件中，也就是上传之前，把参数写好传递到后台。  
							},
							'onUploadSuccess' : function(file, data, response) { //上传成功后触发 
								var arr = data.split("^"); //字符分割
								if (arr[1] == "1000") {
									//alert("上传"+arr[0]+"文件成功");
								} else {
									alert(arr[2] + "：" + arr[3]);
								}
							},
							'onQueueComplete' : function() {//所有文件上传完成    
								alert("文件上传成功!");
							}
						});

		$("#up").click(function() {
			var uname = $("#uname").val();
			$("#file").uploadify("settings", "formData", {
				'uname' : uname
			});//向表单中注入参数
			var num = $("#file").data('uploadify').queueData.queueLength;//获取已加入上传队列的文件数
			if (num == 0) {
				alert("先选择待上传文件");
				return;
			}
			$('#file').uploadify('upload', '*');
		});
	});
</script>
</head>
<body>

	<s:hidden name="folderId" id="folderId"></s:hidden>
	<input type="text" name="uname" id="uname" />
	<!-- 用于上传时传递参数 -->
	<input type='file' name='file' id='file' />

	<div id="fileQueue"></div>
	<!--显示进度，这是必须的，不能去掉-->
	<div id="InputDetailBar">
		<input type="submit" id="up" value="上传">
		<!-- 		 self.opener.location.reload();history.back(-1); -->
		<a href="javascript:history.go(-1);"><input type="button"
			value="返回"></a>
	</div>
</body>
</html>