package test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import cn.myself.notecloud.dao.NoteDao;
import cn.myself.notecloud.dao.ShareDao;
import cn.myself.notecloud.entity.Note;
import cn.myself.notecloud.entity.Share;
import cn.myself.notecloud.util.NoteUtil;

public class TestShareDao extends TestBase {
	private ShareDao shareDao;
	private NoteDao noteDao;
	@Before
	public void init()
	{
		shareDao=this.getContext().getBean("shareDao",ShareDao.class);
		noteDao=this.getContext().getBean("noteDao",NoteDao.class);
	}
	@Test
	public void test1()
	{
		Share share=new Share();
		Note note =noteDao.findByNoteId("05d49f64-6ae1-4875-ba75-fc0f91a6b48a");
		share.setCn_note_id(note.getId());
		share.setCn_share_body(note.getBody());
		share.setCn_share_id(NoteUtil.createId());
		share.setCn_share_title(note.getTitle());
		int i=shareDao.save(share);
		System.out.println(i);
	}
	@Test
	public void test2()
	{
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("title", "%");
		params.put("begin", 1);
		List<Share> notes=shareDao.findNoteByTitle(params);
		for(Share share:notes)
		{
			System.out.println(share);
		}
	}
}
