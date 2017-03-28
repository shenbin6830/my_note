package cn.myself.notecloud.service;

import java.util.List;
import java.util.Map;

import cn.myself.notecloud.entity.Note;
import cn.myself.notecloud.exception.NoteBookNotFoundException;

public interface NoteService {
	List<Map<String,Object>> listNotes(String bookId) throws NoteBookNotFoundException;
	Note addNote(String userId,String bookId,String noteTitle);
	int delNote(String noteId);
}
