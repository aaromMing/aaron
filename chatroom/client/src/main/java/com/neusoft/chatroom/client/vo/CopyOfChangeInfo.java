package com.neusoft.chatroom.client.vo;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.neusoft.chatroom.client.model.GetIP;
import com.neusoft.chatroom.client.model.StateCode;
import com.neusoft.chatroom.client.model.StringEdit;
import com.neusoft.chatroom.client.model.Userinfo;


public class CopyOfChangeInfo extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private JLabel jluname = null;

	private JLabel jLwel = null;

	private JTextField jtuname = null;

	private JLabel jlp = null;

	private JPasswordField jp1 = null;

	private JLabel jlpOldPasswd = null;
	
	private JLabel jlp2 = null;

	private JPasswordField jP2 = null;

	private JLabel jlmail = null;

	private JTextField jtfage = null;

	private JLabel jlsex = null;

	private JLabel jlpetname = null;

	private JTextField jtfpetname = null;

	private JRadioButton jrdboy = null;

	private JRadioButton jrbgirl = null;

	private JButton jttj = null;

	private JButton jbrew = null;
	Socket s = null;
	Userinfo u = null;
	ButtonGroup bg = new ButtonGroup();

	/**
	 * This is the default constructor
	 */
	public CopyOfChangeInfo(Socket s,Userinfo utemp) {
		super();
		this.s =s;
		u = utemp;
		initialize();
	}
	
	public CopyOfChangeInfo(Userinfo utemp) {
		super();
		u = utemp;
		initialize();
	}
	
	public static void main(String[] args) {
		Userinfo userInfo = new Userinfo();
		userInfo.setName("ming");
		userInfo.setSex("��");
		new CopyOfChangeInfo(userInfo);
	}
	

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(359, 524);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/com/neusoft/chatroom/client/vo/Icon.jpg")));
		this.setContentPane(getJContentPane());
		this.setTitle("�û���Ϣ�޸�");
		this.setVisible(true);
		this.setDefaultCloseOperation(3);
		this.setResizable(false);
		this.setLocation(350, 280);
		bg.add(jrdboy);
		bg.add(jrbgirl);
	}
	
	public void close(){
		this.setVisible(false);
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jlpetname = new JLabel();
			jlpetname.setText("     ��  ��");
			jlsex = new JLabel();
			jlsex.setText("     ��  ��");
			jlmail = new JLabel();
			jlmail.setText("    ��  ��");
			jlpOldPasswd = new JLabel();
			jlpOldPasswd.setText("    ������");
			jlp2 = new JLabel();
			jlp2.setText("    ȷ������");
			jlp = new JLabel();
			jlp.setText("   ��  ��");
			jLwel = new JLabel();
			jLwel.setText("      �� �� �� Ϣ �� ��");
			jLwel.setFont(new Font("������",Font.BOLD,20));
			jluname = new JLabel();
			jluname.setText("  �û���");
			jContentPane = new JPanel();
			jContentPane.setBackground(new Color(255,255,220));
			jContentPane.setLayout(new GridLayout(12,2));
			jContentPane.add(jluname, null);
			jContentPane.add(jLwel, null);
			jContentPane.add(getJtuname(), null);
			jContentPane.add(jlp, null);
			jContentPane.add(getJp1(), null);
			jContentPane.add(jlp2, null);
//			jContentPane.add(jlpOldPasswd, null);
			jContentPane.add(getJP2(), null);
			jContentPane.add(jlmail, null);
			jContentPane.add(getJtfage(), null);
			jContentPane.add(jlsex, null);
			jContentPane.add(jlpetname, null);
			jContentPane.add(getJtfpetname(), null);
			jContentPane.add(getJrdboy(), null);
			jContentPane.add(getJrbgirl(), null);
			jContentPane.add(getJttj(), null);
			jContentPane.add(getJbrew(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jtuname	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJtuname() {
		if (jtuname == null) {
			jtuname = new JTextField();
			jtuname.setText(u.getName());
			jtuname.setEnabled(false);
		}
		return jtuname;
	}

	/**
	 * This method initializes jp1	
	 * 	
	 * @return javax.swing.JPasswordField	
	 */
	private JPasswordField getJp1() {
		if (jp1 == null) {
			jp1 = new JPasswordField();
			jp1.setText(u.getPass());
		}
		return jp1;
	}

	/**
	 * This method initializes jP2	
	 * 	
	 * @return javax.swing.JPasswordField	
	 */
	private JPasswordField getJP2() {
		if (jP2 == null) {
			jP2 = new JPasswordField();
			jP2.setText(u.getPass());
			jP2.addFocusListener(new java.awt.event.FocusAdapter() {
				public void focusLost(java.awt.event.FocusEvent e) {
					String repass = jP2.getText();
						if (jP2.getText().equals(jp1.getText())){						
						}
						else{
							errormessage("���벻��", "������������벻ͬ�����������롣", 1);
						}			
				}
			});
		}
		return jP2;
	}

	/**
	 * This method initializes jtfage	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJtfage() {
		if (jtfage == null) {
			jtfage = new JTextField();
			jtfage.setText(u.getMail());
			jtfage.addFocusListener(new java.awt.event.FocusAdapter() {
				public void focusLost(java.awt.event.FocusEvent e) {
					boolean j = jtfage.getText().contains("@");
					boolean i = jtfage.getText().contains(".");
					int length = jtfage.getText().length();
					if (!i){
						errormessage("�����ʽ����", "������������ʽ�������������롣", 1);	
					}
					else{
						if (!j){
							errormessage("�����ʽ����", "������������ʽ�������������롣", 1);
						}
					}
				}
			});
		}
		return jtfage;
	}

	/**
	 * This method initializes jtfpetname	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJtfpetname() {
		if (jtfpetname == null) {
			jtfpetname = new JTextField();
			jtfpetname.setText(u.getPetname());
		}
		return jtfpetname;
	}

	/**
	 * This method initializes jrdboy	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getJrdboy() {
		if (jrdboy == null) {
			jrdboy = new JRadioButton();
			jrdboy.setText("��");
			if(u.getSex().equals("��"))
				jrdboy.setSelected(true);
		}
		return jrdboy;
	}

	/**
	 * This method initializes jrbgirl	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getJrbgirl() {
		if (jrbgirl == null) {
			jrbgirl = new JRadioButton();
			jrbgirl.setText("Ů");
			if(u.getSex().equals("Ů"))
				jrbgirl.setSelected(true);
		}
		return jrbgirl;
	}

	/**
	 * This method initializes jttj	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJttj() {
		if (jttj == null) {
			jttj = new JButton();
			jttj.setText("�ύ");
			jttj.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					 if(jtuname.getText().equals("")||jp1.getText().equals("")
							 ||jP2.getText().equals("")||jtfage.getText().equals("")
							 ||jtfpetname.getText().equals("")){
						 errormessage("�޸Ĵ���","ѡ�����ȫ����д��", 0);
					 }else if(!jrdboy.isSelected()&&!jrbgirl.isSelected()){
						 errormessage("�޸Ĵ���","ѡ�����ȫ����д��", 0);
					 }else if(!jp1.getText().equals(jP2.getText())){
						 errormessage("�޸Ĵ���","�������벻һ�£�", 0);
					 }
					 else{						
						//u.setName(jtuname.getText());
						u.setPass(jp1.getText());
						u.setPetname(jtfpetname.getText());
						u.setMail(jtfage.getText());
						if(jrdboy.isSelected()){
							u.setSex("��");
						}
						if(jrbgirl.isSelected()){
							u.setSex("Ů");
						}
						u.setPower("��ͨ�û�");
						String ip = GetIP.getIp();
						String request = StringEdit.hechengUser(StateCode.USERUPDATE, u, ip);	
						try {
							PrintStream p = new PrintStream(s.getOutputStream());
							p.println(request);
							BufferedReader b = new BufferedReader(new InputStreamReader(s.getInputStream()));
							String response = b.readLine();
							String head = StringEdit.getStateCode(response);
							if(head.equals(StateCode.SUCCESS)){
								//ע��ɹ�
								errormessage("�޸ĳɹ�","��ϲ���޸ĳɹ�", 1);
								close();
								
							}else{
								//ע����Ϣ������
								errormessage("�޸Ĵ���","�޸ĳ�����������д", 0);
							}
							
						} catch (IOException e1) {
							System.out.println("�ڷ��͵�ʱ�������");
						  }				
					}
				}
			});
		}
		return jttj;
	}
	public void  errormessage(String title,String value,int i){
		JOptionPane.showMessageDialog(this, value,title,i);
		//������Ϣ�� ���ĸ������� ��һ�������ǣ�������󣨻����ĸ����嵯�����ڶ�����������ʾ������ ���������������� ���ĸ�������ͼ��(0~3)
	}

	/**
	 * This method initializes jbrew	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJbrew() {
		if (jbrew == null) {
			jbrew = new JButton();
			jbrew.setText("ȡ��");
			jbrew.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					close();
					
				}
			});
		}
		return jbrew;
	}

}  //  @jve:decl-index=0:visual-constraint="247,41"
