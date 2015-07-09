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

	private JLabel jlpOldPasswd = null;
	
	private JLabel jlp2 = null;

	private JPasswordField jP2 = null;

	private JLabel jlmail = null;

	private JTextField jtfage = null;

	private JLabel jlsex = null;

	private JLabel jlpetname = null;

	private JTextField jtfpetname = null;
	
	private JTextField jtfOldPasswd = null;

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
		this.setSize(359, 384);
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
			jlpetname.setBounds(new Rectangle(22, 251, 63, 27));
			jlpetname.setText("\u6635    \u79F0");
			jlsex = new JLabel();
			jlsex.setBounds(new Rectangle(22, 218, 61, 27));
			jlsex.setText("\u6027    \u522B");
			jlmail = new JLabel();
			jlmail.setBounds(new Rectangle(22, 186, 60, 25));
			jlmail.setText("\u90AE    \u7BB1");
			jlp2 = new JLabel();
			jlp2.setBounds(new Rectangle(22, 153, 76, 28));
			jlp2.setText("\u786E\u8BA4\u5BC6\u7801");
			jlp = new JLabel();
			jlp.setBounds(new Rectangle(22, 123, 57, 25));
			jlp.setText("\u5BC6    \u7801");
			jluname = new JLabel();
			jluname.setBounds(new Rectangle(22, 90, 59, 28));
			jluname.setText("\u7528 \u6237 \u540D");
			jlpOldPasswd = new JLabel();
			jlpOldPasswd.setBounds(new Rectangle(22, 56, 76, 24));
			jlpOldPasswd.setText("\u65E7 \u5BC6 \u7801");
			jContentPane = new JPanel();
			jContentPane.setBackground(new Color(255,255,220));
			jContentPane.setLayout(null);
			jContentPane.add(jlpOldPasswd, null);
			jContentPane.add(getJtfOldPasswd(), null);
			jContentPane.add(jluname, null);
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
			
			JLabel label = new JLabel("\u7528\u6237\u4FE1\u606F\u4FEE\u6539");
			label.setFont(new Font("幼圆", Font.BOLD, 18));
			label.setBounds(131, 22, 122, 24);
			jContentPane.add(label);
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
			jtuname.setBounds(new Rectangle(98, 90, 231, 27));
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
			jp1.setBounds(new Rectangle(98, 118, 231, 29));
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
			jP2.setBounds(new Rectangle(98, 150, 231, 31));
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
			jtfage.setBounds(new Rectangle(98, 187, 231, 28));
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
			jtfpetname.setBounds(new Rectangle(98, 250, 225, 25));
		}
		return jtfpetname;
	}
	
	private JTextField getJtfOldPasswd() {
		if (jtfOldPasswd == null) {
			jtfOldPasswd = new JTextField();
			jtfOldPasswd.setBounds(new Rectangle(98, 56, 231, 30));
		}
		return jtfOldPasswd;
	}

	/**
	 * This method initializes jrdboy	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getJrdboy() {
		if (jrdboy == null) {
			jrdboy = new JRadioButton();
			jrdboy.setBounds(new Rectangle(101, 221, 78, 27));
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
			jrbgirl.setBounds(new Rectangle(202, 222, 72, 22));
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
			jttj.setBounds(new Rectangle(108, 285, 76, 33));
			jttj.setText("提交");
			jttj.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					String oldpasswd = jtfOldPasswd.getText();
					 if(jtuname.getText().equals("")||jp1.getText().equals("")
							 ||jP2.getText().equals("")||jtfage.getText().equals("")
							 ||jtfpetname.getText().equals("")){
						 errormessage("修改错误","选项必须全部填写！", 0);
					 }else if(!jrdboy.isSelected()&&!jrbgirl.isSelected()){
						 errormessage("修改错误","选项必须全部填写！", 0);
					 }else if(!jp1.getText().equals(jP2.getText())){
						 errormessage("修改错误","两次密码不一致！", 0);
					 }else if (!u.getPass().equals(oldpasswd)){
						 errormessage("修改错误","原密码输入错误!", 0);
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
			jbrew.setBounds(new Rectangle(234, 285, 75, 32));
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
