package com.neusoft.chatroom.service.model.db;

import java.sql.Timestamp;



public class Message {
  private int id;
  private int get_id;
  private int send_id;
  private String message;  
  private Timestamp time;
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
	public int getGet_id() {
		return get_id;
	}
	public void setGet_id(int get_id) {
		this.get_id = get_id;
	}
	public int getSend_id() {
		return send_id;
	}
	public void setSend_id(int send_id) {
		this.send_id = send_id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	
	
	
}