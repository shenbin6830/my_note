package test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import cn.myself.notecloud.entity.Note;
import cn.myself.notecloud.entity.Share;
import cn.myself.notecloud.service.ShareService;

public class TestShareService extends TestBase {
	private ShareService shareService;
	@Before
	public void init()
	{
		shareService=this.getContext().getBean("shareService",ShareService.class);
	}
	@Test
	public void test1()//车是shareService 的save方法
	{
		shareService.addShare("05d49f64-6ae1-4875-ba75-fc0f91a6b48a");
		
	}
	@Test
	public void test2()                                                                                                                
	{
		List<Share> shares=shareService.searchNotes("",1);
		for(Share s:shares)
		{
			System.out.println(s);
		}
		
	}
}
