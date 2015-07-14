package com.neusoft.chatroom.api.service;

public class StateCode {
	public static final String LOGIN = "101";//登录
	public static final String REGISTER = "102";//注册-普通用户 添加-管理员
	public static final String EXIT = "103";//退出
	public static final String USERUPDATE = "104";//个人信息修改-普通用户
	public static final String ADMINUPDATE = "105";//修改个人的信息-管理员
	public static final String SELECTUSER = "106";//查询某个人的信息
	public static final String SELECTALL = "107";//查询所有人的信息
	public static final String DELETEUSER = "108";//删除用户信息
	public static final String SENDMESSAGE = "109";//发送信息
	public static final String SELECTUSERIP = "110";//查询某个人的ip地址
	public static final String SENDFILE = "111";//发送文件	
	public static final String SELECTALLONLINE = "112";//查询所有人的信息
	public static final String DELETEONLINEUSER = "113";//查询所有人的信息
	public static final String SUCCESS = "yes"; //返回信息
	public static final String ERROR = "no"; //返回信息
}
