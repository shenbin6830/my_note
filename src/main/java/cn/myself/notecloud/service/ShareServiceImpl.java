package cn.myself.notecloud.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.myself.notecloud.dao.NoteDao;
import cn.myself.notecloud.dao.ShareDao;
import cn.myself.notecloud.entity.Note;
import cn.myself.notecloud.entity.Share;
import cn.myself.notecloud.exception.NoteBookNotFoundException;
import cn.myself.notecloud.exception.NoteNotFoundException;
import cn.myself.notecloud.util.NoteUtil;
@Service("shareService")
public class ShareServiceImpl implements ShareService{
	@Resource
	private NoteDao noteDao;
	@Resource
	private ShareDao shareDao;
	public void addShare(String noteId) {
		if(noteId==null || noteId.trim().isEmpty())
		{
			throw new NoteNotFoundException("noteId不能为空");
		}
		Note note=noteDao.findByNoteId(noteId);
		if(note==null)
		{
			throw new NoteNotFoundException("note没有找到");
		}
		Share share=new Share();
		share.setCn_note_id(noteId);
		share.setCn_share_body(note.getBody());
		share.setCn_share_id(NoteUtil.createId());
		share.setCn_share_title(note.getTitle());
		int i=shareDao.save(share);
	}
	public List<Share> searchNotes(String title,int page) {
		String result="%";
		if(title!=null)
		{
			result="%"+title+"%";
		}
		//计算抓取记录的二七店
		int begin=(page-1)*3;
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("title", result);//对应#{title}
		params.put("begin", begin);//对应#{begin}
		List<Share> notes=shareDao.findNoteByTitle(params);
		System.out.println("notes's length"+notes.size());
		return notes;
	}

}
