function LoadNotesAction() {
	// 把搜索结果的版面隐藏
	$("#pc_part_6").hide();
	// 显示全部笔记版面
	$("#pc_part_2").show();

	// console.log($(this));
	// 清除记录选中效果，必须先清除才能给它添加选中效果，跟.html("")一样
	$("#book_ul a").removeClass("checked");
	// 设置记录选中效果
	$(this).find("a").addClass("checked");
	// 获取参数bookId
	var bookId = $(this).data("bookId");
	console.log("book=" + bookId);
	$.ajax({
		url : path + "/note/list.do",
		type : "post",
		data : {
			"bookId" : bookId
		},
		dataType : "json",
		success : function(result) {
			if (result.state == 0) {
				// 获取笔记集合
				var notes = result.data;
				// 清空笔记列表
				$("#note_ul li").remove();
				// 遍历集合中的笔记记录
				for (var i = 0; i < notes.length; i++) {
					// 获取笔记ID
					var noteId = notes[i].id;
					// 获取笔记标题
					var noteTitle = notes[i].title;
					// 创建笔记li元素
					createNoteLi(noteId, noteTitle);

				}
				// if($(this).find('a').focus())
				// {
				// $("#add_note").click(addNoteAlertWindow);
				// //addCookie("isFocus", true);
				// }
			}
		},
		error : function() {
			alert("获取note失败");
		}
	});
}
function createNoteLi(noteId, noteTitle) {
	var sli = "";
	sli += '<li class="online">';
	sli += '<a >';
	sli += '<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>'
			+ noteTitle
			+ '<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button>'
	sli += '</a>';
	sli += '<div class="note_menu" tabindex="-1">';
	sli += '<dl>';
	sli += '<dt><button type="button" class="btn btn-default btn-xs btn_move" title="移动至..."><i class="fa fa-random"></i></button></dt>';
	sli += '<dt><button type="button" class="btn btn-default btn-xs btn_share" title="分享"><i class="fa fa-sitemap"></i></button></dt>';
	sli += '<dt><button type="button" class="btn btn-default btn-xs btn_delete" title="删除"><i class="fa fa-times"></i></button></dt>';
	sli += '</dl>';
	sli += '</div>';
	sli += '</li>';
	// 将sli转换成jQuery对象
	var $li = $(sli);
	// 绑定noteId
	$li.data("noteId", noteId);
	$("#note_ul").append($li);
}
function createShareLi(shareId, shareTitle) {
	var sli = "";
	sli += '<li class="online">';
	sli += '<a >';
	sli += '<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>'
			+ shareTitle;
			
	sli += '</a>';
	sli += '</li>';
	// 将sli转换成jQuery对象
	var $li = $(sli);
	// 绑定noteId
	$li.data("shareId", shareId);
	$("#search_ul").append($li);
}
function showNoteMenu() {

	// 展开下拉菜单
	$("#note_ul").on("click", ".btn_slide_down", function() {

		// 隐藏笔记菜单
		$("#note_ul div").hide();
		// 获取菜单对象
		var note_menu = $(this).parents("li").find("div");// li和div同级，要通过li找div，先找到li的父级,父级通过find（div）找到div
		note_menu.show();
		// 删除选中效果
		$("#note_ul a").removeClass("checked");
		// 选中效果设置
		$(this).parent().addClass("checked");// this代表按钮，按钮的父级是dl，给dl添加样式checked，dl的作用就是添加样式的时候选择
		// 阻止冒泡 return false 做了下面三件事
		// event.preventDefault();
		// event.stopPropagation();
		// 停止回调函数执行并立即返回。
		return false;
	});
	// 点击body范围，隐藏笔记菜单
	$("body").click(function() {

		$("#note_ul div").hide();
	});
}
function addShareAction() {
	// 获取参数
	var $li = $(this).parents("li");
	var noteId = $li.data("noteId");
	console.log(noteId);
	// 发送请求
	$.ajax({
		url : path + "/share/add.do",
		type : "post",
		data : {
			"noteId" : noteId
		},
		dataType : "json",
		success : function(result) {
			if (result.state == 0) {
				var message = result.message;
				alert(message);
			}

		},
		error : function() {
			alert("分享失败");
		}
	})
}
function addNoteAction() {
	// 获取参数
	var noteTitle = $("#input_note").val().trim();
	var userId = getCookie("userId");
	var $li = $("#book_ul a.checked").parent();
	var bookId = $li.data("bookId");
	// 发送Ajax请求
	$.ajax({
		url : path + "/note/add.do",
		type : "post",
		data : {
			"userId" : userId,
			"bookId" : bookId,
			"noteTitle" : noteTitle
		},
		dataType : "json",
		success : function(result) {
			if (result.state == 0) {
				var note = result.data;
				var id = note.id;
				var title = note.title;
				// 关闭对话框
				closeAlertWindow();
				createNoteLi(id, title);
				alert("创建笔记成功");
			}
		},
		error : function() {
			alert("创建笔记失败");
		}
	});
}
function delNoteAction() {
	// 获得参数 通过找到note_ul下的有class名为checked的a元素来获得他的父级li，从而的到绑定在li上的noteId
	var $li = $("#note_ul a.checked").parent();
	var noteId = $li.data("noteId");
	// 删除以后同步刷新需要用到bookId
	// var $li=$("#book_ul a.checked").parent();
	// var bookId=$li.data("bookId");
	$.ajax({
		url : path + "/note/del.do",
		type : "post",
		data : {
			"noteId" : noteId
		},
		dataType : "json",
		success : function(result) {
			if (result.state == 0) {
				$li.remove();
				alert(result.message);
			}
		},
		error : function() {
			alert("删除笔记失败");
		}
	})
}
function SearchNotes(event) {// 绑定笔记模糊搜索（search_note)的键盘监听事件
	// 获取键盘keyCode码
	var code = event.keyCode;
	// console.log(code);

	if (code == 13) {
		//重置page为1
		page=1;
		// 获取参数 html("aaa"); 相当于innerHtml,一般用于标签体内容的出啊如
		var title = $("#search_note").val().trim();// val针对value属性进行赋值
		// console.log(title);

		$.ajax({
			url : path + "/share/find.do",
			type : "post",
			data : {
				"title" : title,"page":page
			},
			dataType : "json",
			success : function(result) {
				if (result.state == 0) {
					var shares = result.data;
					// 切换列表区域
					$("#pc_part_2").hide();// 将全部笔记隐藏
					$("#pc_part_6").show();// 将搜索结果显示
					// 切换显示区域
					$("#pc_part_3").hide();
					$("#pc_part_5").show();
					// 清空笔记列表
					$("#search_ul li").remove();
					// 遍历集合中的笔记记录
					for (var i = 0; i < shares.length; i++) {
						// 获取笔记ID
						var shareId = shares[i].cn_share_id;
						// 获取笔记标题
						var shareTitle = shares[i].cn_share_title;
						// 创建笔记li元素
						createShareLi(shareId, shareTitle);

					}
				}
			},
			error : function() {
				alert("搜索失败");
			}
		})
	}
}
function loadShareButton(){
	var title=$("#search_note").val().trim();
	page+=1;
	$.ajax({
		url : path + "/share/find.do",
		type : "post",
		data : {
			"title" : title,"page":page
		},
		dataType : "json",
		success : function(result) {
			if (result.state == 0) {
				var shares = result.data;
				// 切换列表区域
				$("#pc_part_2").hide();// 将全部笔记隐藏
				$("#pc_part_6").show();// 将搜索结果显示
				// 切换显示区域
				$("#pc_part_3").hide();
				$("#pc_part_5").show();
				// 清空笔记列表
				$("#search_ul li").remove();
				// 遍历集合中的笔记记录
				for (var i = 0; i < shares.length; i++) {
					// 获取笔记ID
					var shareId = shares[i].cn_share_id;
					// 获取笔记标题
					var shareTitle = shares[i].cn_share_title;
					// 创建笔记li元素
					createShareLi(shareId, shareTitle);
					
				}
			}
		},
		error : function() {
			alert("搜索失败");
		}
	});
}