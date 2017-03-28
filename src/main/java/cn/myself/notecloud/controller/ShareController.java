package cn.myself.notecloud.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.myself.notecloud.entity.JsonResult;
import cn.myself.notecloud.entity.Note;
import cn.myself.notecloud.entity.Share;
import cn.myself.notecloud.exception.NoteNotFoundException;
import cn.myself.notecloud.service.ShareService;

@Controller
@RequestMapping("/share")
public class ShareController {
	@Resource
	private ShareService shareService;
	@RequestMapping("/add.do")
	@ResponseBody
	public JsonResult addShare(String noteId)
	{
		shareService.addShare(noteId);
		JsonResult result=new JsonResult();
		result.setState(0);
		result.setMessage("分享笔记成功");
		return result;
	}
	@RequestMapping("/find.do")
	@ResponseBody
	public JsonResult searchNotes(String title,int page)
	{
		List<Share> shares=shareService.searchNotes(title,page);
		for(Share s:shares)
		{
			System.out.println(s);
		}
		return new JsonResult(shares);
	}
	@ExceptionHandler(NoteNotFoundException.class)
	@ResponseBody
	public JsonResult noteNotFoundExc(NoteNotFoundException e) 
	{
		e.printStackTrace();
		return new JsonResult(2,e);//关于note的错误,也许state应该多定义几个，把异常细分
	}
}
