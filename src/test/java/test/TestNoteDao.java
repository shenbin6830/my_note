package test;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import cn.myself.notecloud.dao.BookDao;
import cn.myself.notecloud.dao.NoteDao;
import cn.myself.notecloud.entity.Note;
import cn.myself.notecloud.service.NoteService;
import cn.myself.notecloud.util.NoteUtil;

public class TestNoteDao extends TestBase {
	private NoteDao noteDao;
	@Before
	public void init()
	{
		noteDao=super.getContext().getBean("noteDao",NoteDao.class);
	}
	@Test
	public void test1()
	{
		List<Map<String,Object>> notes=noteDao.findByBookId("1db556b9-d1dc-4ed9-8274-45cf0afbe859");
		for(Map note:notes)
		{
			
			System.out.println(note.get("id")+","+note.get("title"));
		}
	}
	@Test
	public void test2()
	{
		Note note =new Note();
		note.setBookId("48595f52-b22c-4485-9244-f4004255b972");
		note.setTitle("传过来的title");
		note.setBody("kong");
		note.setCreateTime(System.currentTimeMillis());
		note.setId(NoteUtil.createId());
		note.setLastModifyTime(System.currentTimeMillis());
		note.setStatusId("1");
		note.setTypeId("");
		note.setUserId("48595f52-b22c-4485-9244-f4004255b972");
		int i=noteDao.save(note);
		System.out.println(i);
	}
	@Test
	public void test3()
	{
		Note note=noteDao.findByNoteId("05d49f64-6ae1-4875-ba75-fc0f91a6b48a");
		System.out.println(note);
	}
	@Test
	public void test4()//测试delete 实际上是将cn_note 表中某条数据的status属性改为0
	{
		int i=noteDao.delete("05d49f64-6ae1-4875-ba75-fc0f91a6b48a");
		System.out.println(i);
	}
}
