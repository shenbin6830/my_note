package cn.myself.notecloud.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.myself.notecloud.dao.UserDao;
import cn.myself.notecloud.entity.Book;
import cn.myself.notecloud.entity.User;
import cn.myself.notecloud.exception.NameException;
import cn.myself.notecloud.exception.PasswordException;
import cn.myself.notecloud.util.NoteUtil;

@Service("userService")
public class UserServiceImpl implements UserService {
	@Resource
	private UserDao userDao;

	public User login(String name, String password) throws NameException,
			PasswordException {
		// 在浏览器端账号密码校验过了，为了保证程序的健壮性，在服务器端还是要对参数校验
		if (name == null || name.trim().isEmpty())// 参数格式校验
		{
			throw new NameException("用户名不能为空");
		}
		if (password == null || password.trim().isEmpty()) {
			throw new PasswordException("密码不能为空");
		}
		// 使用有效的name参数，调用dao的相关方法查询
		User user = userDao.findUserByName(name);
		if (user == null)// 格式上没有错误，但数据库里没有，所以查找出来的user是null
		{
			throw new NameException("用户名错误");
		}
		String md5pwd = NoteUtil.md5(password);
		// System.out.println(user.getPassword());
		// System.out.println(md5pwd);
		if (user.getPassword().equals(md5pwd)) {
			return user;
		} else {
			throw new PasswordException("密码错误");
		}
	}

	public User regist(String username, String nick, String password)
			throws NameException, PasswordException {
		// 验证用户名是否在数据库存在
		/*
		 * 1.判断用户名是否为空 2.生成id 3.nick="" nick=name 4.new user 5.dao.add(user)
		 */
//		if (username == null) {
//			throw new NameException("用户名为空");
//		} 
		User user = userDao.findUserByName(username);
		if (user == null) {
				String id = NoteUtil.createId();
				if(nick==null || nick.trim().isEmpty())
				{
					nick=username;
				}
				String token="";
				password=NoteUtil.md5(password);
				user=new User(id,username,password,token,nick);
				userDao.addUser(user);
			return user;
		}else
		{
			throw new NameException("用户名已存在");
	}
	}

}
