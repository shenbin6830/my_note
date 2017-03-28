package cn.myself.notecloud.dao;

import java.util.List;
import java.util.Map;

import cn.myself.notecloud.entity.Note;
import cn.myself.notecloud.entity.Share;

public interface ShareDao {
	int save(Share share);
	List<Share> findNoteByTitle(Map params);
}
