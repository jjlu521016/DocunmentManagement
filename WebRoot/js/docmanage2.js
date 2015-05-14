var moveFormerFolder = null;
var movedCurrentFolderId = -1;

//
function selectAllFile() 
{
	//window.alert(document.getElementById("fileListBox").getElementsByTagName("input").length);
	
	var isAllselected = false;
	var checkboxList = document.getElementById("fileListBox").getElementsByTagName("input");
	
	for(var i = 0; i < checkboxList.length; i++)
	{

		if(! checkboxList[i].checked)
			break;
	}
	
	if(i == checkboxList.length)
	{
		isAllselected = true;
	}
	
	for(var i = 0; i < checkboxList.length; i++)
	{
		if(isAllselected) 
		{
			checkboxList[i].checked = false;
			continue;
		}
		
		checkboxList[i].checked = true;
	}
}


//
function moveSelectedFile()
{
	var checkboxList = document.getElementById("fileListBox").getElementsByTagName("input");
	var fileIdStr = "";
	var folderIdStr = "";
	for(var i = 0; i < checkboxList.length; i++)
	{
		if(checkboxList[i].checked)
		{
			var isFile = checkboxList[i].getAttribute("isFile");
			var id = checkboxList[i].getAttribute("fileId");
			//window.alert(isFile);
			if(isFile == "true")
				fileIdStr += (fileIdStr == "" ? id : "," + id);
			else
				folderIdStr += (folderIdStr == "" ? id : "," + id);
			
		}
	}
	var url = "servlet/MoveFileAction?movedFileId=" + fileIdStr + "&movedFolderId=" + folderIdStr + "&newParentId=" + movedCurrentFolderId + "&oldParentId=" + currentFolderId;
	//window.alert(url);
	CallAjax("get", url, "", onMovedSelectedFile, true);
}

function onMovedSelectedFile(xmlhttp)
{
	window.alert(xmlhttp.responseText);
	hiddenTopBox("move");
	changeFolderById(currentFolderId, true);
	/*
	var xmlDoc = xmlhttp.responseXML;
	var obj = xmlDoc.getElementsByTagName("error")[0];
	if(obj == null)
	{
		obj = xmlDoc.getElementsByTagName("user")[0];
	}
	var type = obj.nodeName;
	//window.alert("type:" + type);
	if(type == "error")
		window.alert(obj.childNodes[0].nodeValue);
	else
	{
		window.alert(xmlhttp.responseText);
	}
	*/
}

//
function deleteSelectedFile()
{
	var checkboxList = document.getElementById("fileListBox").getElementsByTagName("input");
	var fileIdStr = "";
	var folderIdStr = "";
	for(var i = 0; i < checkboxList.length; i++)
	{
		if(checkboxList[i].checked)
		{
			var isFile = checkboxList[i].getAttribute("isFile");
			var id = checkboxList[i].getAttribute("fileId");
			//window.alert(isFile);
			if(isFile == "true")
				fileIdStr += (fileIdStr == "" ? id : "," + id);
			else
				folderIdStr += (folderIdStr == "" ? id : "," + id);
			
		}
	}
	var url = "servlet/DeleteFileAction?deletedFileId=" + fileIdStr + "&deletedFolderId=" + folderIdStr + "&currentFolderId=" + currentFolderId;
	CallAjax("get", url, "", onDeleteSelectedFile, true);
}

function onDeleteSelectedFile(xmlhttp)
{
	window.alert(xmlhttp.responseText);
	changeFolderById(currentFolderId, true);
	/*
	var xmlDoc = xmlhttp.responseXML;
	var obj = xmlDoc.getElementsByTagName("error")[0];
	if(obj == null)
	{
		obj = xmlDoc.getElementsByTagName("user")[0];
	}
	var type = obj.nodeName;
	//window.alert("type:" + type);
	if(type == "error")
		window.alert(obj.childNodes[0].nodeValue);
	else
	{
		window.alert(xmlhttp.responseText);
	}
	*/
}


function showTopBox(type) 
{
	document.getElementById("hiddenLevel").style.display = "block";
	
	if(type == "newFile")
	{
		document.getElementById("newFileBox").style.display = "block";
		document.getElementById("currentFolderName").innerHTML = currentFolderName;
		document.uploadFileForm.currentfolderId.value = currentFolderId;
		//window.alert(document.uploadFileForm.currentfolderId.value);
		return;
	}
	if(type == "newFolder")
	{
		document.getElementById("newFolderBox").style.display = "block";
		document.getElementById("currentFolderName2").innerHTML = currentFolderName;
		return;
	}
	if(type == "move")
	{
		document.getElementById("moveFileBox").style.display = "block";
		//document.getElementById("moveFolderList").innerHTML = document.getElementById("folderContent").innerHTML;
		var content = document.getElementById("folderContent").innerHTML;
		var reg=new RegExp("<A","g");
		content = content.replace(reg, "<A onclick=onlyChangeColor(event)");
		var reg=new RegExp("style=\"[^\"]*\"","g");
		content = content.replace(reg, "");
		//content = content.replace("BORDER-BOTTOM: #cccccc 1px solid", "");
		//content = content.replace("BORDER-LEFT: #cccccc 1px solid", "");
		//content = content.replace("BORDER-RIGHT: #cccccc 1px solid", "");
		//content = content.replace("BORDER-TOP: #cccccc 1px solid", "");
		//content = content.replace("BACKGROUND-COLOR: #ecf2fe;", "");
		reg=new RegExp("id=([a-z0-9]+)","g");  
		content =content.replace(reg, "");
		//window.alert(content);
		document.getElementById("moveFolderList").innerHTML = content;
		return;
	}
}

function onlyChangeColor(e)
{
	if (!e) var e = window.event;
	var obj = e.target ?  e.target : e.srcElement;	
	var parNode = obj.parentNode;
	if(obj.nodeName == "IMG")
		parNode = parNode.parentNode;
	
	parNode.style.backgroundColor = "#ecf2fe";
	parNode.style.border = "1px solid #cccccc";
	movedCurrentFolderId = parNode.getAttribute("folderId");
	
	//window.alert(movedCurrentFolderId);
	
	if(moveFormerFolder != null && moveFormerFolder != parNode)
	{
		moveFormerFolder.style.backgroundColor = "transparent";
		moveFormerFolder.style.border = "none";
	}
	moveFormerFolder = parNode;
}

function hiddenTopBox(type)
{
	document.getElementById("hiddenLevel").style.display = "none";
	
	if(type == "newFile")
	{
		document.getElementById("newFileBox").style.display = "none";
		return;
	}
	if(type == "newFolder")
	{
		document.getElementById("newFolderBox").style.display = "none";
		return;
	}
	if(type == "move")
	{
		document.getElementById("moveFileBox").style.display = "none";
		return;
	}
}


//
function doMoveFile()
{
	moveSelectedFile();
}

function onUploadFileReturn(msg)
{
	window.alert(msg);
	hiddenTopBox("newFile");
	changeFolderById(currentFolderId, true);
}

function doCreateNewFolder()
{
	//window.alert("create new folder");
	var newFolderName = document.getElementById("newfolderName").getAttribute("value");
	//window.alert(newFolderName);
	var url = "servlet/CreateFolderAction?newFolderName=" + newFolderName + "&parentFolderId=" + currentFolderId;
	CallAjax("get", url, "", onCreateNewFolder, true);
}


function onCreateNewFolder(xmlhttp)
{
	//window.alert("inserrt folder success!");
	//hiddenTopBox("newFolder");
	//changeFolderById(currentFolderId, true);
	

	var xmlDoc = xmlhttp.responseXML;
	var obj = xmlDoc.getElementsByTagName("error")[0];
	if(obj == null)
	{
		obj = xmlDoc.getElementsByTagName("folder")[0];
	}
	var type = obj.nodeName;
	//window.alert("type:" + type);
	if(type == "error")
		window.alert(obj.childNodes[0].nodeValue);
	else
	{
		window.alert("inserrt folder success!");
		hiddenTopBox("newFolder");
		changeFolderById(currentFolderId, true);
	}

}









