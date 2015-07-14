package com.neusoft.chatroom.service.model.tools;

import java.net.Socket;

import com.neusoft.chatroom.api.entity.Userinfo;

public class UserThread extends Thread {
	Socket so = null;
	Socket sendso = null;
	public UserThread(Socket sotemp,Socket sendsotemp){
		so = sotemp;
		sendso = sendsotemp;
	}
	 public void run(){
		 LiGong g = new LiGong();
		 Userinfo u = new Userinfo();
		 Thread t1 = new SendThread(sendso,u,g);
		 Thread t2 = new GET(so,sendso,u,g,t1);
		 t1.start();
		 t2.start();
	 }
}
