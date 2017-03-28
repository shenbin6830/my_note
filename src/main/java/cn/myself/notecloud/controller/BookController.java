package cn.myself.notecloud.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.myself.notecloud.entity.Book;
import cn.myself.notecloud.entity.JsonResult;
import cn.myself.notecloud.exception.UserNotFoundException;
import cn.myself.notecloud.service.BookService;
@Controller
@RequestMapping("/book")
public class BookController extends ExceptionController {
	@Resource
	private BookService bookService;
	
	@RequestMapping("/loadBooks.do")
	@ResponseBody
	public JsonResult listBooks(String userId)
	{
		List<Book> result=bookService.listBooks(userId);
		return new JsonResult(result);
		
	}
	@RequestMapping("/add.do")
	@ResponseBody
	public JsonResult addBook(String userId,String bookName)
	{
		Book book=bookService.addBook(userId, bookName);
		return new JsonResult(book);
	}
	@ExceptionHandler(UserNotFoundException.class)
	@ResponseBody
	public JsonResult userNotFoundexp(UserNotFoundException e)
	{
		e.printStackTrace();
		return new JsonResult(2,e);//当是用户操作账号错误的时候state 报2
	}
}
