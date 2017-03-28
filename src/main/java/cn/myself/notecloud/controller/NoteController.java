package cn.myself.notecloud.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.myself.notecloud.entity.JsonResult;
import cn.myself.notecloud.entity.Note;
import cn.myself.notecloud.exception.NoteBookNotFoundException;
import cn.myself.notecloud.exception.NoteNotFoundException;
import cn.myself.notecloud.service.NoteService;

@Controller
@RequestMapping("/note")
public class NoteController extends ExceptionController {
	@Resource
	private NoteService noteService;
	@RequestMapping("/list.do")
	@ResponseBody
	public JsonResult listNotes(String bookId)
	{
		List<Map<String,Object>> list=noteService.listNotes(bookId);
		return new JsonResult(list);
		
	}
	@RequestMapping("/add.do")
	@ResponseBody
	public JsonResult addNote(String userId,String bookId,String noteTitle)
	{
		Note note=noteService.addNote(userId, bookId, noteTitle);
		return new JsonResult(note);
	}
	@RequestMapping("/del.do")
	@ResponseBody
	public JsonResult delNote(String noteId,String bookId)
	{
		int i=noteService.delNote(noteId);
		//System.out.println(i);
		JsonResult result=new JsonResult();
		switch(i){
		case 0:{
			result.setState(1);
			result.setMessage("删除笔记失败");
			break;
		}
		default:{
			result.setState(0);
			result.setMessage("删除成功");
			break;
		}
	}
		return result;
}		
	@ExceptionHandler(NoteBookNotFoundException.class)
	@ResponseBody
	public JsonResult pwdexp(NoteBookNotFoundException e)
	{
		e.printStackTrace();
		return new JsonResult(e);//book没有找到异常
	}
	@ExceptionHandler(NoteNotFoundException.class)
	@ResponseBody
	public JsonResult noteExp(NoteNotFoundException e)
	{
		return new JsonResult(2,e);
	}
}	
