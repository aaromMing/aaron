package com.neusoft.chatroom.client.vo;

import java.awt.FlowLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class BackManagement extends JFrame{
	
	JTextField t0 = new JTextField(10);
	JTextField t1 = new JTextField(10);
	JTextField t2 = new JTextField(10);
	JComboBox b = new JComboBox();
	JComboBox w =new JComboBox();
	JLabel f =new JLabel("用户列表:");
	JLabel lu =new JLabel("用户名");//作用在窗体上显示文字
	JLabel oldm =new JLabel("旧密码");
	JLabel m =new JLabel("密码");
	JLabel mm =new JLabel("确认密码");
	JLabel u =new JLabel("邮箱");
	JLabel n =new JLabel("昵称");
	JLabel x =new JLabel("性别");
	JLabel i =new JLabel("权限：");
	
	JButton j = new JButton("提交");
	JButton c = new JButton("重填");
	JButton q = new JButton("取消");
	JPasswordField pold = new JPasswordField(10);
	JPasswordField p0 = new JPasswordField(10);
	JPasswordField p1 = new JPasswordField(10);
	
	JRadioButton r1 = new JRadioButton("男");
	JRadioButton r2 = new JRadioButton("女");
	ButtonGroup g = new ButtonGroup();
	
	JPanel p2 = new JPanel();
	public BackManagement(){
		b.addItem("Ann");
		b.addItem("Jack");
		b.addItem("Jone");
		b.addItem("Janny");
		w.addItem("普通用户");
		w.addItem("管理员");
		g.add(r1);
		g.add(r2);
//		p0.setEchoChar('^');//设置密码框显示的字符
//		p1.setEchoChar('^');
		this.setTitle("即时通信系统－－后台管理");
		this.setSize(500,500);
		this.setLocation(350,280);
		this.setLayout(new FlowLayout());
		this.setResizable(false);
		this.add(f);
		this.add(b);
		this.add(lu);
		this.add(t0);
		
		this.add(oldm);
		this.add(pold);
		
		this.add(m);
		this.add(p0);
		this.add(mm);
		this.add(p1);
		this.add(u);
		this.add(t1);
		this.add(n);
		this.add(t2);
		this.add(x);
		this.add(r1);
		this.add(r2);
		this.add(i);
		this.add(w);
		this.add(j);
		this.add(c);
		this.add(q);
		this.setDefaultCloseOperation(3);
		this.setVisible(true);
	}
}