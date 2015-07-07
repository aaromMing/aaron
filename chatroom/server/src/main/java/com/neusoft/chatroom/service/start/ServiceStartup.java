package com.neusoft.chatroom.service.start;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.neusoft.chatroom.service.model.tools.UserThread;



public class ServiceStartup {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("服务器端启动了.......");
		try {
			ServerSocket so = new ServerSocket(4001);
			ServerSocket sendso = new ServerSocket(4002);
			while(true){
				Socket s = so.accept();
				Socket sends = sendso.accept();
				System.out.println("有客户端访问了");
				Thread t = new UserThread(s,sends);
				t.start();
			}
		} catch (IOException e) {
			System.out.println("服务器端启动的时候出错了" + e.getMessage());
		}	

	}

}
