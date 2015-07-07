package com.neusoft.chatroom.service.model.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MessageDAO {
	private Connection con = null;
	public MessageDAO(Connection temp){
		con = temp;
	}
	//��ӷ���
	public boolean insert(Message m){
		boolean b = false;
		String sql = "insert into message (get_id,send_id,message,time) values(?,?,?,?)";
		PreparedStatement stat =null;
		try{
			stat = con.prepareStatement(sql);
			stat.setInt(1,m.getGet_id());
			stat.setInt(2,m.getSend_id());
			stat.setString(3, m.getMessage());
			stat.setTimestamp(4, m.getTime());
			int i = stat.executeUpdate();
			if(i > 0){
				b = true;
			}
		}catch(Exception e){
			System.out.println("������Ϣ������ʱ�������" + e.getMessage());
		}finally{
			DbUtils.close(null, stat, null);
		}
		
		return b;
	}
	//�޸ķ��� ---������id
	public boolean update(Message m){
		boolean b = false;
		String sql = "update message set get_id = ? , send_id =? ,message = ? ,time= ? where msg_id =?";
		PreparedStatement stat =null;
		try{
			stat = con.prepareStatement(sql);
			
			stat.setInt(1, m.getGet_id());
			stat.setInt(2,m.getSend_id());
			stat.setString(3,m.getMessage());
			stat.setTimestamp(4, m.getTime());
			stat.setInt(5,m.getId());
			
			int i = stat.executeUpdate();
			
			if(i > 0){
				b = true;
			}
		}catch(Exception e){
			System.out.println("������Ϣ���޸ĵ�ʱ�������" + e.getMessage());
		}finally{
			DbUtils.close(null, stat, null);
		}
		
		return b;
	}
	//ɾ������
	public boolean delete(Message m){
		boolean b = false;
		String sql = "delete from message where get_id = ?";
		PreparedStatement stat =null;
		try{
			stat = con.prepareStatement(sql);
			stat.setInt(1,m.getGet_id());
			int i = stat.executeUpdate();
			
			if(i > 0){
				b = true;
			}
		}catch(Exception e){
			System.out.println("������Ϣ��ɾ����ʱ�������" + e.getMessage());
		}finally{
			DbUtils.close(null, stat, null);
		}
		
		return b;
	}

	//��ѯ--��ѯ�������ߵ���
	public List selectAll(){
		List l = new ArrayList();
		String sql = "select * from message";
		PreparedStatement stat =null;
		ResultSet  rs = null;
		try{
			stat = con.prepareStatement(sql);
			rs = stat.executeQuery();
			while(rs.next()){
				Message m = new Message();
				m.setId(rs.getInt("msg_id"));
				m.setGet_id(rs.getInt("get_id"));
				m.setSend_id(rs.getInt("send_id"));
				m.setTime(rs.getTimestamp("time"));
				m.setMessage(rs.getString("message"));
				l.add(m);
			}
		}catch(Exception e){
			System.out.println("������Ϣ���ѯ������Ϣ��ʱ�������" + e.getMessage());
		}finally{
			DbUtils.close(rs, stat, null);
		}
		return l;
	}
	
	public List findMessage(Userinfo u){
		boolean b = false;
		PreparedStatement stat =null;
		ResultSet rs = null;
		
		List l = new ArrayList();
		try{
		stat = con.prepareStatement("select * from message,userinfo where send_id = userinfo.user_id and get_id =? ");
		stat.setInt(1,u.getId());
		rs = stat.executeQuery();

		while(rs.next()){		
			Message us = new Message();
			us.setPetname(rs.getString("petname"));
			us.setId(rs.getInt("msg_id"));
			//System.out.println("    11      ");
            us.setSend_id(rs.getInt("send_id"));
            us.setGet_id(rs.getInt("get_id"));
			us.setMessage(rs.getString("message"));
			us.setTime(rs.getTimestamp("time"));
			l.add(us);
		}	
	//	}
		}catch(Exception e){
			System.out.println("����ѯ��ѯ��Ϣ������ʱ�������" + e.getMessage());
		}finally{
			DbUtils.close(rs, stat, null);
		}
		return l;
	}
	//����get_id ��ѯ
	public boolean  findId(Message m){
		boolean b = false;
		String sql = "select * from message where get_id = ?";
		PreparedStatement stat =null;
		ResultSet  rs = null;
		try{
			stat = con.prepareStatement(sql);
			stat.setInt(1,m.getGet_id());
			rs = stat.executeQuery();
			if(rs.next()){
				b = true;
				m.setId(rs.getInt("msg_id"));
				m.setGet_id(rs.getInt("get_id"));
				m.setSend_id(rs.getInt("send_id"));
				m.setTime(rs.getTimestamp("time"));
				m.setMessage(rs.getString("message"));
				
			}
		}catch(Exception e){
			System.out.println("������Ϣ���ѯ������Ϣ��ʱ�������" + e.getMessage());
		}finally{
			DbUtils.close(rs, stat, null);
		}
		return b;
	}
	

}
