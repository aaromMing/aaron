package com.neusoft.chatroom.client.start;

import java.net.Socket;

import com.neusoft.chatroom.client.vo.Login;


public class ClientStartup {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Socket s = new Socket("localhost",4001);
			Socket smessage = new Socket("localhost",4002);
			new Login(s,smessage);
			
		} catch (Exception e) {
			System.out.println("Á´½Ó·þÎñÆ÷Ê§°Ü" + e.getMessage());
		}	

	}

}
