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
	JLabel f =new JLabel("�û��б�:");
	JLabel lu =new JLabel("�û���");//�����ڴ�������ʾ����
	JLabel oldm =new JLabel("������");
	JLabel m =new JLabel("����");
	JLabel mm =new JLabel("ȷ������");
	JLabel u =new JLabel("����");
	JLabel n =new JLabel("�ǳ�");
	JLabel x =new JLabel("�Ա�");
	JLabel i =new JLabel("Ȩ�ޣ�");
	
	JButton j = new JButton("�ύ");
	JButton c = new JButton("����");
	JButton q = new JButton("ȡ��");
	JPasswordField pold = new JPasswordField(10);
	JPasswordField p0 = new JPasswordField(10);
	JPasswordField p1 = new JPasswordField(10);
	
	JRadioButton r1 = new JRadioButton("��");
	JRadioButton r2 = new JRadioButton("Ů");
	ButtonGroup g = new ButtonGroup();
	
	JPanel p2 = new JPanel();
	public BackManagement(){
		b.addItem("Ann");
		b.addItem("Jack");
		b.addItem("Jone");
		b.addItem("Janny");
		w.addItem("��ͨ�û�");
		w.addItem("����Ա");
		g.add(r1);
		g.add(r2);
//		p0.setEchoChar('^');//�����������ʾ���ַ�
//		p1.setEchoChar('^');
		this.setTitle("��ʱͨ��ϵͳ������̨����");
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