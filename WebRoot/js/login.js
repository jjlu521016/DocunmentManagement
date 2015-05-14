

// 处理身份验证返回信息函数
function processLoginResponse(xmlhttp) {
	var xmlDoc = xmlhttp.responseXML;
	var obj = xmlDoc.getElementsByTagName("error")[0];
	if(obj == null)
	{
		obj = xmlDoc.getElementsByTagName("user")[0];
	}
	var type = obj.nodeName;
		
	if(type == "error")
		window.alert(obj.childNodes[0].nodeValue);
	else  if(type == "user")
	{
		goToMyIndex();
	}
}

// 身份验证函数
function userCheck() {	
	var uname = document.loginForm.username.value;
	var psw = document.loginForm.password.value;
	if (uname == "") {
		window.alert("用户名不能为空!");
		document.loginForm.username.focus();
		return false;
	} else {
		CallAjax("get", "servlet/LoginAction?username=" + uname + "&password=" + psw, "", processLoginResponse);
	}
}

//跳转到注册页面函数
function goToRegisterPage() {
	self.location = "register.html";
}

//跳转到我的首页
function goToMyIndex() {
	self.location = "docmanage.html";
}