
var XMLHttpReq;
//����XMLHttpRequest����       
function createXMLHttpRequest() {
	if (window.XMLHttpRequest) { //Mozilla �����
		XMLHttpReq = new XMLHttpRequest();
	} else {
		if (window.ActiveXObject) { // IE�����
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
	
//����������
function loginRequest(url) {
	createXMLHttpRequest();
	XMLHttpReq.open("GET", url, true);
	XMLHttpReq.onreadystatechange = processLoginResponse;//ָ����Ӧ����
	XMLHttpReq.send(null);  // ��������
}

// ���������֤������Ϣ����
function processLoginResponse() {
	if (XMLHttpReq.readyState == 4) { // �ж϶���״̬
		if (XMLHttpReq.status == 200) { // ��Ϣ�Ѿ��ɹ����أ���ʼ������Ϣ
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
		} else { //ҳ�治����
			window.alert("\u60a8\u6240\u8bf7\u6c42\u7684\u9875\u9762\u6709\u5f02\u5e38\u3002");
		}
	}
}

// �����֤����
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

//��ת��ע��ҳ�溯��
function goToRegisterPage() {
	self.location = "register.html";
}

