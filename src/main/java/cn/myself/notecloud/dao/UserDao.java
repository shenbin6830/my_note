package cn.myself.notecloud.dao;

import java.util.List;

import cn.myself.notecloud.entity.Book;
import cn.myself.notecloud.entity.User;

public interface UserDao {
	User findUserByName(String name);
	void addUser(User user);
	User findUserByUserId(String userId);
}
