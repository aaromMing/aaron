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



public class GET extends Thread{//获取客户端的请求，并处理该请求，响应客户端
	Socket s = null;
	Socket sends = null;
	LiGong l = null;
	Userinfo user = null;
	Thread t1 = null;//发送的线程的对象
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
					//登录
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
						//登录成功
						l.FangXia();//唤醒发送线程
						String s = StringEdit.hechengUser(StateCode.SUCCESS,u," ");
						p.println(s);
					}else{
						//登录失败
						p.println(StateCode.ERROR+"|");
					}
					
				}
				if(head.equals(StateCode.REGISTER)){
					//注册
					Userinfo u = StringEdit.chaifenUser(request);
					//u.setPower("普通用户");
					boolean blogin = us.zhuce(u);
					if(blogin){
						//注册成功
						p.println(StateCode.SUCCESS+"|");
					}else{
						//注册失败
						p.println(StateCode.ERROR+"|");
					}
				}
				if(head.equals(StateCode.DELETEONLINEUSER)){
					int a = StringEdit.chaifenUserID1(request);
					boolean blogin = us.shanchuzaixianxinxi(a);
					if(blogin){
						//注册成功
						p.println(StateCode.SUCCESS+"|");
					}else{
						//注册失败
						p.println(StateCode.ERROR+"|");
					}
				}
				if(head.equals(StateCode.SENDMESSAGE)){
					//注册
					Message u = StringEdit.chaifenmessage(request);
					boolean blogin = us.charuxinxi(u);
					if(blogin){
						//注册成功
						p.println(StateCode.SUCCESS+"|");
					}else{
						//注册失败
						p.println(StateCode.ERROR+"|");
					}
				}
				if(head.equals(StateCode.USERUPDATE)){
					//注册
					Userinfo u = StringEdit.chaifenUser(request);
					
					boolean blogin = us.xiugai(u);
					if(blogin){
						//注册成功
						p.println(StateCode.SUCCESS+"|");
					}else{
						//注册失败
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
					//注册
					Userinfo u = StringEdit.chaifenUser(request);
					//u.setPower("普通用户");
					boolean blogin = us.delete(u);
					if(blogin){
						//删除成功
						p.println(StateCode.SUCCESS+"|");
					}else{
						//删除失败
						p.println(StateCode.ERROR+"|");
					}
				}
				
				if(head.equals(StateCode.EXIT)){
					 //用户退出
					us.tuichu(user);
					t1.stop();
					s.close();
					sends.close();
					break;
				}
				
			}
		}catch(Exception e){
			System.out.println("客户端已经退出了");
			//把用户的状态修改成0不在线
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

	