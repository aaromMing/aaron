package com.neusoft.chatroom.api.entity;

import java.sql.Timestamp;

public class OnlineUser {
	private int id;
	private int user_id;
	private String ip;
	private Timestamp lasttime;
	private int state ;//0代表 不在线 1代表在线
	private String petname;	
	
	public String getPetname() {
		return petname;
	}
	public void setPetname(String petname) {
		this.petname = petname;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	
	public Timestamp getLasttime() {
		return lasttime;
	}
	public void setLasttime(Timestamp lasttime) {
		this.lasttime = lasttime;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	
}
