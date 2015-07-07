package com.neusoft.chatroom.client.tool;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JTextArea;
public class GetFileThread extends Thread{
	private String send_ip = null;
	private String filename = null;
	private JTextArea jt = null;
	public GetFileThread(String ip,String filename,JTextArea jt){
		send_ip = ip;
		this.filename = filename;
		this.jt = jt;
		System.out.println(ip+"   "+filename+"     "+jt);
	}
    public void run(){
//    	 1--------------链接服务器端
		Socket s;
		try {
			s = new Socket(send_ip,4003);
			//
			this.jt.append("服务器链接成功，开始下载文件\n");
			//System.out.println("服务器链接成功，开始下载文件");
			// 2--------------读取服务器端发送过来的内容
			BufferedInputStream bin = new BufferedInputStream(s.getInputStream());
			DataInputStream din = new DataInputStream(bin);
			// 第二种读取方式。每次读取2kb的内容
			byte b[] = new byte[2048];
			
			int i = din.read(b);//读取
			// 3-------------把文件的内容写到本地的文件中
			FileOutputStream f = new FileOutputStream("c:/" + filename);
			DataOutputStream d =new DataOutputStream(f);
			while(i != -1){
				d.write(b,0,i);//把内容写到文件中
				i = din.read(b);
			}
			
			// 4-------------关闭所有的流
			d.close();
			f.close();
			din.close();
			bin.close();
			s.close();
			//System.out.println("文件接收完成");
			this.jt.append("文件接收完成\n");
		} catch (UnknownHostException e) {
			//System.out.println("文件接收失败");
			this.jt.append("文件接收完成\n");
		} catch (IOException e) {
			//System.out.println("文件接收失败");
			this.jt.append("文件接收完成\n");
		}
    }
}

