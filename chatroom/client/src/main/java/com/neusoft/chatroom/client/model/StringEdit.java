package com.neusoft.chatroom.client.model;

import java.sql.Timestamp;
import java.util.List;

public class StringEdit {
	// ��ֳ�Э���ͷ���� ���û���ص�Э�� ��
	// Э����|id,name,pass,petname,mail,sex,power,ip
	// ��ѯ���е��û���Э���ǣ�
	// Э����|id.name,id.name,id.name......,id.name

	// ----------�ϳ��ַ���(��һ��������������Ժϳ�һ���ַ���)
	public static String hechengUser(String head,
			com.neusoft.chatroom.client.model.Userinfo u, String ip) {
		String s = "";
		s = head + "|" + u.getId() + "," + u.getName() + "," + u.getPass()
				+ "," + u.getPetname() + "," + u.getMail() + "," + u.getSex()
				+ "," + u.getPower() + "," + ip;
		return s;
	}

	// ----------����ַ���(��һ���ַ����е�ÿһ���λ����ȡ������������һ������)

	// ��һ���ַ����а�״̬���ֳ���
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
