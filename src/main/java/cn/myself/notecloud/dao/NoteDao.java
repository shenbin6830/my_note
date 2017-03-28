package cn.myself.notecloud.dao;

import java.util.List;
import java.util.Map;

import cn.myself.notecloud.entity.Note;

public interface NoteDao {
	List<Map<String,Object>> findByBookId(String bookId);
	int save(Note note);
	Note findByNoteId(String noteId);
	int delete(String noteId);
}
