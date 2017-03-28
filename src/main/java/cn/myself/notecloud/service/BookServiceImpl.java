package cn.myself.notecloud.service;

import java.sql.Timestamp;
import java.util.List;



import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.myself.notecloud.dao.BookDao;
import cn.myself.notecloud.dao.UserDao;
import cn.myself.notecloud.entity.Book;
import cn.myself.notecloud.entity.User;
import cn.myself.notecloud.exception.UserNotFoundException;
import cn.myself.notecloud.util.NoteUtil;
@Service("bookService")
public class BookServiceImpl implements BookService {
	@Resource
	private BookDao bookDao;
	@Resource
	private UserDao userDao;
	public List<Book> listBooks(String userId) {
		if(userId==null || userId.trim().isEmpty())
		{
			throw new UserNotFoundException("userId为空");
		}
		User user=userDao.findUserByUserId(userId);
		if(user==null)
		{
			throw new UserNotFoundException("用户没有找到");
		}
		List<Book> books=bookDao.findByUserId(user.getId());
		return books;
		
	}
	public Book addBook(String userId, String bookName)
			throws UserNotFoundException {
		if(userId==null ||userId.trim().isEmpty())
		{
			throw new UserNotFoundException("userId不能为空");
		}
		User user=userDao.findUserByUserId(userId);
		if(user==null)
		{
			throw new UserNotFoundException("用户不存在");
		}
		Book book=new Book();
		String bookId=NoteUtil.createId();
		Timestamp time=new Timestamp(System.currentTimeMillis());
		book.setId(bookId);
		book.setUserId(userId);
		book.setName(bookName);
		book.setTypeId("5");
		book.setCreateTime(time);
		book.setDesc("你说空就空吗");
		bookDao.save(book);
		return book;
	}

}
