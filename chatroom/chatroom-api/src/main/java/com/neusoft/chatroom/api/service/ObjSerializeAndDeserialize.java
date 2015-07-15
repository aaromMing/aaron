package com.neusoft.chatroom.api.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import com.neusoft.chatroom.api.entity.Userinfo;

public class ObjSerializeAndDeserialize {

	//
	// public static Userinfo DeserializePerson(InputStream is,Class<T> obj)
	// throws Exception, IOException {
	// ObjectInputStream ois = new ObjectInputStream( is);
	// Userinfo userInfo = (Userinfo) ois.readObject();
	// System.out.println("Person对象反序列化成功！");
	// return userInfo;
	// }

	public static Object Deserialize(InputStream is) {
		ObjectInputStream ois;
		Object newObj = null;
		try {
			ois = new ObjectInputStream(is);
			newObj = ois.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(newObj + "对象反序列化成功！");
		return newObj;
	}

	@SuppressWarnings("unchecked")
	public static <T extends StateCode> T DeserializePerson(InputStream is,
			Class<T> obj) {
		ObjectInputStream ois;
		T newObj = null;
		try {
			ois = new ObjectInputStream(is);
			newObj = (T) ois.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(newObj + "对象反序列化成功！");
		return newObj;
	}

	@SuppressWarnings("unchecked")
	public static <T extends StateCode> T DeserializePerson(Class<T> obj)
			throws Exception, IOException {
		InputStream is = new FileInputStream(new File("G:/Person.txt"));
		ObjectInputStream ois = new ObjectInputStream(is);
		T userInfo = (T) ois.readObject();
		System.out.println("对象反序列化成功！StateCode:" + userInfo.getStateCode());
		ois.close();
		return userInfo;
	}

	public static <T extends StateCode> void SerializeObj(T obj)
			throws FileNotFoundException, IOException {
		// ObjectOutputStream
		// 对象输出流，将Person对象存储到E盘的Person.txt文件中，完成对Person对象的序列化操作
		ObjectOutputStream oo = new ObjectOutputStream(new FileOutputStream(
				new File("G:/Person.txt")));
		oo.writeObject(obj);
		System.out.println("对象序列化成功！");
		oo.close();
	}

	public static <T extends StateCode> void SerializeObj(OutputStream out,
			T obj) throws IOException {
		// ObjectOutputStream
		// 对象输出流，将Person对象存储到E盘的Person.txt文件中，完成对Person对象的序列化操作
		try {
			ObjectOutputStream oo = new ObjectOutputStream(out);
			oo.writeObject(obj);
			System.out.println(obj + "对象序列化成功！");
			// oo.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void SerializeUserInfo() throws FileNotFoundException,
			IOException {
		Userinfo userInfo = new Userinfo();
		userInfo.setStateCode(StateCode.LOGIN);
		userInfo.setName("gacl");
		userInfo.setSex("男");
		// ObjectOutputStream
		// 对象输出流，将Person对象存储到E盘的Person.txt文件中，完成对Person对象的序列化操作
		ObjectOutputStream oo = new ObjectOutputStream(new FileOutputStream(
				new File("G:/Person.txt")));
		oo.writeObject(userInfo);
		System.out.println("Person对象序列化成功！");
		oo.close();
	}

	public static void main(String[] args) throws Exception {
		SerializeUserInfo();
		DeserializePerson(StateCode.class);
	}

}
