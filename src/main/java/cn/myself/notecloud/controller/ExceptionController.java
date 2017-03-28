package cn.myself.notecloud.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.myself.notecloud.entity.JsonResult;

public abstract class ExceptionController {
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public JsonResult exp(Exception e)
	{
		e.printStackTrace();
		return new JsonResult(e);
	}
}
