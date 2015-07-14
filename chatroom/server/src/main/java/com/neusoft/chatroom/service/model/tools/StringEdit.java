package com.neusoft.chatroom.service.model.tools;

import java.sql.Timestamp;
import java.util.List;





import com.neusoft.chatroom.service.model.db.Message;
import com.neusoft.chatroom.service.model.db.OnlineUser;
import com.neusoft.chatroom.service.model.db.Userinfo;
import com.neusoft.chatroom.service.model.service.IUserService;
import com.neusoft.chatroom.service.model.service.UserService;

public class StringEdit {
//	拆分出协议的头部分 跟用户相关的协议 ：
	//协议码|id,name,pass,petname,mail,sex,power,ip
	//查询所有的用户的协议是：
	//协议码|id.name,id.name,id.name......,id.name 
	
	
	/**
	 * ----------合成字符串(把一个对象的所有属性合成一个字符串)
	 * @param head
	 * @param u
	 * @param ip
	 * @return
	 */
	public static String hechengUser(String head,Userinfo u,String ip){
		String s = "";
		s = head+"|"+u.getId()+","+u.getName()+","+u.getPass()+
		"," + u.getPetname() +"," +u.getMail()+","+u.getSex()+","+u.getPower()+","+ip;
		return s;
	}
	public static String hechengUser(String head,Userinfo u){
		String s = "";
		s = head+"|"+u.getId()+","+u.getName()+","+u.getPass()+
		"," + u.getPetname() +"," +u.getMail()+","+u.getSex()+","+u.getPower()+",";
		return s;
	}
	/**
	 * ----------拆分字符串(把一个字符串中的每一项按照位子提取出来，并返回一个对象)
	 * @param s
	 * @return
	 */
	public static Userinfo chaifenUser(String s){
		int i = s.indexOf('|');
		s = s.substring(i+1);
		String v[] = s.split(",");
		Userinfo u = new Userinfo();
		u.setId(Integer.parseInt(v[0]));
		u.setName(v[1]);
		u.setPass(v[2]);
		u.setPetname(v[3]);
		u.setMail(v[4]);
		u.setSex(v[5]);
		u.setPower(v[6]);
		u.setIp(v[7]);
		return u;	
	}

	public static  String getStateCode(String s){
		int i = s.indexOf('|');
		s = s.substring(0,i);
		return s;
	}
	public static String hechengUserS(String head,List l){
		String s = head+"|";
		for(int i=0;i<l.size();i++){
		Userinfo u = (Userinfo)l.get(i);
		s = s + u.getId()+"."+u.getPetname() +",";
		}
		int i = s.lastIndexOf(",");
		s = s.substring(0,i);
		return s;
	}
	public static String hechengUserS2(String head,List l){
		String s = head+"|";
		for(int i=0;i<l.size();i++){
        OnlineUser u = (OnlineUser)l.get(i);
		s = s + u.getUser_id()+"."+u.getPetname() +",";
		}
		int i = s.lastIndexOf(",");
		s = s.substring(0,i);
		return s;
	}
	public static int chaifenUserID1(String s){
		int i = s.indexOf('|');
		s = s.substring(i+1);
		String v[] = s.split(",");
		int j = Integer.parseInt(v[0]);
		return j;
	}
	public static String [] chaifenUsers(String s){
		int i = s.indexOf("|");
		s = s.substring(i+1);
		String v[] = s.split(",");
		return v;
		
	}
	public static Userinfo chaifenUserID(String s){
		int i = s.indexOf('|');
		s = s.substring(i+1);
		String v[] = s.split(",");
		Userinfo u = new Userinfo();
		IUserService us = new UserService();
		u = us.chaxundanyi(v[0]);
		return u;
	
	}
	/*public static String hechengmessages(List l){
		  String message ="";
	      for(int i = 0;i<l.size();i++){
	    	  Message m = (Message)l.get(i);
	    	  message = message + m.getPetname() + "." + m.getMessage()+";"+m.getTime() + ",";
	      }
	      int i = message.lastIndexOf(",");
	      if(i==-1)  return message;
	   
	      message = message.substring(0,i);
		  return message;
	  }*/
	public static String hechengmessages(List l){
		  String message ="";
		  IUserService us = new UserService();
	      for(int i = 0;i<l.size();i++){
	    	  Message m = (Message)l.get(i);
	    	  if(m.getMessage().length()>=11){
	    		  if(m.getMessage().substring(0, 6).equals("请接收文件:")){
	                  int id = m.getSend_id();                 
	                  String ip = us.findip(id);
		    		  message = message + m.getPetname() + "." + m.getMessage()+";"+m.getTime()+"!"+ip + ",";
		    	  }else{
		    		  message = message + m.getPetname() + "." + m.getMessage()+";"+m.getTime() +"!"+ ",";
		    	  }	  
	    	  }else{
	    		  message = message + m.getPetname() + "." + m.getMessage()+";"+m.getTime() +"!"+ ",";
	    	  }
//	    	  if(m.getMessage().substring(0, 6).equals("请接收文件:")){
//              int id = m.getSend_id();
//              UserService us = new UserService();
//              String ip = us.findip(id);
//	    		  message = message + m.getPetname() + "." + m.getMessage()+";"+m.getTime()+"!"+ip + ",";
//	    	  }else{
//	    		  message = message + m.getPetname() + "." + m.getMessage()+";"+m.getTime() +"!"+ ",";
//	    	  }	  
	      }
	      int i = message.lastIndexOf(",");
	      if(i==-1)  return message;
	      message = message.substring(0,i);
		  return message;
	  }
	  public static Message chaifenmessage(String us){
		  Message ms = new Message();
		  int i = us.indexOf('|');
			us = us.substring(i+1);
			String v[] = us.split(",");
			ms.setGet_id(Integer.parseInt(v[0]));
			ms.setSend_id(Integer.parseInt(v[1]));
			ms.setMessage(v[2]);
			Timestamp   time   =   Timestamp.valueOf(v[3]);
			ms.setTime(time);
		    return ms;
	  }
	
	public static String[] chaifenUserss(String s){
		int i = s.indexOf("|");
		s = s.substring(i+1);
		String v[] = s.split(",");
		return v;		
	}

}
	