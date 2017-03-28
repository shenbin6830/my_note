package test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.myself.notecloud.dao.UserDao;

public class TestCase {
	@Test
	public void test1()
	{
		ApplicationContext ac=new ClassPathXmlApplicationContext("conf/spring-mybatis.xml");
		UserDao dao=ac.getBean("userDao",UserDao.class);
		dao.findUserByName("demo");
	}
}
