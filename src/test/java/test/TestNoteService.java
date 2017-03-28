package test;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import cn.myself.notecloud.entity.Note;
import cn.myself.notecloud.service.NoteService;

public class TestNoteService extends TestBase {
	private NoteService noteService;
	@Before
	public void init()
	{
		noteService=super.getContext().getBean("noteService",NoteService.class);
	}
	@Test
	public void test1()
	{
		List<Map<String,Object>> list=noteService.listNotes("1db556b9-d1dc-4ed9-8274-45cf0afbe859");
		for(Map<String,Object> m:list)
		{
			System.out.println(m);
		}
	}
	@Test
	public void test2()
	{
		Note note=noteService.addNote("48595f52-b22c-4485-9244-f4004255b972", "0780fc33-0573-4adf-bbb0-0ed37f03cded", "noteService");
		System.out.println(note);
	}
}	
