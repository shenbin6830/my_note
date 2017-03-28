package test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.myself.notecloud.entity.User;
import cn.myself.notecloud.service.UserService;

public class TestUserService {
	private UserService service;
	@Before
	public void init()
	{
		//string[] config={"conf/spring-mybatis.xml","conf/spring-mvc.xml"};
		ApplicationContext ac=new ClassPathXmlApplicationContext("conf/spring-*.xml");
		service=ac.getBean("userService",UserService.class);
	}
	@Test//预期：用户名错误
	public void test1()//junit 内部用了断言的机制
	{
		User user=service.login("demo11", "123");
	}
	@Test//预期：密码错误
	public void test2()//junit 内部用了断言的机制
	{
		User user=service.login("demo", "123");
	}
	@Test//预期：登录成功
	public void test3()
	{
		User user=service.login("demo", "123456");
	}
	@Test
	public void test4()
	{
		User user=service.regist("user-124", "nnn2", "8888888");
		System.out.println(user);
	}
}
