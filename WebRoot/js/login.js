

// ���������֤������Ϣ����
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

// �����֤����
function userCheck() {	
	var uname = document.loginForm.username.value;
	var psw = document.loginForm.password.value;
	if (uname == "") {
		window.alert("�û�������Ϊ��!");
		document.loginForm.username.focus();
		return false;
	} else {
		CallAjax("get", "servlet/LoginAction?username=" + uname + "&password=" + psw, "", processLoginResponse);
	}
}

//��ת��ע��ҳ�溯��
function goToRegisterPage() {
	self.location = "register.html";
}

//��ת���ҵ���ҳ
function goToMyIndex() {
	self.location = "docmanage.html";
}