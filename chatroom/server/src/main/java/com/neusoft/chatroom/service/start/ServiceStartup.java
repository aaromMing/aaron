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
		System.out.println("��������������.......");
		try {
			ServerSocket so = new ServerSocket(4001);
			ServerSocket sendso = new ServerSocket(4002);
			while(true){
				Socket s = so.accept();
				Socket sends = sendso.accept();
				System.out.println("�пͻ��˷�����");
				Thread t = new UserThread(s,sends);
				t.start();
			}
		} catch (IOException e) {
			System.out.println("��������������ʱ�������" + e.getMessage());
		}	

	}

}
