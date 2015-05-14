
var XMLHttpReq;
var userAviable;
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
function sendRequest(url) {
	createXMLHttpRequest();
	XMLHttpReq.open("GET", url, true);
	XMLHttpReq.onreadystatechange = processResponse;//指定响应函数
	XMLHttpReq.send(null);  // 发送请求
}

// 处理返回信息函数
function processResponse() {
	if (XMLHttpReq.readyState == 4) { // 判断对象状态
		if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
			var res = XMLHttpReq.responseText;
			if (res == 1) {
				window.alert("\u7528\u6237\u540d\u5df2\u7ecf\u88ab\u5360\u7528,\u8bf7\u66f4\u6362!");
				document.regForm.uname.focus();
				userAviable = false;
			} else {
				userAviable = true;
			}
		} else { //页面不正常
			window.alert("\u60a8\u6240\u8bf7\u6c42\u7684\u9875\u9762\u6709\u5f02\u5e38\u3002");
		}
	}
}
function checkUser() {
	var uname = document.regForm.uname.value;
	if (uname == "") {
		window.alert(document.regForm.uname.msg);
		document.regForm.uname.focus();
		return false;
	} else {
		sendRequest("checkUname?uname=" + uname);
	}
}

// 注册函数
function regist() {
	if (!userAviable) {
		return false;
	}
	var uname = document.regForm.uname.value;
	var psw = document.regForm.psw.value;
	var psw2 = document.regForm.psw2.value;
	var subject = document.regForm.subject.value;
	var email = document.regForm.email.value;
	if (uname == "") {
		window.alert(document.regForm.uname.msg);
		document.regForm.uname.focus();
		return false;
	} else {
		if (psw == "") {
			window.alert(document.regForm.psw.msg);
			document.regForm.psw.focus();
			return false;
		} else {
			if (psw != psw2) {
				window.alert(document.regForm.psw2.msg);
				document.regForm.psw2.focus();
				return false;
			} else {
				if (subject == "") {
					window.alert(document.regForm.subject.msg);
					document.regForm.subject.focus();
					return false;
				} else {
					if (email == "") {
						window.alert(document.regForm.email.msg);
						document.regForm.email.focus();
						return false;
					} else {
						return true;
					}
				}
			}
		}
	}
}

