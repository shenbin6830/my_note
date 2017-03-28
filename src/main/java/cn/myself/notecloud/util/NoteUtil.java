package cn.myself.notecloud.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import org.apache.commons.codec.binary.Base64;


public class NoteUtil {
	//MD5数据摘要
	public static String md5(String src){
		try {
			MessageDigest md=MessageDigest.getInstance("MD5");
			//加密处理
			byte[] output=md.digest(src.getBytes());
			//利用Base64转换成字符串结果
			String ret=Base64.encodeBase64String(output);
		
			return ret;
			
		} catch (NoSuchAlgorithmException e) {
			
			e.printStackTrace();
			return null;
		}
		
	}
	public static void main(String[] args) {
		System.out.println(md5("123456"));
		System.out.println(createId());
	}
	//利用UUID生成主键值
	public static String createId()
	{
		String id=UUID.randomUUID().toString();
		return id;
	}
	
}
