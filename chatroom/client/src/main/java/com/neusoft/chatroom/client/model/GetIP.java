package com.neusoft.chatroom.client.model;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class GetIP {
	
	public static String getIp() {
		// ��ȡ������ip��ַ
		InetAddress addr = null;
		try {
			addr = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String IP = addr.getHostAddress().toString();
		return IP;
	}
}
