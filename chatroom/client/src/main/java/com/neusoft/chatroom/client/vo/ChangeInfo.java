package com.neusoft.chatroom.client.vo;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
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


public class ChangeInfo extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private JLabel jluname = null;

	private JLabel jLwel = null;

	private JTextField jtuname = null;

	private JLabel jlp = null;

	private JPasswordField jp1 = null;

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
	public ChangeInfo(Socket s,Userinfo utemp) {
		super();
		this.s =s;
		u = utemp;
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(359, 324);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/com/neusoft/chatroom/client/vo/Icon.jpg")));
		this.setContentPane(getJContentPane());
		this.setTitle("用户信息修改");
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
			jlpetname.setBounds(new Rectangle(25, 217, 63, 27));
			jlpetname.setText("     昵  称");
			jlsex = new JLabel();
			jlsex.setBounds(new Rectangle(25, 184, 61, 27));
			jlsex.setText("     性  别");
			jlmail = new JLabel();
			jlmail.setBounds(new Rectangle(26, 152, 60, 25));
			jlmail.setText("    邮  箱");
			jlp2 = new JLabel();
			jlp2.setBounds(new Rectangle(12, 119, 76, 28));
			jlp2.setText("    确认密码");
			jlp = new JLabel();
			jlp.setBounds(new Rectangle(32, 89, 57, 25));
			jlp.setText("   密  码");
			jLwel = new JLabel();
			jLwel.setBounds(new Rectangle(28, 6, 305, 35));
			jLwel.setText("      用 户 信 息 修 改");
			jLwel.setFont(new Font("新宋体",Font.BOLD,20));
			jluname = new JLabel();
			jluname.setBounds(new Rectangle(30, 51, 59, 28));
			jluname.setText("  用户名");
			jContentPane = new JPanel();
			jContentPane.setBackground(new Color(255,255,220));
			jContentPane.setLayout(null);
			jContentPane.add(jluname, null);
			jContentPane.add(jLwel, null);
			jContentPane.add(getJtuname(), null);
			jContentPane.add(jlp, null);
			jContentPane.add(getJp1(), null);
			jContentPane.add(jlp2, null);
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
			jtuname.setBounds(new Rectangle(96, 54, 240, 27));
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
			jp1.setBounds(new Rectangle(96, 84, 239, 29));
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
			jP2.setBounds(new Rectangle(98, 116, 237, 31));
			jP2.addFocusListener(new java.awt.event.FocusAdapter() {
				public void focusLost(java.awt.event.FocusEvent e) {
					String repass = jP2.getText();
						if (jP2.getText().equals(jp1.getText())){						
						}
						else{
							errormessage("密码不符", "两次输入的密码不同，请重新输入。", 1);
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
			jtfage.setBounds(new Rectangle(96, 153, 239, 28));
			jtfage.addFocusListener(new java.awt.event.FocusAdapter() {
				public void focusLost(java.awt.event.FocusEvent e) {
					boolean j = jtfage.getText().contains("@");
					boolean i = jtfage.getText().contains(".");
					int length = jtfage.getText().length();
					if (!i){
						errormessage("邮箱格式错误", "您输入的邮箱格式错误，请重新输入。", 1);	
					}
					else{
						if (!j){
							errormessage("邮箱格式错误", "您输入的邮箱格式错误，请重新输入。", 1);
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
			jtfpetname.setBounds(new Rectangle(100, 216, 231, 27));
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
			jrdboy.setBounds(new Rectangle(103, 187, 78, 27));
			jrdboy.setText("男");
			if(u.getSex().equals("男"))
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
			jrbgirl.setBounds(new Rectangle(204, 188, 72, 22));
			jrbgirl.setText("女");
			if(u.getSex().equals("女"))
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
			jttj.setBounds(new Rectangle(111, 249, 76, 33));
			jttj.setText("提交");
			jttj.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					 if(jtuname.getText().equals("")||jp1.getText().equals("")
							 ||jP2.getText().equals("")||jtfage.getText().equals("")
							 ||jtfpetname.getText().equals("")){
						 errormessage("修改错误","选项必须全部填写！", 0);
					 }else if(!jrdboy.isSelected()&&!jrbgirl.isSelected()){
						 errormessage("修改错误","选项必须全部填写！", 0);
					 }else if(!jp1.getText().equals(jP2.getText())){
						 errormessage("修改错误","两次密码不一致！", 0);
					 }
					 else{						
						//u.setName(jtuname.getText());
						u.setPass(jp1.getText());
						u.setPetname(jtfpetname.getText());
						u.setMail(jtfage.getText());
						if(jrdboy.isSelected()){
							u.setSex("男");
						}
						if(jrbgirl.isSelected()){
							u.setSex("女");
						}
						u.setPower("普通用户");
						String ip = GetIP.getIp();
						String request = StringEdit.hechengUser(StateCode.USERUPDATE, u, ip);	
						try {
							PrintStream p = new PrintStream(s.getOutputStream());
							p.println(request);
							BufferedReader b = new BufferedReader(new InputStreamReader(s.getInputStream()));
							String response = b.readLine();
							String head = StringEdit.getStateCode(response);
							if(head.equals(StateCode.SUCCESS)){
								//注册成功
								errormessage("修改成功","恭喜您修改成功", 1);
								close();
								
							}else{
								//注册信息有问题
								errormessage("修改错误","修改出错，请重新填写", 0);
							}
							
						} catch (IOException e1) {
							System.out.println("在发送的时候出错了");
						  }				
					}
				}
			});
		}
		return jttj;
	}
	public void  errormessage(String title,String value,int i){
		JOptionPane.showMessageDialog(this, value,title,i);
		//弹出消息框 的四个参数： 第一个参数是：窗体对象（基于哪个窗体弹出）第二个参数：显示的内容 第三个参数：标题 第四个参数：图标(0~3)
	}

	/**
	 * This method initializes jbrew	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJbrew() {
		if (jbrew == null) {
			jbrew = new JButton();
			jbrew.setBounds(new Rectangle(235, 249, 75, 32));
			jbrew.setText("取消");
			jbrew.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					close();
					
				}
			});
		}
		return jbrew;
	}

}  //  @jve:decl-index=0:visual-constraint="247,41"
