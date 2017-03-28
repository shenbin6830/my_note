package test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.myself.notecloud.dao.UserDao;
import cn.myself.notecloud.entity.User;

public class TestDao {
	@Test
	public void testAddUser()
	{
		ApplicationContext ctx=new ClassPathXmlApplicationContext("conf/spring-*.xml");
		UserDao dao=ctx.getBean("userDao",UserDao.class);
		//token类似于session
		User user=new User("123456","大表哥","123456","","老司机");
		dao.addUser(user);
	}
}
