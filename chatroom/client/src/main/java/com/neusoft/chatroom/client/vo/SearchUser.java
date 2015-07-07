package com.neusoft.chatroom.client.vo;

import java.awt.Rectangle;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.neusoft.chatroom.client.model.Userinfo;
public class SearchUser extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private JLabel namelist = null;

	private JLabel nam = null;

	private JLabel petname = null;

	private JLabel pass = null;

	private JLabel mail = null;

	private JLabel sex = null;

	private JLabel power = null;

	private JComboBox unl = null;

	private JTextField un = null;

	private JTextField up = null;

	private JTextField upa = null;

	private JTextField um = null;

	private JRadioButton b = null;

	private JLabel ub = null;

	private JRadioButton g = null;

	private JLabel ug = null;

	private JComboBox upower = null;

	private JButton back = null;

	Socket s = null;
	Userinfo u = null;
	Socket so =null;
	
	
	public SearchUser(Socket s ,Userinfo utemp,Socket so) {
		super();
		this.s = s;
		u = utemp;
		this.so = so;
		initialize();
	}
	
	private void initialize() {
		this.setSize(424, 424);
		this.setContentPane(getJContentPane());
		this.setTitle("即时通信后台管理－－用户查询");
		this.setVisible(true);
		this.setDefaultCloseOperation(3);
		this.setResizable(false);
		this.setLocation(350, 280);
		this.addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent e) {
				close();
				HoutaimainFrame l = new HoutaimainFrame(s,u,so);
			}
		});
		
	}
	
	public void close(){
		this.setVisible(false);
	}
	
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			ug = new JLabel();
			ug.setBounds(new Rectangle(326, 241, 49, 27));
			ug.setText("女");
			ub = new JLabel();
			ub.setBounds(new Rectangle(201, 243, 41, 28));
			ub.setText("男");
			power = new JLabel();
			power.setBounds(new Rectangle(46, 285, 72, 33));
			power.setText("权限");
			sex = new JLabel();
			sex.setBounds(new Rectangle(46, 237, 72, 33));
			sex.setText("性别");
			mail = new JLabel();
			mail.setBounds(new Rectangle(45, 193, 73, 34));
			mail.setText("邮箱");
			pass = new JLabel();
			pass.setBounds(new Rectangle(46, 146, 73, 32));
			pass.setText("密码");
			petname = new JLabel();
			petname.setBounds(new Rectangle(45, 102, 76, 34));
			petname.setText("昵称");
			nam = new JLabel();
			nam.setBounds(new Rectangle(45, 59, 75, 32));
			nam.setText("用户名");
			namelist = new JLabel();
			namelist.setBounds(new Rectangle(46, 16, 73, 31));
			namelist.setText("用户列表");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(namelist, null);
			jContentPane.add(nam, null);
			jContentPane.add(petname, null);
			jContentPane.add(pass, null);
			jContentPane.add(mail, null);
			jContentPane.add(sex, null);
			jContentPane.add(power, null);
			jContentPane.add(getUnl(), null);
			jContentPane.add(getUn(), null);
			jContentPane.add(getUp(), null);
			jContentPane.add(getUpa(), null);
			jContentPane.add(getUm(), null);
			jContentPane.add(getB(), null);
			jContentPane.add(ub, null);
			jContentPane.add(getG(), null);
			jContentPane.add(ug, null);
			jContentPane.add(getUpower(), null);
			jContentPane.add(getBack(), null);
		}
		return jContentPane;
	}


	private JComboBox getUnl() {
		if (unl == null) {
			unl = new JComboBox();
			unl.setBounds(new Rectangle(152, 16, 221, 30));
		}
		return unl;
	}
	/**
	 * This method initializes jtpetname	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getUn() {
		if (un == null) {
			un = new JTextField();
			un.setBounds(new Rectangle(151, 60, 224, 29));
		}
		return un;
	}

	/**
	 * This method initializes jp1	
	 * 	
	 * @return javax.swing.JPasswordField	
	 */
	private JTextField getUp() {
		if (up == null) {
			up = new JTextField();
			up.setBounds(new Rectangle(150, 104, 224, 31));
		}
		return up;
	}

	/**
	 * This method initializes jP2	
	 * 	
	 * @return javax.swing.JPasswordField	
	 */
	private JTextField getUpa() {
		if (upa == null) {
			upa = new JTextField();
			upa.setBounds(new Rectangle(149, 144, 222, 32));
		}
		return upa;
	}

	/**
	 * This method initializes jtfage	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getUm() {
		if (um == null) {
			um = new JTextField();
			um.setBounds(new Rectangle(152, 194, 218, 33));
		}
		return um;
	}
	
	private JRadioButton getB() {
		if (b == null) {
			b = new JRadioButton();
			b.setBounds(new Rectangle(156, 248, 16, 14));
		}
		return b;
	}


	/**
	 * This method initializes jrbgirl	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getG() {
		if (g == null) {
			g = new JRadioButton();
			g.setBounds(new Rectangle(281, 245, 21, 21));
		}
		return g;
	}
	private JComboBox getUpower() {
		if (upower == null) {
			upower = new JComboBox();
			upower.setBounds(new Rectangle(151, 286, 217, 33));			
					upower = new JComboBox();					
					upower.addItem("普通用户");
					upower.addItem("管理员");
				}
		return upower;
	}
	
	
	private JButton getBack() {
		if (back == null) {
			back = new JButton();
			back.setBounds(new Rectangle(167, 339, 85, 35));
			back.setText("返回");
			back.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					close();
					HoutaimainFrame l = new HoutaimainFrame(s,u,so);
				}
			}); 
		}
		return back;
	}
	
		
	}

