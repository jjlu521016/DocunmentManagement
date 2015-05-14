
// 注册函数
function regist() {

	var uname = document.regForm.username.value;
	var nickname = document.regForm.nickname.value;
	var psw = document.regForm.password.value;
	var psw2 = document.regForm.password2.value;
	var email = document.regForm.email.value;
	var sex = null;
	for(var i=0;i<document.regForm.sex.length;i++)
	{
		if(document.regForm.sex[i].checked)
		{
		  sex = document.regForm.sex[i].value;
		}
	}

	if (uname == "") {
		window.alert("用户名不能为空!");
		document.regForm.username.focus();
		return false;
	} else {
		if( nickname == "")
		{
			window.alert("昵称不能为空!");
			document.regForm.password.focus();
			return false;
			
		} else if (psw == "") {
			window.alert("密码不能为空!");
			document.regForm.password.focus();
			return false;
			
		} else {
			if (psw != psw2) {
				window.alert("密码不一致!");
				document.regForm.password2.focus();
				return false;
			} else {
				if (sex == "") {
					window.alert("性别不能为空!");
					return false;
				} else {
					if (email == "") {
						window.alert("邮箱不能为空!");
						document.regForm.email.focus();
						return false;
					} else {
						var url = "servlet/RegisterAction?username=" + uname + "&nickname=" + nickname + "&password=" + psw + "&password2=" + psw2 + "&sex=" + sex + "&email=" + email;
						CallAjax("get", url, "", processResponse);
					}
				}
			}
		}
	}
}

// 处理返回信息函数
function processResponse(xmlhttp) {
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
		window.alert("注册成功, 请登录");
		goToLoginPage();
	}
}


//跳转到登录页
function goToLoginPage() {
	self.location = "login.html";
}


