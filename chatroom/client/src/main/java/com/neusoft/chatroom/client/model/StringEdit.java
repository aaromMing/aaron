package com.neusoft.chatroom.client.model;

import java.sql.Timestamp;
import java.util.List;

public class StringEdit {
	// 拆分出协议的头部分 跟用户相关的协议 ：
	// 协议码|id,name,pass,petname,mail,sex,power,ip
	// 查询所有的用户的协议是：
	// 协议码|id.name,id.name,id.name......,id.name

	// ----------合成字符串(把一个对象的所有属性合成一个字符串)
	public static String hechengUser(String head,
			com.neusoft.chatroom.client.model.Userinfo u, String ip) {
		String s = "";
		s = head + "|" + u.getId() + "," + u.getName() + "," + u.getPass()
				+ "," + u.getPetname() + "," + u.getMail() + "," + u.getSex()
				+ "," + u.getPower() + "," + ip;
		return s;
	}

	// ----------拆分字符串(把一个字符串中的每一项按照位子提取出来，并返回一个对象)

	// 从一个字符串中把状态码拆分出来
	public static String getStateCode(String s) {
		int i = s.indexOf('|');
		s = s.substring(0, i);
		return s;
	}

	public static String hechengUserID1(String head, int id, String ip) {
		String s = "";
		s = head + "|" + id + "," + ip;
		return s;
	}

	public static String hechengUserS(String head, List l) {
		String s = head + "|";
		for (int i = 0; i < l.size(); i++) {
			Userinfo u = (Userinfo) l.get(i);
			s = s + u.getId() + "." + u.getPetname() + ",";
		}
		int i = s.lastIndexOf(",");
		s = s.substring(0, i);
		return s;
	}

	public static String[] chaifenUsers(String s) {
		int i = s.indexOf("|");
		s = s.substring(i + 1);
		String v[] = s.split(",");
		return v;

	}

	public static Userinfo chaifenUser(String s) {
		int i = s.indexOf('|');
		s = s.substring(i + 1);
		String v[] = s.split(",");
		Userinfo u = new Userinfo();
		u.setId(Integer.parseInt(v[0]));
		u.setName(v[1]);
		u.setPass(v[2]);
		u.setPetname(v[3]);
		u.setMail(v[4]);
		u.setSex(v[5]);
		u.setPower(v[6]);
		u.setIp(v[7]);
		return u;

	}

	public static String hechengmessages(List l) {
		String message = "";
		for (int i = 0; i < l.size(); i++) {
			Message m = (Message) l.get(i);
			message = message + m.getPetname() + "." + m.getMessage() + ";"
					+ m.getTime() + ",";
		}
		int i = message.lastIndexOf(",");
		if (i == -1)
			return message;
		// System.out.println("oooooooo     " + i);
		message = message.substring(0, i);
		return message;
	}

	public static Message chaifenmessage(String us) {
		Message ms = new Message();
		int i = us.indexOf('|');
		us = us.substring(i + 1);
		String v[] = us.split(",");
		ms.setGet_id(Integer.parseInt(v[0]));
		ms.setSend_id(Integer.parseInt(v[1]));
		ms.setMessage(v[2]);
		Timestamp time = Timestamp.valueOf(v[3]);
		ms.setTime(time);
		return ms;
	}

	public static String[] chaifenmessagess(String s) {
		int i = s.indexOf("|");
		if (i == s.length() - 1) {
			return null;
		} else {
			s = s.substring(i + 1);
			String v[] = s.split(",");
			return v;
		}
		// return v;
	}

	public static String hechengmessage(Message us) {
		String message = StateCode.SENDMESSAGE + "|";
		message = message + us.getGet_id() + "," + us.getSend_id() + ","
				+ us.getMessage() + "," + us.getTime();
		return message;
	}

	public static String hechengUserID(String head, String id, String ip) {
		String s = "";
		s = head + "|" + id + "," + ip;
		return s;
	}

	public static Userinfo chaifenUsernoip(String s) {
		int i = s.indexOf('|');
		s = s.substring(i + 1);
		String v[] = s.split(",");
		Userinfo u = new Userinfo();
		u.setId(Integer.parseInt(v[0]));
		u.setName(v[1]);
		u.setPass(v[2]);
		u.setPetname(v[3]);
		u.setMail(v[4]);
		u.setSex(v[5]);
		u.setPower(v[6]);
		// u.setIp(v[7]);
		return u;

	}

	public static String hechengfile(Message us, String ip) {
		String message = StateCode.SENDMESSAGE + "|";
		message = message + us.getGet_id() + "," + us.getSend_id() + ","
				+ us.getMessage() + "," + us.getTime();
		return message;
	}

}
