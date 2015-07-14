package com.neusoft.chatroom.service.model.service;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.neusoft.chatroom.service.model.db.DbUtils;
import com.neusoft.chatroom.service.model.db.MessageDAO;
import com.neusoft.chatroom.service.model.db.OnlineUserDAO;
import com.neusoft.chatroom.service.model.db.UserinfoDAO;
import com.neusoft.chatroom.service.model.db.entity.Message;
import com.neusoft.chatroom.service.model.db.entity.OnlineUser;
import com.neusoft.chatroom.service.model.db.entity.Userinfo;

public class UserService implements IUserService {
	
	@Override
	public List selectAll() {
		Connection con = DbUtils.getConnection();
		UserinfoDAO ud = new UserinfoDAO(con);
		List l = ud.selectAll();
		return l;
	}

	@Override
	public boolean denglu(Userinfo u, String ip) {// 客户端的IP地址
		boolean b = false;
		Connection con = DbUtils.getConnection();
		UserinfoDAO ud = new UserinfoDAO(con);
		OnlineUserDAO od = new OnlineUserDAO(con);
		// 查询用户和密码是否正确
		String pass = u.getPass();
		boolean blogin = ud.findName(u);// 正确查询该用户所有信息
		if (blogin && pass.equals(u.getPass())) {
			b = true;

			// 查询该用户在状态表中是否有记录
			OnlineUser o = new OnlineUser();
			o.setUser_id(u.getId());
			boolean bonline = od.findUser_id(o);
			o.setIp(ip);
			Date d = new Date();
			Timestamp t = new Timestamp(d.getTime());
			o.setLasttime(t);
			o.setState(1);
			if (bonline) {
				// 有记录 -说明以前登录过
				od.update(o);
				// 修改用户状态就行了
			} else {
				// 没有记录的话则说明该用户没有登录程序是第一次访
				od.insert(o);
				// 向状态表中插入该用户信息并把状态设置为1
			}
		}

		return b;
	}

	@Override
	public boolean zhuce(Userinfo u) {
		Connection con = DbUtils.getConnection();
		UserinfoDAO ud = new UserinfoDAO(con);
		boolean b = ud.findName(u);
		if (b) {
			// 该用户名重复不能注册
			return false;
		} else {
			// 用户名是不重复的
			boolean bzhuce = ud.insert(u);
			return bzhuce;
		}
	}

	@Override
	public boolean xiugai(Userinfo u) {
		Connection con = DbUtils.getConnection();
		UserinfoDAO ud = new UserinfoDAO(con);
		boolean xgs = ud.update(u);
		return xgs;
	}

	@Override
	public boolean delete(Userinfo u) {
		Connection con = DbUtils.getConnection();
		UserinfoDAO ud = new UserinfoDAO(con);
		boolean delete = ud.delete(u);
		return delete;
	}

	@Override
	public boolean serchuserid(Userinfo u) {
		Connection con = DbUtils.getConnection();
		UserinfoDAO ud = new UserinfoDAO(con);
		boolean b = ud.findID(u);
		if (b) {
			// 该用户名重复不能注册
			return false;
		} else {
			// 用户名是不重复的
			boolean bzhuce = ud.findID(u);
			return bzhuce;
		}
	}

	@Override
	public List getMessage(Userinfo u) {
		Connection con = DbUtils.getConnection();
		MessageDAO ud = new MessageDAO(con);
		List b = new ArrayList();
		b = ud.findMessage(u);
		return b;
	}

	@Override
	public boolean charuxinxi(Message u) {
		Connection con = DbUtils.getConnection();
		MessageDAO ud = new MessageDAO(con);
		boolean b = ud.insert(u);
		return b;
	}

	@Override
	public void delecttempmessage(List message) {
		Connection con = DbUtils.getConnection();
		MessageDAO ud = new MessageDAO(con);
		for (int i = 0; i < message.size(); i++) {
			Message u = new Message();
			u = (Message) message.get(i);
			ud.delete(u);
		}
	}

	@Override
	public boolean shanchuzaixianxinxi(int id) {
		Connection con = DbUtils.getConnection();
		OnlineUserDAO us = new OnlineUserDAO(con);
		boolean b = us.deleteByUser_id(id);
		return b;
	}

	@Override
	public boolean serchuserid(Userinfo u, String id) {
		Connection con = DbUtils.getConnection();
		UserinfoDAO ud = new UserinfoDAO(con);
		boolean b = ud.findID(id, u);
		return b;
	}

	@Override
	public Userinfo chaxundanyi(String id) {
		Connection con = DbUtils.getConnection();
		UserinfoDAO ud = new UserinfoDAO(con);

		Userinfo b = ud.finduserbyid(id);
		return b;
	}

	@Override
	public List selectAllonline() {
		Connection con = DbUtils.getConnection();
		OnlineUserDAO ud = new OnlineUserDAO(con);
		List l = ud.selectAll();
		return l;
	}

	@Override
	public List selectall(Userinfo u) {
		Connection con = DbUtils.getConnection();
		UserinfoDAO ud = new UserinfoDAO(con);
		List l = ud.selectAll();
		return l;
	}

	@Override
	public void tuichu(Userinfo u) {
		Connection con = DbUtils.getConnection();
		UserinfoDAO ud = new UserinfoDAO(con);
		OnlineUserDAO od = new OnlineUserDAO(con);
		OnlineUser o = new OnlineUser();
		o.setUser_id(u.getId());
		boolean b = od.findUser_id(o);
		if (b) {
			o.setState(0);
			Date d = new Date();
			Timestamp t = new Timestamp(d.getTime());
			o.setLasttime(t);
			od.update(o);
		}

	}

	@Override
	public String findip(int id) {
		Connection con = DbUtils.getConnection();
		OnlineUserDAO od = new OnlineUserDAO(con);
		String ip = od.findip(id);
		return ip;
	}
}
