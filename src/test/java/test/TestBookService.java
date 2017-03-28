package test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.myself.notecloud.entity.Book;
import cn.myself.notecloud.service.BookService;

public class TestBookService {
	private BookService bookService;
	@Before
	public void init()
	{
		ApplicationContext ac=new ClassPathXmlApplicationContext("conf/spring-*.xml");
		bookService=ac.getBean("bookService",BookService.class);
	}
	@Test
	public void test1()//测试bookService的listBooks方法
	{
		List<Book> list=bookService.listBooks("39295a3d-cc9b-42b4-b206-a2e7fab7e77c");
		for(Book b:list)
		{
			System.out.println(b);
		}
	}
	@Test
	public void test2()//测试bookService的save方法
	{
		Book book=bookService.addBook("48595f52-b22c-4485-9244-f4004255b972", "javaSource");
		System.out.println(book);
	}
}
