package cn.myself.notecloud.dao;

import java.util.List;

import cn.myself.notecloud.entity.Book;

public interface BookDao {
	List<Book> findByUserId(String userId);
	Book findByBookId(String bookId);
	void save(Book book);
	
}
