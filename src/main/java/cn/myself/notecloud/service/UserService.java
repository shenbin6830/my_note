package cn.myself.notecloud.service;

import cn.myself.notecloud.entity.User;
import cn.myself.notecloud.exception.NameException;
import cn.myself.notecloud.exception.PasswordException;

public interface UserService {
	User login(String name,String password) throws NameException,PasswordException;
	User regist(String username,String nick,String password)throws NameException,PasswordException;
}
