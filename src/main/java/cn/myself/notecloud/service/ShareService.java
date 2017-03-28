package cn.myself.notecloud.service;

import java.util.List;

import cn.myself.notecloud.entity.Note;
import cn.myself.notecloud.entity.Share;

public interface ShareService {
	void addShare(String noteId);
	List<Share> searchNotes(String title,int page);
}
