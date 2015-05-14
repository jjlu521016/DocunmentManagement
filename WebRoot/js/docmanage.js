var currentFolderId = -1;
var currentFolderName = "";
var formerClickedFolder = null;


//��ȡ�û���Ϣ
function getUserInfo()
{
	CallAjax("get", "servlet/GetUserInfoAction?abc=abc", "", onGotUserInfo, false); //同步
}

//
function onGotUserInfo(xmlhttp) {
	var xmlDoc = xmlhttp.responseXML;
	var obj = xmlDoc.getElementsByTagName("error")[0];
	if(obj == null)
	{
		obj = xmlDoc.getElementsByTagName("user")[0];
	}
	var type = obj.nodeName;
	//window.alert("type:" + type);
	if(type == "error")
	{	
		window.alert(obj.childNodes[0].nodeValue);
		self.location = "login.html";
	}
	else  if(type == "user")
	{
		var username = obj.childNodes[0].childNodes[0].nodeValue;
		var nickname = obj.childNodes[2].childNodes[0].nodeValue;
		var sex = obj.childNodes[3].childNodes[0].nodeValue;
		var email = obj.childNodes[4].childNodes[0].nodeValue;
		
		//window.alert(username + " " + nickname + " " + sex + " " + email);
		//����
		document.getElementById("username").innerHTML = username;
		document.getElementById("nickname").innerHTML = nickname;
		document.getElementById("sex").innerHTML = sex;
		document.getElementById("email").innerHTML = email;
	}
}



//��ȡ�û������ļ���
function getUserAllFolderList()
{
	//此处采用同步访问
	CallAjax("get", "servlet/FolderListAction?abc=abc", "", onGotUserFolderList, false);
}


//
function onGotUserFolderList(xmlhttp) {
	
	//window.alert(xmlhttp.responseText);
	var xmlDoc = xmlhttp.responseXML;
	//window.alert(xmlhttp.responseText);
	var obj = xmlDoc.getElementsByTagName("error")[0];
	if(obj == null)
	{
		obj = xmlDoc.getElementsByTagName("root")[0];
	}
	var type = obj.nodeName;
		
	if(type == "error")
		window.alert(obj.childNodes[0].nodeValue);
	else  if(type == "root")
	{
		if( ! obj.hasChildNodes())
		{
			document.getElementById("folderContent").innerHTML = "<font color=red>Don't Have Any Folder!</font>";
			return;
		}
		
		var folderRoot = document.createElement("ul");
		
		for( var i = 1; i < xmlDoc.firstChild.childNodes.length; i++)
		{
			createFolderTree(xmlDoc.firstChild.childNodes[i], folderRoot);
		}
		
		var folderContent = document.getElementById("folderContent");
		while (folderContent.hasChildNodes()) {
			folderContent.removeChild(folderContent.firstChild);
		}
		
		document.getElementById("folderContent").appendChild(folderRoot);
		
		//window.alert(document.getElementById("folderContent").innerHTML);

	}
}

//�ݹ鴴���ļ�����״�б� currentFolderElementΪ���XML�ĵ�ǰԪ��  parentNodeΪHTMLԪ�صĸ��ڵ�
function createFolderTree(currentFolderElement, parentNode)
{
	var folder = currentFolderElement;
	var id = folder.getAttribute("id");
	var name = folder.getAttribute("name");
	var subFolderNum = folder.getAttribute("subFolderNum");
	var fileNum = folder.getAttribute("fileNum");
	var capacity = folder.getAttribute("capacity");
	var shared = folder.getAttribute("shared");
	var createTime = folder.getAttribute("createTime");
					
	var newLI = document.createElement("li");
	//ֱ�ӵ���class����
	//newLI.setAttribute("class", "folderClosed1");
	//ע��ͼƬ·������
	//newLI.style.background = "transparent url(./images/mi.gif) no-repeat";
	
	newLI.setAttribute("id", "folder" + id);
	newLI.setAttribute("folderId", id);
	newLI.setAttribute("name", name)
	newLI.setAttribute("subFolderNum", subFolderNum);
	newLI.setAttribute("fileNum", fileNum);
	newLI.setAttribute("capacity", capacity);
	newLI.setAttribute("shared", shared);
	newLI.setAttribute("createTime", createTime);
	newLI.setAttribute("isFolded", false);
	
	var newA = document.createElement("a");
	var plusImg = document.createElement("img");
	var folderImg = document.createElement("img");
	
	plusImg.src = "./images/mi.gif";
	plusImg.setAttribute("type", "plusImg");
	//plusImg.addEventListener("click", fold ,false);
	//plusImg.setAttribute("onclick", "fold(event)");
	
	folderImg.setAttribute("type", "folderImg");
	if(shared == "true")
	{
		//newA.setAttribute("class", "sharedFolder");
		//newA.style.background  = "transparent url(./images/share_files.png) no-repeat";
		folderImg.src = "./images/share_files.png";
	}
	else
	{
		//newA.style.background  = "transparent url(./images/folder1.gif) no-repeat";
		folderImg.src = "./images/folder1.gif";
	}

	newA.setAttribute("href", "#");
	//newA.addEventListener("click", changeFolder, false);
	newA.setAttribute("onclick", "changeFolder(event)");
	newA.onclick = changeFolder;
	newA.appendChild(plusImg);
	newA.appendChild(folderImg);
	newA.appendChild(document.createTextNode(name));
	
	newLI.appendChild(newA);	
	parentNode.appendChild(newLI);
	
	if(currentFolderElement.hasChildNodes())
	{
		var newUL = document.createElement("ul");
		newUL.setAttribute("id", id + "menu");
		//newUL.setAttribute("class", "folderClosed2");
		//newUL.style.display = "block";
		
		for( var i = 0; i < currentFolderElement.childNodes.length; i++)
		{
			createFolderTree(currentFolderElement.childNodes[i], newUL);
		}
		parentNode.appendChild(newUL);
	}
	
}

function changeFolder(e)
{
	
	if (!e) var e = window.event;
	var obj = e.target ?  e.target : e.srcElement;	
	
	if(obj.nodeName == "IMG" && obj.getAttribute("type") == "plusImg")
	{
		fold(e);
		return;
	}
	
	
	var parNode = obj.parentNode;
	if(obj.nodeName == "IMG")
		parNode = parNode.parentNode;
	
	var folderId = parNode.getAttribute("folderId");
	currentFolderId = folderId;  //VIP
	changeFolderById(folderId, false);
	/*
	parNode.style.backgroundColor = "#ecf2fe";
	parNode.style.border = "1px solid #cccccc";
	if(formerClickedFolder != null && formerClickedFolder != parNode)
	{
		formerClickedFolder.style.backgroundColor = "transparent";
		formerClickedFolder.style.border = "none";
	}
	formerClickedFolder = parNode;
	
	currentFolderName = parNode.getAttribute("name");
	updateFolderMsg(parNode);
	updatePath(parNode);
	//window.alert(folderId);
	getUserFileListByFolder(folderId);
	*/
}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
function changeFolderById(folderId, updateFolderList)
{
	if(updateFolderList == true)
	{	
		getUserAllFolderList();
		//window.alert(document.getElementById("folderContent").innerHTML);
		formerClickedFolder = null;
	}
	
	//window.alert(folderId);
	var currentFolder = document.getElementById("folder" + folderId);
	currentFolderId = folderId;
	
	if(currentFolder == null)
	{
		//window.alert("currentFolder is null");
		return;
	}
	//window.alert(folderId);
	
	currentFolder.style.backgroundColor = "#ecf2fe";
	currentFolder.style.border = "1px solid #cccccc";
	
	if(formerClickedFolder != null && formerClickedFolder != currentFolder)
	{
		formerClickedFolder.style.backgroundColor = "transparent";
		formerClickedFolder.style.border = "none";
	}
	formerClickedFolder = currentFolder;
	
	currentFolderName = currentFolder.getAttribute("name");
	updateFolderMsg(currentFolder);
	updatePath(currentFolder);
	//window.alert(folderId);
	getUserFileListByFolder(folderId);
	
}

//�۵��ļ���
function fold(e) {
	if (!e) var e = window.event;
	var obj = e.target ?  e.target : e.srcElement;	
	//window.alert( obj.nodeName);
	var parNode = obj.parentNode.parentNode;
	var folderId = parNode.getAttribute("folderId");
	var isFolded  = parNode.getAttribute("isFolded");
	var subFolderNum = parNode.getAttribute("subFolderNum");

	if(isFolded)
	{
		obj.src = "./images/pl.gif";
		if(subFolderNum != 0)
			document.getElementById(folderId + "menu").style.display = "none";
		
	}
	else
	{
		obj.src = "./images/mi.gif";
		if(subFolderNum != 0)
			document.getElementById(folderId + "menu").style.display = "block";
	}
	
	parNode.setAttribute("isFolded", isFolded ? false : true);	
}

//��ȡ�û�ָ���ļ��е��ļ�
function getUserFileListByFolder(folderId)
{
	CallAjax("get", "servlet/FileListAction?folderId=" + folderId , "", onGotFileList, true);
}

function onGotFileList(xmlhttp)
{
	var xmlDoc = xmlhttp.responseXML;
	//window.alert(xmlhttp.responseText);
	
	var obj = xmlDoc.getElementsByTagName("error")[0];
	if(obj == null)
	{
		obj = xmlDoc.getElementsByTagName("root")[0];
	}
	var type = obj.nodeName;
		
	if(type == "error")
		window.alert(obj.childNodes[0].nodeValue);
	else  if(type == "root")
	{
		if(obj.hasChildNodes())
			createTable(obj);
		else
			document.getElementById("fileListBox").innerHTML = "<font color=red>Don't Have Any File!</font>";
	}
}

//��ȡ���������й�����ļ���
function getAllSharedFolder()
{
	
}

//��ʼ���û�����
function initUserForm()
{
	getUserInfo();
	getUserAllFolderList(); //由于这里采用的是同步， 所以该方法执行完后才能执行后面的方法，保证foderContent的innerHTML为最新内容。
	//查找一个文件夹
	//window.alert(document.getElementById("folderContent").innerHTML);
	var root = document.getElementById("folderContent").childNodes[0];
	//window.alert(root.nodeName);
	if(root == null || root.nodeName != "UL") 
	{	
		document.getElementById("fileListBox").innerHTML = "<font color=red>Don't Have Any File!</font>";
		//window.alert("Dont's have any folder");
		return;
	}
	//window.alert(root.childNodes[0].nodeName);
	//window.alert( document.getElementById("folderContent").innerHTML);
	var folderId = root.childNodes[0].getAttribute("folderId");;
	//window.alert(folderId);
	changeFolderById(folderId, false);
	//getUserFileListByFolder(1);
	//getAllSharedFolder();
}

//���´����ļ�����Ϣ
function updateFolderMsg(obj)
{
	var capacity = obj.getAttribute("capacity");
	capacity = capacity / 1024;
	document.getElementById("capacity").innerHTML = capacity.toFixed(2);
	document.getElementById("subFolderNum").innerHTML = obj.getAttribute("subFolderNum");
	document.getElementById("fileNum").innerHTML = obj.getAttribute("fileNum");
}

function updatePath(obj)
{
	var parNode = obj;
	
	var pathNode = document.getElementById("path");
	//window.alert(pathNode.getElementsByTagName("a").length);
	//for(var i = pathNode.getElementsByTagName("a").length - 1; i >= 0; i--)
	//{
	//	pathNode.removeChild(pathNode.getElementsByTagName("a")[i]);
	//}
	//pathNode.innerHTML = "·����";
	var pathStr = "";

	while( parNode != null && parNode.parentNode != null)
	{
		if(parNode.nodeName == "UL")
		{
			parNode = parNode.previousSibling;
		}
		if(parNode != null)
		{
			var name = parNode.getAttribute("name");
			var id = parNode.getAttribute("folderId");
			pathStr = "<a onclick=\"changeFolderById(" + id + ", false)\" href=\"#\">" + name + "</a>/" + pathStr;
			
			//var newA = document.createElement("a");
			//newA.innerHTML = name;
			//newA.setAttribute("href", "#");
			//newA.setAttribute("onclick", "changeFolderById(" + id + ", false)");
			//newA.onclick = changeFolderById;
			
			parNode = parNode.parentNode;
		}
	}
	pathStr = "Path: " + pathStr.substr(0, pathStr.length - 1);
	pathNode.innerHTML = pathStr;
}

//����������
function createTable(root)
{
	var fileListBox = document.getElementById("fileListBox");
	fileListBox.removeChild(fileListBox.childNodes[0]);
	
	var table = document.createElement("table");
	table.appendChild(createTableHeader());
	
	var oddNumber = 1;
	var subFolderList = root.getElementsByTagName("folder");
	for( var i = 0; i < subFolderList.length; i++)
	{
		var id = subFolderList.item(i).getAttribute("id");
		var folderName = subFolderList.item(i).getAttribute("name");
		var capacity = subFolderList.item(i).getAttribute("capacity");
		var createTime = subFolderList.item(i).getAttribute("createTime");
		
		if( i == subFolderList.length - 1)
		{
			table.appendChild(createTableFooter(oddNumber++, false, id, folderName, capacity, createTime, ""));
			break;
		}
		table.appendChild(createMiddleRows(oddNumber++, false, id, folderName, capacity, createTime, ""));		
	}
	
	var fileList = root.getElementsByTagName("file");
	for( var i = 0; i < fileList.length; i++)
	{
		var id = fileList.item(i).getAttribute("id");
		var size = fileList.item(i).getAttribute("size");
		var filename = fileList.item(i).childNodes.item(0).firstChild.nodeValue;
		var path = fileList.item(i).childNodes.item(1).firstChild.nodeValue;
		var pubTime = fileList.item(i).childNodes.item(2).firstChild.nodeValue;
		
		if( i == fileList.length - 1)
		{
			table.appendChild(createTableFooter(oddNumber++, true, id, filename, size, pubTime, path));
			break;
		}
		table.appendChild(createMiddleRows(oddNumber++, true, id, filename, size, pubTime, path));
		
	}
	
	//fileListBox.appendChild(table);	
	fileListBox.innerHTML = "<table>" + table.innerHTML + "</table>";
	fileListBox.style.display = "block";
	//window.alert(fileListBox.innerHTML);
	
}


function createTableHeader()
{
	var newRow = document.createElement("tr");
	var td1 = document.createElement("td");
	var td2 = document.createElement("td");
	var td3 = document.createElement("td");
	var td4 = document.createElement("td");
	
	td1.style.width = "6%";
	td2.style.width = "54%";
	td3.style.width = "20%";
	td4.style.width = "20%";
	
	td1.appendChild(document.createTextNode("All"));
	td2.appendChild(document.createTextNode("Title"));
	td3.appendChild(document.createTextNode("Size"));
	td4.appendChild(document.createTextNode("CreateTime"));
	
	td4.style.borderRight = "1px solid #CCCCCC";
	
	newRow.appendChild(td1);
	newRow.appendChild(td2);
	newRow.appendChild(td3);
	newRow.appendChild(td4);
	
	return newRow;
}

function createMiddleRows(oddNumber, isFile, id , title, size, createTime, path)
{
	var newRow = document.createElement("tr");
	var td1 = document.createElement("td");
	var td2 = document.createElement("td");
	var td3 = document.createElement("td");
	var td4 = document.createElement("td");

	
	var cb = document.createElement("input");
	cb.type = "checkbox";
	cb.name = "fileSelect";
	cb.setAttribute("name", "fileSelect");
	cb.setAttribute("isFile", isFile);
	cb.setAttribute("fileId", id);
	
	var newA = document.createElement("a");
	
	if(isFile)
	{
		newA.setAttribute("href", path);
		newA.setAttribute("target", "_blank");
	}
	else
	{
		var folderImg = document.createElement("img");
		folderImg.src = "./images/folder1.gif";
		newA.appendChild(folderImg);
		newA.setAttribute("href", "#");
		newA.setAttribute("onclick", "changeFolderById(" + id + ", false);"); //
	}
	
	newA.appendChild(document.createTextNode(title));
	
	td4.style.borderRight = "1px solid #CCCCCC";
		
	var newSize = size / 1024;
	newSize = newSize.toFixed(2);
	
	td1.appendChild(cb);
	td2.appendChild(newA);
	td3.appendChild(document.createTextNode(newSize + " KB"));
	td4.appendChild(document.createTextNode(createTime));
	
	newRow.appendChild(td1);
	newRow.appendChild(td2);
	newRow.appendChild(td3);
	newRow.appendChild(td4);
	
	if(oddNumber % 2 == 1)
	{
		newRow.style.backgroundColor = "#ecf2fe";
	}
	return newRow;
}

function createTableFooter(oddNumber, isFile, id , title, size, createTime, path)
{
	var newRow = createMiddleRows(oddNumber, isFile, id , title, size, createTime, path);
	var td1 = newRow.childNodes[0];
	var td2 = newRow.childNodes[1];
	var td3 = newRow.childNodes[2];
	var td4 = newRow.childNodes[3];
	
	td1.style.borderBottom = "1px solid #CCCCCC";
	td2.style.borderBottom = "1px solid #CCCCCC";
	td3.style.borderBottom = "1px solid #CCCCCC";
	td4.style.borderBottom = "1px solid #CCCCCC";
	
	return newRow;
}