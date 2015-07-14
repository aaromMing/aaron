package com.neusoft.chatroom.service.model.tools;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import com.neusoft.chatroom.api.entity.Userinfo;
import com.neusoft.chatroom.api.service.IUserService;
import com.neusoft.chatroom.api.service.StateCode;
import com.neusoft.chatroom.service.model.service.UserService;

public class SendThread extends Thread {
	Socket sends =null;
	LiGong l = null;
	Userinfo u = null;
	public SendThread(Socket sendtemp,Userinfo u,LiGong xiaoli){
		
		sends = sendtemp;
		this.u = u;
		l = xiaoli;
	}
	public void run(){
		l.GuaQi();
		System.out.println("登录人的ID是:"+u.getId());
		while(true){
			//每隔3秒中去数据库中查找当前的登录人的信息
			//有的话则发送
			//没有则
			IUserService us = new UserService();
			List l = new ArrayList();
			String messagelist = "";
			l = us.getMessage(u);
			if(l==null){
				messagelist = "";
			}else{
				messagelist  =  StringEdit.hechengmessages(l);
			}
			try {
				PrintStream p = new PrintStream(sends.getOutputStream());
				p.println(StateCode.SUCCESS+"|"+ messagelist);
			    us.delecttempmessage(l);
			} catch (IOException e1) {	
			}  
			try {
				sleep(3000);
			} catch (InterruptedException e) {			
			}
		}	
	}
}
