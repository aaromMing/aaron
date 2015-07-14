package com.neusoft.chatroom.service.model.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.neusoft.chatroom.service.model.db.entity.OnlineUser;


public class OnlineUserDAO {
	private Connection con = null;
	public OnlineUserDAO(Connection temp){
		con = temp;//在创建dao对象的时候要传递进来一个链接对象
	}
	
	public boolean insert(OnlineUser u){
		String sql ="insert into onlineuser (user_id,ip,lasttime,State) values(?,?,?,?)";
		int i = -1;
		//插入数据
		try {
			PreparedStatement stat = con.prepareStatement(sql);
			stat.setInt(1,u.getUser_id());
			stat.setString(2,u.getIp());

			stat.setTimestamp(3,u.getLasttime());
			stat.setInt(4,u.getState());
			i = stat.executeUpdate();
		} catch (SQLException e) {
			System.out.println("在添加在线用户的时候出错了" + e.getMessage());
		}
		if(i >0){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean update(OnlineUser u){//根据id修改某一条信息
		String sql ="update onlineuser set ip =?,lasttime =?,State =? where user_id = ?";
		int i = -1;
		//插入数据
		try {
			PreparedStatement stat = con.prepareStatement(sql);
			stat.setString(1,u.getIp());

			stat.setTimestamp(2,u.getLasttime());
			stat.setInt(3,u.getState());
			stat.setInt(4,u.getUser_id());
			i = stat.executeUpdate();
		} catch (SQLException e) {
			System.out.println("在修改在线用户的时候出错了" + e.getMessage());
		}
		if(i >0){
			return true;
		}else{
			return false;
		}
		
	}
	
	public boolean delete(OnlineUser u){//根据用户的ID删除该用户信息
		String sql ="delete from onlineuser where user_id =?";
		int i = -1;
		//插入数据
		try {
			PreparedStatement stat = con.prepareStatement(sql);
			stat.setInt(1, u.getUser_id());
			i = stat.executeUpdate();
		} catch (SQLException e) {
			System.out.println("在删除在线用户的时候出错了" + e.getMessage());
		}
		if(i >0){
			return true;
		}else{
			return false;
		}
		
	}
	
//	查询--查询所有在线的人
	public List selectAll(){
		List l = new ArrayList();
		//String sql = "select * from onlineuser where  state = 1";
		String sql = "select userinfo.petname petname ,onlineuser.id id,onlineuser.user_id user_id,onlineuser.ip ip,onlineuser.lasttime lasttime,onlineuser.state state from onlineuser, userinfo where userinfo.user_id = onlineuser.user_id and state = 1";
		PreparedStatement stat =null;
		ResultSet  rs = null;
		try{
			stat = con.prepareStatement(sql);
			rs = stat.executeQuery();
			while(rs.next()){
				OnlineUser o = new OnlineUser();
			    o.setPetname(rs.getString("petname"));
				o.setId(rs.getInt("id"));
				o.setUser_id(rs.getInt("user_id"));
				o.setIp(rs.getString("ip"));
				o.setLasttime(rs.getTimestamp("lasttime"));
				o.setState(rs.getInt("state"));
				l.add(o);
			}
		}catch(Exception e){
			System.out.println("在向用户状态表查询所有在线人员的时候出错了" + e.getMessage());
		}finally{
			DbUtils.close(rs, stat, null);
		}
		return l;
	}
	public boolean deleteByUser_id(int user_id){
		boolean b = false;
		String sql = "delete from onlineuser where user_id = ?";
		PreparedStatement stat =null;
		try{
			stat = con.prepareStatement(sql);
			stat.setInt(1,user_id);
			int i = stat.executeUpdate();
			
			if(i > 0){
				b = true;
			}
		}catch(Exception e){
			System.out.println("在向用户状态表删除的时候出错了" + e.getMessage());
		}finally{
			DbUtils.close(null, stat, null);
		}
		
		return b;
	}
	public boolean findUser_id(OnlineUser u){//必须对参数U的id赋值后才能查询
		boolean b = false;
		PreparedStatement stat =null;
		ResultSet rs = null;
		try{
		stat = con.prepareStatement("select * from onlineuser where user_id =? ");
		stat.setInt(1,u.getUser_id());		
		rs = stat.executeQuery();
		if(rs.next()){
			//提取该用户的详细信息
			b = true;
			u.setId(rs.getInt("id"));
			u.setIp(rs.getString("ip"));
			u.setLasttime(rs.getTimestamp("lasttime"));
			u.setState(rs.getInt("State"));

		}
		}catch(Exception e){
			System.out.println("做查询在线用户操作的时候出错了" + e.getMessage());
		}finally{
			DbUtils.close(rs, stat, null);
		}
		return b;
	}
	public String findip(int id){
		String ip = null;
		String sql = "select ip from onlineuser where user_id = ?";
		PreparedStatement stat =null;
		ResultSet  rs = null;
		try{
			stat = con.prepareStatement(sql);
			stat.setInt(1,id);
			rs = stat.executeQuery();
			if(rs.next()){
				ip = rs.getString("ip");
			}
		}catch(Exception e){
			System.out.println("在向用户状态表查询所有在线人员的时候出错了" + e.getMessage());
		}finally{
			DbUtils.close(rs, stat, null);
		}
		return ip;
	}
	

}
