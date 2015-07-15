package com.neusoft.chatroom.api.service;

import java.io.Serializable;

@SuppressWarnings("serial")
public class StateCode implements Serializable {
	
	public static final String LOGIN = "101";//��¼
	public static final String REGISTER = "102";//ע��-��ͨ�û� ���-����Ա
	public static final String EXIT = "103";//�˳�
	public static final String USERUPDATE = "104";//������Ϣ�޸�-��ͨ�û�
	public static final String ADMINUPDATE = "105";//�޸ĸ��˵���Ϣ-����Ա
	public static final String SELECTUSER = "106";//��ѯĳ���˵���Ϣ
	public static final String SELECTALL = "107";//��ѯ�����˵���Ϣ
	public static final String DELETEUSER = "108";//ɾ���û���Ϣ
	public static final String SENDMESSAGE = "109";//������Ϣ
	public static final String SELECTUSERIP = "110";//��ѯĳ���˵�ip��ַ
	public static final String SENDFILE = "111";//�����ļ�	
	public static final String SELECTALLONLINE = "112";//��ѯ�����˵���Ϣ
	public static final String DELETEONLINEUSER = "113";//��ѯ�����˵���Ϣ
	public static final String SUCCESS = "yes"; //������Ϣ
	public static final String ERROR = "no"; //������Ϣ
	
	private String stateCode;

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}
	
	
}
