package com.neusoft.chatroom.service.model.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;






/**
 * 
 * DAO������ã�
 * ��ĳһ�ű����� ɾ�� �޸� ��ѯ�������ṩ��Ӧ�ķ���
 *
 */
public class UserinfoDAO {
	private Connection con = null;
	public UserinfoDAO(Connection temp){
		con = temp;//�ڴ���dao�����ʱ��Ҫ���ݽ���һ�����Ӷ���
	}
	public boolean insert(Userinfo u){
		String sql ="insert into userinfo (name,pass,petname,mail,sex,power) values(?,?,?,?,?,?)";
		int i = -1;
		//��������
		try {
			PreparedStatement stat = con.prepareStatement(sql);
			stat.setString(1,u.getName());
			stat.setString(2,u.getPass());
			stat.setString(3,u.getPetname());
			stat.setString(4,u.getMail());
			stat.setString(5,u.getSex());
			stat.setString(6,u.getPower());
			i = stat.executeUpdate();
		} catch (SQLException e) {
			System.out.println("������û���ʱ�������" + e.getMessage());
		}
		if(i >0){
			return true;
		}else{
			return false;
		}
	}
	public boolean update(Userinfo u){//����id�޸�ĳһ���˵���Ϣ
		String sql ="update userinfo set name = ?,pass =?,petname =?,mail =?,sex =?,power = ? where user_id = ?";
		int i = -1;
		//��������
		try {
			PreparedStatement stat = con.prepareStatement(sql);
			stat.setString(1,u.getName());
			stat.setString(2,u.getPass());
			stat.setString(3,u.getPetname());
			stat.setString(4,u.getMail());
			stat.setString(5,u.getSex());
			stat.setString(6,u.getPower());
			stat.setInt(7, u.getId());
			i = stat.executeUpdate();
		} catch (SQLException e) {
			System.out.println("���޸��û���ʱ�������" + e.getMessage());
		}
		if(i >0){
			return true;
		}else{
			return false;
		}
		
	}
	
	public boolean delete(Userinfo u){//�����û���IDɾ�����û���Ϣ
		String sql ="delete from userinfo where user_id =?";
		int i = -1;
		//��������
		try {
			PreparedStatement stat = con.prepareStatement(sql);
			stat.setInt(1, u.getId());
			i = stat.executeUpdate();
		} catch (SQLException e) {
			System.out.println("��ɾ���û���ʱ�������" + e.getMessage());
		}
		if(i >0){
			return true;
		}else{
			return false;
		}
		
	}
	
	
	
	public boolean findName(Userinfo u){
		boolean b = false;
		PreparedStatement stat =null;
		ResultSet rs = null;
		try{
		stat = con.prepareStatement("select * from userinfo where name =? ");
		stat.setString(1,u.getName());
		
		rs = stat.executeQuery();
		b = rs.next();
		if(b){
			//��ȡ���û�����ϸ��Ϣ
			u.setPass(rs.getString("pass"));
			u.setId(rs.getInt("user_id"));
			u.setPetname(rs.getString("petname"));
			u.setMail(rs.getString("mail"));
			u.setSex(rs.getString("sex"));
			u.setPower(rs.getString("power"));
		}
		}catch(Exception e){
			System.out.println("����ѯ�û��������������ʱ�������" + e.getMessage());
		}finally{
			DbUtils.close(rs, stat, null);
		}
		return b;
	}
	
	
	
	
	public boolean findID(Userinfo u){//����Բ���U��id��ֵ����ܲ�ѯ
		boolean b = false;
		PreparedStatement stat =null;
		ResultSet rs = null;
		try{
		stat = con.prepareStatement("select * from userinfo where user_id =? ");
		stat.setInt(1,u.getId());
		
		rs = stat.executeQuery();
		b = rs.next();
		if(b){
			//��ȡ���û�����ϸ��Ϣ
			u.setPass(rs.getString("pass"));
			u.setName(rs.getString("name"));
			u.setPetname(rs.getString("petname"));
			u.setMail(rs.getString("mail"));
			u.setSex(rs.getString("sex"));
			u.setPower(rs.getString("power"));
		}
		}catch(Exception e){
			System.out.println("����ѯ�û��������������ʱ�������" + e.getMessage());
		}finally{
			DbUtils.close(rs, stat, null);
		}
		return b;
	}
	public Userinfo finduserbyid(String id){//����Բ���U��id��ֵ����ܲ�ѯ
		boolean b = false;
		PreparedStatement stat =null;
		ResultSet rs = null;
		Userinfo u = new Userinfo();
		int i = Integer.parseInt(id);
		try{
		stat = con.prepareStatement("select * from userinfo where user_id =? ");
		stat.setInt(1,i);
		
		rs = stat.executeQuery();
		b = rs.next();
		if(b){
			//��ȡ���û�����ϸ��Ϣ
			u.setId(rs.getInt("user_id"));
			u.setPass(rs.getString("pass"));
			u.setName(rs.getString("name"));
			u.setPetname(rs.getString("petname"));
			u.setMail(rs.getString("mail"));
			u.setSex(rs.getString("sex"));
			u.setPower(rs.getString("power"));
			return u;
		}
		}catch(Exception e){
			System.out.println("����ѯ�û��������������ʱ�������" + e.getMessage());
		}finally{
			DbUtils.close(rs, stat, null);
		}
	    return null;
	}
	
	public boolean findID(String id,Userinfo u){//����Բ���U��id��ֵ����ܲ�ѯ
		boolean b = false;
		PreparedStatement stat =null;
		ResultSet rs = null;
		try{
		stat = con.prepareStatement("select * from userinfo where user_id =? ");
		stat.setString(1,id);
		
		rs = stat.executeQuery();
		b = rs.next();
		if(b){
			//��ȡ���û�����ϸ��Ϣ
			u.setPass(rs.getString("pass"));
			u.setName(rs.getString("name"));
			u.setPetname(rs.getString("petname"));
			u.setMail(rs.getString("mail"));
			u.setSex(rs.getString("sex"));
			u.setPower(rs.getString("power"));
		}
		}catch(Exception e){
			System.out.println("����ѯ�û��������������ʱ�������" + e.getMessage());
		}finally{
			DbUtils.close(rs, stat, null);
		}
		return b;
	}
	
	
	public List selectAll(){//ȫ����ѯ
		PreparedStatement stat =null;
		ResultSet rs = null;
		List l = new ArrayList();
		try{
		stat = con.prepareStatement("select * from userinfo ");
		rs = stat.executeQuery();
		
		while(rs.next()){
			Userinfo u = new Userinfo();
			//��ȡ���û�����ϸ��Ϣ
			u.setId(rs.getInt("user_id"));
			u.setPass(rs.getString("pass"));
			u.setName(rs.getString("name"));
			u.setPetname(rs.getString("petname"));
			u.setMail(rs.getString("mail"));
			u.setSex(rs.getString("sex"));
			u.setPower(rs.getString("power"));
			l.add(u);
		}
		}catch(Exception e){
			System.out.println("����ѯ�û��������������ʱ�������" + e.getMessage());
		}finally{
			DbUtils.close(rs, stat, null);
		}
		return l;
	}

}
