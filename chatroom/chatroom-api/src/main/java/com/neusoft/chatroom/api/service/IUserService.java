package com.neusoft.chatroom.api.service;

import java.util.List;

import com.neusoft.chatroom.api.entity.Message;
import com.neusoft.chatroom.api.entity.Userinfo;

public interface IUserService extends IService {

	List selectAll();

	boolean denglu(Userinfo u);

	boolean zhuce(Userinfo u);

	boolean xiugai(Userinfo u);

	boolean delete(Userinfo u);

	boolean serchuserid(Userinfo u);

	List getMessage(Userinfo u);

	boolean charuxinxi(Message u);

	void delecttempmessage(List message);

	boolean shanchuzaixianxinxi(int id);

	boolean serchuserid(Userinfo u, String id);

	Userinfo chaxundanyi(String id);

	List selectAllonline();

	List selectall(Userinfo u);

	void tuichu(Userinfo u);

	String findip(int id);

}