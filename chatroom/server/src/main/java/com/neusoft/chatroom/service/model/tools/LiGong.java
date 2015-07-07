package com.neusoft.chatroom.service.model.tools;

public class LiGong {
	public synchronized void GuaQi(){
		try {
			wait();
		} catch (InterruptedException e) {
			
		}
	}
	public synchronized void FangXia(){
		notify();
	}
	
}
