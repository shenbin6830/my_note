package cn.myself.notecloud.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.myself.notecloud.dao.BookDao;
import cn.myself.notecloud.dao.NoteDao;
import cn.myself.notecloud.dao.UserDao;
import cn.myself.notecloud.entity.Book;
import cn.myself.notecloud.entity.Note;
import cn.myself.notecloud.exception.NoteBookNotFoundException;
import cn.myself.notecloud.exception.NoteNotFoundException;
import cn.myself.notecloud.util.NoteUtil;
@Service("noteService")
public class NoteServiceImpl implements NoteService {
	@Resource
	private UserDao userDao;
	@Resource
	private NoteDao noteDao;
	@Resource
	private BookDao bookDao;
	public List<Map<String, Object>> listNotes(String bookId)
			throws NoteBookNotFoundException {
		if(bookId==null || bookId.trim().isEmpty())
		{
			throw new NoteBookNotFoundException("book ID 不能为空");
		}
		//校验bookID
		Book book=bookDao.findByBookId(bookId);
		if(book==null)
		{
			throw new NoteBookNotFoundException("笔记本不存在");
		}
		List<Map<String,Object>> list=noteDao.findByBookId(bookId);
		
		return list;
	}
	public Note addNote(String userId, String bookId, String noteTitle) {
//		if(userId==null || userId.trim().isEmpty())
//		{
//			throw new UserNotFoundException("userId不能为空 ");
//		}
//		User user=userDao.findUserByUserId(userId);
//		if(user==null)
//		{
//			throw new UserNotFoundException("找不到用户");
//		}
		Note note=new Note();
		String noteId=NoteUtil.createId();
		long createTime=System.currentTimeMillis();
		long lastModifyTime=System.currentTimeMillis();
		note.setBody("");
		note.setBookId(bookId);
		note.setCreateTime(createTime);
		note.setId(noteId);
		note.setLastModifyTime(lastModifyTime);
		note.setStatusId("1");
		note.setTitle(noteTitle);
		note.setTypeId("1");
		note.setUserId(userId);
		noteDao.save(note);
		return note;
	}
	public int delNote(String noteId) {
//		if(noteId==null || noteId.trim().isEmpty())
//		{
//			throw new NoteNotFoundException("noteId不能为空");
//		}
//		Note note=noteDao.findByNoteId(noteId);
//		if(note==null)
//		{
//			throw new NoteNotFoundException("找不到笔记");
//		}
		int i=noteDao.delete(noteId);
		return i;
	}

	
}
