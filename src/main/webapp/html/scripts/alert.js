function closeAlertWindow(){
	//清空对话框
	$("#can").html("");
	//隐藏背景色
 	$(".opacity_bg").hide();
}
//弹出新建笔记本对话框
function addBookAlertWindow(){
		//弹出对话框
		$("#can").load("alert/alert_notebook.html");
		//显示背景色
	 	$(".opacity_bg").show();
	
}
function addNoteAlertWindow(){
	var $li = $("#book_ul a.checked").parent();
	if ($li.length == 0) {
		alert("没有选中");
	} else {
	//弹出对话框
	$("#can").load("alert/alert_note.html");
	//显示背景色
	$(".opacity_bg").show();
	}
}
function addNoteDelAlertWindow(){
	//给删除按钮添加弹出框
	$("#can").load("alert/alert_delete_note.html");
	//显示背景颜色
	$(".opacity_bg").show();
	
}
