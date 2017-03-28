package cn.myself.notecloud.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.myself.notecloud.entity.JsonResult;
import cn.myself.notecloud.entity.User;
import cn.myself.notecloud.exception.NameException;
import cn.myself.notecloud.exception.PasswordException;
import cn.myself.notecloud.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController extends ExceptionController{
	@Resource//(name="userService")
	private UserService userService;
	
	@RequestMapping("/login.do")
	@ResponseBody//加上这个注解，以json的形式传递数据
	public Object login(String name,String password)
	{
		User user=userService.login(name, password);
		return new JsonResult(user);
	}
	@RequestMapping("/regist.do")
	@ResponseBody
	public JsonResult regist(String username,String nick,String password)
	{
			User user=userService.regist(username, nick, password);
			return new JsonResult(user);
	}
	//当这个Controller中任何一个方法发生异常，一定会被这个方法拦截到。然后，输出日志。封装JsonResult并返回，页面上得到status为false。就这么简单
	@ExceptionHandler(NameException.class)//括号里是异常的类型
	@ResponseBody
	public JsonResult nameexp(NameException e)
	{
		e.printStackTrace();
		return new JsonResult(2,e);//当是用户操作账号错误的时候state 报2
	}
	@ExceptionHandler(PasswordException.class)
	@ResponseBody
	public JsonResult pwdexp(PasswordException e)//
	{
		e.printStackTrace();
		return new JsonResult(3,e);//当是用户操作密码错误的时候state 报3
	}
}
