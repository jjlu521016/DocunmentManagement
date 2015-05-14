
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
function loadRequest() {
	createXMLHttpRequest();
	var url = "first";
	XMLHttpReq.open("GET", url, true);
	XMLHttpReq.onreadystatechange = processLoadResponse;//指定响应函数
	XMLHttpReq.send(null);  // 发送请求
}
	// 处理返回信息函数
function processLoadResponse() {
	if (XMLHttpReq.readyState == 4) { // 判断对象状态
		if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
			AddBlogList();
			AddArticleList();
			setTimeout("loadRequest()", 10000);
		} else { //页面不正常
			window.alert("\u60a8\u6240\u8bf7\u6c42\u7684\u9875\u9762\u6709\u5f02\u5e38\u3002");
		}
	}
}
	// 增加Blog函数
function AddBlogList() {
	deleteBlogList();
	var xmlDoc = XMLHttpReq.responseXML;
	var blogs = xmlDoc.getElementsByTagName("blog");
	var currentBlog = null;
	for (var i = 0; i < blogs.length; i++) {
		currentBlog = blogs[i];
		var id = currentBlog.getElementsByTagName("id")[0].firstChild.nodeValue;
		var name = currentBlog.getElementsByTagName("name")[0].firstChild.nodeValue;
		var row = document.createElement("tr");
		row.setAttribute("id", id);
		var cell = document.createElement("td");
		cell.appendChild(document.createTextNode("NO " + (i + 1) + "."));
		row.appendChild(cell);
		cell = document.createElement("td");
		var href = document.createElement("a");
		href.setAttribute("href", "openBlog?blogid=" + id);
		href.appendChild(document.createTextNode(name));
		cell.appendChild(href);
		row.appendChild(cell);
		document.getElementById("blogList").appendChild(row);
	}
}

	// 增加Article函数
function AddArticleList() {
	deleteArticleList();
	var xmlDoc = XMLHttpReq.responseXML;
	var articles = xmlDoc.getElementsByTagName("article");
	var currentArticle = null;
	for (var i = 0; i < articles.length; i++) {
		currentArticle = articles[i];
		var id = currentArticle.getElementsByTagName("id")[0].firstChild.nodeValue;
		var name = currentArticle.getElementsByTagName("title")[0].firstChild.nodeValue;
		var time = currentArticle.getElementsByTagName("time")[0].firstChild.nodeValue;
		var row = document.createElement("tr");
		row.setAttribute("id", id);
		var cell = document.createElement("td");
		var href = document.createElement("a");
		href.setAttribute("href", "openArticle?articleid=" + id);
		href.appendChild(document.createTextNode(name));
		cell.appendChild(href);
		row.appendChild(cell);
		cell = document.createElement("td");
		cell.appendChild(document.createTextNode(time));
		cell.setAttribute("width", 110);
		row.appendChild(cell);
		document.getElementById("articleList").appendChild(row);
	}
}
	// 删除Blog函数
function deleteBlogList() {
	var blogList = document.getElementById("blogList");
	while (blogList.hasChildNodes()) {
		blogList.removeChild(blogList.firstChild);
	}
}
	
	// 删除Article函数
function deleteArticleList() {
	var articleList = document.getElementById("articleList");
	while (articleList.hasChildNodes()) {
		articleList.removeChild(articleList.firstChild);
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
	// 博客身份验证函数
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

