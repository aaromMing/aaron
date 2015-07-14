package com.neusoft.chatroom.service.model.tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import com.neusoft.chatroom.api.entity.Message;
import com.neusoft.chatroom.api.entity.Userinfo;
import com.neusoft.chatroom.api.service.IUserService;
import com.neusoft.chatroom.api.service.StateCode;
import com.neusoft.chatroom.service.model.service.UserService;



public class GET extends Thread{//��ȡ�ͻ��˵����󣬲������������Ӧ�ͻ���
	Socket s = null;
	Socket sends = null;
	LiGong l = null;
	Userinfo user = null;
	Thread t1 = null;//���͵��̵߳Ķ���
	IUserService us =ServiceProxy.getOwnerProxy(UserService.class);
	
	public GET(Socket stemp,Socket sendtemp,Userinfo u,LiGong xiaoli,Thread t1){
		s = stemp;
		sends = sendtemp;
		user = u;
		l = xiaoli;
		this.t1 = t1;
	}
	public void run(){
		try{
			while(true){
				BufferedReader b = new BufferedReader(new InputStreamReader (s.getInputStream()));
				PrintStream p = new PrintStream(s.getOutputStream());
				String request = b.readLine();
				String head = StringEdit.getStateCode(request);
				head = head.trim();
				if(head.equals(StateCode.LOGIN)){
					//��¼
					Userinfo u = StringEdit.chaifenUser(request);
					
					boolean blogin = us.denglu(u,u.getIp());
					user.setId(u.getId());
					user.setName(u.getName());
					user.setPass(u.getPass());
					user.setPetname(u.getPetname());
					user.setMail(u.getMail());
					user.setSex(u.getSex());
					user.setPower(u.getPower());
					if(blogin){
						//��¼�ɹ�
						l.FangXia();//���ѷ����߳�
						String s = StringEdit.hechengUser(StateCode.SUCCESS,u," ");
						p.println(s);
					}else{
						//��¼ʧ��
						p.println(StateCode.ERROR+"|");
					}
					
				}
				if(head.equals(StateCode.REGISTER)){
					//ע��
					Userinfo u = StringEdit.chaifenUser(request);
					//u.setPower("��ͨ�û�");
					boolean blogin = us.zhuce(u);
					if(blogin){
						//ע��ɹ�
						p.println(StateCode.SUCCESS+"|");
					}else{
						//ע��ʧ��
						p.println(StateCode.ERROR+"|");
					}
				}
				if(head.equals(StateCode.DELETEONLINEUSER)){
					int a = StringEdit.chaifenUserID1(request);
					boolean blogin = us.shanchuzaixianxinxi(a);
					if(blogin){
						//ע��ɹ�
						p.println(StateCode.SUCCESS+"|");
					}else{
						//ע��ʧ��
						p.println(StateCode.ERROR+"|");
					}
				}
				if(head.equals(StateCode.SENDMESSAGE)){
					//ע��
					Message u = StringEdit.chaifenmessage(request);
					boolean blogin = us.charuxinxi(u);
					if(blogin){
						//ע��ɹ�
						p.println(StateCode.SUCCESS+"|");
					}else{
						//ע��ʧ��
						p.println(StateCode.ERROR+"|");
					}
				}
				if(head.equals(StateCode.USERUPDATE)){
					//ע��
					Userinfo u = StringEdit.chaifenUser(request);
					
					boolean blogin = us.xiugai(u);
					if(blogin){
						//ע��ɹ�
						p.println(StateCode.SUCCESS+"|");
					}else{
						//ע��ʧ��
						p.println(StateCode.ERROR+"|");
					}
				}
				if(head.equals(StateCode.SELECTUSER)){
					Userinfo u = StringEdit.chaifenUserID(request);
					user.setId(u.getId());
					user.setName(u.getName());
					user.setPass(u.getPass());
					user.setPetname(u.getPetname());
					user.setMail(u.getMail());
					user.setSex(u.getSex());
					user.setPower(u.getPower());
						String s = StringEdit.hechengUser(StateCode.SUCCESS,user,user.getIp());
						p.println(s);
				}
				if(head.equals(StateCode.SELECTALL)){
					List l = new ArrayList();
					l = us.selectAll();
					String alluser = StringEdit.hechengUserS(StateCode.SUCCESS,l);
					p.println(alluser);
				}
				if(head.equals(StateCode.SELECTALLONLINE)){
					//Userinfo u = StringEdit.chaifenUser(request);
				
					List l = new ArrayList();
					l = us.selectAllonline();
					
					String alluser = StringEdit.hechengUserS2(StateCode.SUCCESS, l);		
				    p.println(alluser);
				}
				if(head.equals(StateCode.DELETEUSER)){
					//ע��
					Userinfo u = StringEdit.chaifenUser(request);
					//u.setPower("��ͨ�û�");
					boolean blogin = us.delete(u);
					if(blogin){
						//ɾ���ɹ�
						p.println(StateCode.SUCCESS+"|");
					}else{
						//ɾ��ʧ��
						p.println(StateCode.ERROR+"|");
					}
				}
				
				if(head.equals(StateCode.EXIT)){
					 //�û��˳�
					us.tuichu(user);
					t1.stop();
					s.close();
					sends.close();
					break;
				}
				
			}
		}catch(Exception e){
			System.out.println("�ͻ����Ѿ��˳���");
			//���û���״̬�޸ĳ�0������
			us.tuichu(user);
			t1.stop();
			try {
				s.close();
				sends.close();
			} catch (IOException e1) {
			
			}
		}
	}
}

	