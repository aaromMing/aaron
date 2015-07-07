package com.neusoft.chatroom.service.model.service;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;







import com.neusoft.chatroom.service.model.db.DbUtils;
import com.neusoft.chatroom.service.model.db.Message;
import com.neusoft.chatroom.service.model.db.MessageDAO;
import com.neusoft.chatroom.service.model.db.OnlineUser;
import com.neusoft.chatroom.service.model.db.OnlineUserDAO;
import com.neusoft.chatroom.service.model.db.Userinfo;
import com.neusoft.chatroom.service.model.db.UserinfoDAO;

public class UserService {
	public List selectAll(){
		Connection con = DbUtils.getConnection();
		UserinfoDAO ud = new UserinfoDAO(con);
		List l = ud.selectAll();
		return l;
	}
	public boolean denglu(Userinfo u,String ip){//�ͻ��˵�IP��ַ
		boolean b = false;
		Connection con = DbUtils.getConnection();
		UserinfoDAO ud = new UserinfoDAO(con);
		OnlineUserDAO od = new OnlineUserDAO(con);
		//��ѯ�û��������Ƿ���ȷ
		String pass = u.getPass();
		boolean blogin = ud.findName(u);//��ȷ��ѯ���û�������Ϣ
		if(blogin && pass.equals(u.getPass())){
			b = true;
				
				//��ѯ���û���״̬�����Ƿ��м�¼
				OnlineUser o = new OnlineUser();
				o.setUser_id(u.getId());
				boolean bonline = od.findUser_id(o);
				o.setIp(ip);
				Date d = new Date();
				Timestamp t = new Timestamp(d.getTime());
				o.setLasttime(t);
				o.setState(1);
				if(bonline){
					//�м�¼ -˵����ǰ��¼��
					 od.update(o);
						//�޸��û�״̬������
				}else{
					//û�м�¼�Ļ���˵�����û�û�е�¼�����ǵ�һ�η�
					 od.insert(o);
						//��״̬���в�����û���Ϣ����״̬����Ϊ1
				}
		}
		
		return b;
	}
	public boolean zhuce(Userinfo u){
		Connection con = DbUtils.getConnection();
		UserinfoDAO ud = new UserinfoDAO(con);
		boolean b = ud.findName(u);
		if(b){
			//���û����ظ�����ע��
			return false;
		}else{
			//�û����ǲ��ظ���
			boolean bzhuce = ud.insert(u);
			return bzhuce;
		}
	}
	public boolean xiugai(Userinfo u){
		Connection con = DbUtils.getConnection();
		UserinfoDAO ud = new UserinfoDAO(con);
		boolean xgs = ud.update(u);
		return xgs;
	}
	public boolean delete(Userinfo u){
		Connection con = DbUtils.getConnection();
		UserinfoDAO ud = new UserinfoDAO(con);
		boolean delete = ud.delete(u);
		return delete;
	}
	public boolean serchuserid(Userinfo u){
		Connection con = DbUtils.getConnection();
		UserinfoDAO ud = new UserinfoDAO(con);
		boolean b = ud.findID(u);
		if(b){
			//���û����ظ�����ע��
			return false;
		}else{
			//�û����ǲ��ظ���
			boolean bzhuce = ud.findID(u);
			return bzhuce;
		}
	}
	public List getMessage(Userinfo u){
		Connection con = DbUtils.getConnection();
		MessageDAO ud = new MessageDAO(con);	
		List b = new ArrayList();
	    b = ud.findMessage(u);
	    return b;
	}
	public boolean charuxinxi(Message u){
		Connection con = DbUtils.getConnection();
		MessageDAO ud = new MessageDAO(con);
		boolean b = ud.insert(u);
		return b;
	}
	public void delecttempmessage(List message){
		Connection con = DbUtils.getConnection();
		MessageDAO ud = new MessageDAO(con);
		for(int i = 0 ; i<message.size();i++){
			Message u = new Message();
			u = (Message)message.get(i);
			ud.delete(u);
		}	
	}
	public boolean shanchuzaixianxinxi(int id){
		Connection con = DbUtils.getConnection();
		OnlineUserDAO us = new OnlineUserDAO(con);
		boolean b = us.deleteByUser_id(id);
		return b;
	}
	public boolean serchuserid(Userinfo u,String id){
		Connection con = DbUtils.getConnection();
		UserinfoDAO ud = new UserinfoDAO(con);
		boolean b = ud.findID(id,u);
		return b;
	}
	public Userinfo chaxundanyi(String id){
		Connection con = DbUtils.getConnection();
		UserinfoDAO ud = new UserinfoDAO(con);
		
		Userinfo b = ud.finduserbyid(id);
		return b;
	}
	public List selectAllonline(){
		Connection con = DbUtils.getConnection();
		OnlineUserDAO ud = new OnlineUserDAO(con);
		List l = ud.selectAll();
		return l;
	}
	public List selectall(Userinfo u){
		Connection con = DbUtils.getConnection();
		UserinfoDAO ud = new UserinfoDAO(con);
		List l = ud.selectAll();
		return l;
	}
	
	public void tuichu(Userinfo u){
		Connection con = DbUtils.getConnection();
		UserinfoDAO ud = new UserinfoDAO(con);
		OnlineUserDAO od = new OnlineUserDAO(con);
		OnlineUser o = new OnlineUser();
		o.setUser_id(u.getId());
		boolean b = od.findUser_id(o);
		if(b){
			o.setState(0);
			Date d = new Date();
			Timestamp t = new Timestamp(d.getTime());
			o.setLasttime(t);
			od.update(o);
		}
		
	}
	public String findip(int id){
		Connection con = DbUtils.getConnection();
		OnlineUserDAO od = new OnlineUserDAO(con);
		String ip = od.findip(id);
		return ip;
	}
}
