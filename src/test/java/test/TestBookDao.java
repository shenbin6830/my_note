package test;

import java.sql.Timestamp;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import cn.myself.notecloud.dao.BookDao;
import cn.myself.notecloud.dao.NoteDao;
import cn.myself.notecloud.entity.Book;
import cn.myself.notecloud.entity.Note;

public class TestBookDao extends TestBase{
	private BookDao bookDao;
	@Before
	public void init()
	{
		bookDao=super.getContext().getBean("bookDao",BookDao.class);
	}
	@Test
	public void test1()
	{
		List<Book> books=bookDao.findByUserId("39295a3d-cc9b-42b4-b206-a2e7fab7e77c");
		for(Book b:books)
		{
			System.out.println(b.getId()+","+b.getName());
		}
	}
	@Test
	public void test2()
	{
		Book book=new Book();
		book.setId("123456");
		book.setUserId("48595f52-b22c-4485-9244-f4004255b972");
		book.setName("cloudnote");
		book.setTypeId("5");
		book.setDesc("");
		Timestamp time=new Timestamp(System.currentTimeMillis());
		book.setCreateTime(time);
		bookDao.save(book);
	}
	
}
