package com.neusoft.chatroom.client.vo;

import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.neusoft.chatroom.client.model.GetIP;
import com.neusoft.chatroom.client.model.StateCode;
import com.neusoft.chatroom.client.model.StringEdit;
import com.neusoft.chatroom.client.tool.SendFileThread;
import com.neusoft.chatroom.service.model.db.entity.Message;
import com.neusoft.chatroom.service.model.db.entity.Userinfo;

public class FileTransfer extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private JLabel jLabel1 = null;

	private JComboBox jComboBox1 = null;

	private JFileChooser jFileChooser1 = null;

	private JButton jButton1 = null;
	Userinfo u = null;
	 private List st;
	 Socket s = null;
	/**
	 * This is the default constructor
	 */
	public FileTransfer(Socket s, Userinfo utemp) {
		super();
		this.s=s;
		this.u = utemp;   
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(444, 333);
		this.setContentPane(getJContentPane());
		this.setTitle("����");
		this.setResizable(false);//���ô���Ĵ�С�Ƿ�����޸�
		this.setLocation(600, 300);
		this.setVisible(true);
	}
 private JFrame s(){
	 return this;
 }
	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(1, -1, 61, 36));
			jLabel1.setText("    ���͸�");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(jLabel1, null);
			jContentPane.add(getJComboBox1(), null);
			jContentPane.add(getJFileChooser1(), null);
			jContentPane.add(getJButton1(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jComboBox1	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getJComboBox1() {
		if (jComboBox1 == null) {
			jComboBox1 = new JComboBox();
			jComboBox1.setBounds(new Rectangle(61, 0, 259, 34));
			jComboBox1.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					jComboBox1.removeAllItems();
					String ip = GetIP.getIp();
					String request = StringEdit.hechengUser(StateCode.SELECTALLONLINE, u, ip);	
					try {
						PrintStream p = new PrintStream(s.getOutputStream());
						p.println(request); 
						BufferedReader b = new BufferedReader(new InputStreamReader(s.getInputStream()));
						String response = b.readLine();
						String head = StringEdit.getStateCode(response);
						if(head.equals(StateCode.SUCCESS)){
							String a[] = StringEdit.chaifenUsers(response);
							st = new ArrayList();
							for(int i=0; i<a.length;i++){
								int j = a[i].indexOf(".");
								st.add(a[i]);
								//st[i] = a[i];
								//a[i] = a[i].substring(j+1);
								jComboBox1.addItem(a[i]);
							}
						}else{
						}	
					} catch (IOException e1) {
						System.out.println("�ڷ��͵�ʱ�������");
					  }	
				}	
			});
		}
		return jComboBox1;
	}

	/**
	 * This method initializes jFileChooser1	
	 * 	
	 * @return javax.swing.JFileChooser	
	 */
	private JFileChooser getJFileChooser1() {
		if (jFileChooser1 == null) {
			jFileChooser1 = new JFileChooser();
			jFileChooser1.setBounds(new Rectangle(1, 36, 424, 252));
		}
		return jFileChooser1;
	}

	/**
	 * This method initializes jButton1	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton1() {
		if (jButton1 == null) {
			jButton1 = new JButton();
			jButton1.setBounds(new Rectangle(323, 1, 98, 33));
			jButton1.setText("����");
			jButton1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
				String filename = jFileChooser1.getSelectedFile().getAbsolutePath();
				  SendFileThread sf = new SendFileThread(filename);
				  String ip = GetIP.getIp();
				  if(jComboBox1.getSelectedItem().toString().equals("ȫ���û�")){
						for(int i = 0; i<(jComboBox1.getItemCount()-1);i++){
							int j = st.get(i).toString().indexOf(".");
							int id = Integer.parseInt(st.get(i).toString().substring(0,j));
							Message us = new Message();
							us.setGet_id(id);
							us.setSend_id(u.getId());
							us.setMessage("������ļ�:" + jFileChooser1.getSelectedFile().getName());
							Date time  = new Date();
							Timestamp tm = new Timestamp(time.getTime());	
							us.setTime(tm);
							
							String request = StringEdit.hechengfile(us,ip);
							try {
								PrintStream p = new PrintStream(s.getOutputStream());
								p.println(request);
								BufferedReader b = new BufferedReader(new InputStreamReader(s.getInputStream()));
								String response = b.readLine();
								String head = StringEdit.getStateCode(response);
								if(head.equals(StateCode.SUCCESS)){
									 sf.start();
									 close();
								}else{
									errormessage("���ʹ���","����ʧ�ܣ�", 0);
									close();
								}
								
							} catch (IOException e1) {
								System.out.println("�ڷ��͵�ʱ�������");
							}
						}
					}else{
						
						Message us= new Message();
						us.setSend_id(u.getId());
						String name = (String)jComboBox1.getSelectedItem();
						int i = name.indexOf(".");
						us.setGet_id(Integer.parseInt(name.substring(0,i)));
						us.setMessage("������ļ�:"+ jFileChooser1.getSelectedFile().getName());
						Date time  = new Date();
						Timestamp tm = new Timestamp(time.getTime());	
						us.setTime(tm);
						
						SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�����ʽ������ʾ����
						Timestamp now = new Timestamp(System.currentTimeMillis());//��ȡϵͳ��ǰʱ��
						String str = df.format(now);

						String request = StringEdit.hechengfile(us,ip);
						try {
							PrintStream p = new PrintStream(s.getOutputStream());
							p.println(request);
							BufferedReader b = new BufferedReader(new InputStreamReader(s.getInputStream()));
							String response = b.readLine();
							String head = StringEdit.getStateCode(response);
							if(head.equals(StateCode.SUCCESS)){
								 sf.start();
								 close();
							}else{
								errormessage("���ʹ���","����ʧ�ܣ�", 0);
								close();
							}
							
						} catch (IOException e1) {
							System.out.println("�ڷ��͵�ʱ�������");
						}
					}				
				 
			
				}
			});
		}
		return jButton1;
	}
	public void close(){
		this.setVisible(false);
	}
	public void  errormessage(String title,String value,int i){
		JOptionPane.showMessageDialog(this, value,title,i);
		//������Ϣ�� ���ĸ������� ��һ�������ǣ�������󣨻����ĸ����嵯�����ڶ�����������ʾ������ ���������������� ���ĸ�������ͼ��(0~3)
	}
}  //  @jve:decl-index=0:visual-constraint="10,10"
