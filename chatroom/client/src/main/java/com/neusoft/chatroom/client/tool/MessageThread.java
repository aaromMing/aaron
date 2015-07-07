package com.neusoft.chatroom.client.tool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import javax.swing.JTextArea;

import com.neusoft.chatroom.client.model.GetIP;
import com.neusoft.chatroom.client.model.StateCode;
import com.neusoft.chatroom.client.model.StringEdit;



public class MessageThread extends Thread{
	Socket so = null;
	Socket sendso = null;
	JTextArea jt;
	static String ip = "";
	static String filename = "";
	public MessageThread(Socket sotemp,Socket sendsotemp,JTextArea jt){
		so = sotemp;
		sendso = sendsotemp;
		this.jt = jt;
	}
	public String isfile(String message){
		if(message.length()>=11){
			if(message.substring(0,6).equals("请接收文件:")){	
				return message;
			}else return "";	
		}else{
			return "";
		}
		
	}
	public static String ip(){
		return ip;
	}
	public static String filename(){
		return filename;
	}
	public void run(){		
		 BufferedReader b;
		 while(true){
				try {
					b = new BufferedReader(new InputStreamReader(sendso.getInputStream()));
					String response = b.readLine();
					String head = StringEdit.getStateCode(response);
					if(head.equals(StateCode.SUCCESS)){
						//解析信息
						String a[] = StringEdit.chaifenmessagess(response);
					
						if(a==null){
							try {
								sleep(3000);
							} catch (InterruptedException e) {			
							}
						}else{
							for(int i=0; i<a.length;i++){
								int j = a[i].indexOf(".");
								int p = a[i].indexOf(";");								
								int q = a[i].indexOf("!");
							    String temp = a[i].substring(p+1,q)+"\n"+a[i].substring(0,j)+"  说："+a[i].substring(j+1,p);
							    String b2 = isfile(a[i].substring(j+1,p));
							 
							   if(b2.equals("")){
							    	 ip = "";
							    }else{
							    	ip = a[i].substring(q+1);
							    	int fi = a[i].substring(j+1,p).indexOf(":");
							    	filename = a[i].substring(j+1,p).substring(fi+1);							    	
							    }
							    this.jt.append(temp + "\n");
					            
							}
							try {
								sleep(3000);
							} catch (InterruptedException e) {			
							}
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
		 }
	
	 }
}
