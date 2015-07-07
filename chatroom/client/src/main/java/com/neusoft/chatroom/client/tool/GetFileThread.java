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
//    	 1--------------���ӷ�������
		Socket s;
		try {
			s = new Socket(send_ip,4003);
			//
			this.jt.append("���������ӳɹ�����ʼ�����ļ�\n");
			//System.out.println("���������ӳɹ�����ʼ�����ļ�");
			// 2--------------��ȡ�������˷��͹���������
			BufferedInputStream bin = new BufferedInputStream(s.getInputStream());
			DataInputStream din = new DataInputStream(bin);
			// �ڶ��ֶ�ȡ��ʽ��ÿ�ζ�ȡ2kb������
			byte b[] = new byte[2048];
			
			int i = din.read(b);//��ȡ
			// 3-------------���ļ�������д�����ص��ļ���
			FileOutputStream f = new FileOutputStream("c:/" + filename);
			DataOutputStream d =new DataOutputStream(f);
			while(i != -1){
				d.write(b,0,i);//������д���ļ���
				i = din.read(b);
			}
			
			// 4-------------�ر����е���
			d.close();
			f.close();
			din.close();
			bin.close();
			s.close();
			//System.out.println("�ļ��������");
			this.jt.append("�ļ��������\n");
		} catch (UnknownHostException e) {
			//System.out.println("�ļ�����ʧ��");
			this.jt.append("�ļ��������\n");
		} catch (IOException e) {
			//System.out.println("�ļ�����ʧ��");
			this.jt.append("�ļ��������\n");
		}
    }
}

