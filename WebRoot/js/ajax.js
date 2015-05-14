
function AJAXRequest() {
    var xmlhttp = false;
    var self = this;
	this.asynchronous = true; //是否异步访问
    this.method = "";
    this.url = "";
    this.content = "";
    this.callback = function(obj) { return; }
	
    if (window.XMLHttpRequest) {
        xmlhttp = new XMLHttpRequest();
    } else if (window.ActiveXObject) {
        try {
            xmlhttp = new ActiveXObject("msxml2.XMLHTTP");
        } catch (e) {
            try {
                xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
            } catch (e) { }
        }
    }
    if (!xmlhttp) {
        alert("XMLHttpRequest创建失败");
        return false;
    }
    this.send = function() {
		
        if (!this.method || !this.url) { return false; }	
		this.url = encodeURI(this.url);
		//window.alert(this.url + "&t=" + Math.random());
        xmlhttp.open(this.method, this.url + "&t=" + Math.random(), this.asynchronous);
		
		if (this.method == "post") {          	
		   	xmlhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");   		
        }
		
        xmlhttp.onreadystatechange = function() {
            if (xmlhttp.readyState == 4) {
                if (xmlhttp.status == 200) {
                    self.callback(xmlhttp);
                } else {
                    alert("请求失败!");
                }
            }
        }
        if (this.method == "post") {
            xmlhttp.send(encodeURI(this.content) + "&t=" + Math.random());
        } else {
            xmlhttp.send(null);
        }
    }
}

//main method  
function CallAjax(method, url, content, callback, asynchronous) {
    var ajax_obj = new AJAXRequest();
    ajax_obj.method = method;
    ajax_obj.url = url;
	ajax_obj.content = content;
	ajax_obj.asynchronous = asynchronous;
    //ajax_obj.callback = function(xmlhttp) {
     //   alert(xmlhttp.responseText);//����ֵ
    //}
	ajax_obj.callback = callback;
    ajax_obj.send();
}