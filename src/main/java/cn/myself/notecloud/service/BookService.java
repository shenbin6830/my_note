package cn.myself.notecloud.service;

import java.util.List;

import javax.annotation.Resource;

import cn.myself.notecloud.dao.BookDao;
import cn.myself.notecloud.entity.Book;
import cn.myself.notecloud.exception.UserNotFoundException;

public interface BookService {
	List<Book> listBooks(String userId) throws UserNotFoundException;
	Book addBook(String userId,String bookName) throws UserNotFoundException;
}
