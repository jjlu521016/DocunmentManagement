
var XMLHttpReq;
//创建XMLHttpRequest对象       
function createXMLHttpRequest() {
	if (window.XMLHttpRequest) { //Mozilla 浏览器
		XMLHttpReq = new XMLHttpRequest();
	} else {
		if (window.ActiveXObject) { // IE浏览器
			try {
				XMLHttpReq = new ActiveXObject("Msxml2.XMLHTTP");
			}
			catch (e) {
				try {
					XMLHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
				}
				catch (e) {
				}
			}
		}
	}
}
	
//发送请求函数
function loginRequest(url) {
	createXMLHttpRequest();
	XMLHttpReq.open("GET", url, true);
	XMLHttpReq.onreadystatechange = processLoginResponse;//指定响应函数
	XMLHttpReq.send(null);  // 发送请求
}

// 处理身份验证返回信息函数
function processLoginResponse() {
	if (XMLHttpReq.readyState == 4) { // 判断对象状态
		if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
			var res = XMLHttpReq.responseXML.getElementsByTagName("res")[0].firstChild.nodeValue;
			if (res == 1) {
				window.alert("\u7528\u6237\u540d\u9519\u8bef\uff01");
			} else {
				if (res == 2) {
					window.alert("\u5bc6\u7801\u9519\u8bef\uff01");
				} else {
					if (res == 3) {
						window.alert("\u9a8c\u8bc1\u7801\u9519\u8bef\uff01");
					} else {
						if (res == 0) {
							var id = XMLHttpReq.responseXML.getElementsByTagName("id")[0].firstChild.nodeValue;
							window.location = "openBlog?blogid=" + id;
						}
					}
				}
			}
		} else { //页面不正常
			window.alert("\u60a8\u6240\u8bf7\u6c42\u7684\u9875\u9762\u6709\u5f02\u5e38\u3002");
		}
	}
}

// 身份验证函数
function userCheck() {
	uname = document.loginForm.uname.value;
	psw = document.loginForm.psw.value;
	checkwd = document.loginForm.checkwd.value;
	if (uname == "") {
		window.alert("\u7528\u6237\u540d\u4e0d\u80fd\u4e3a\u7a7a\u3002");
		document.loginForm.uname.focus();
		return false;
	} else {
		loginRequest("login?uname=" + uname + "&psw=" + psw + "&checkwd=" + checkwd);
	}
}

//跳转到注册页面函数
function goToRegisterPage() {
	self.location = "register.html";
}

