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

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.neusoft.chatroom.client.model.GetIP;
import com.neusoft.chatroom.client.model.StateCode;
import com.neusoft.chatroom.client.model.StringEdit;
import com.neusoft.chatroom.client.model.Userinfo;

public class InsertUser extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	
	private JLabel nam = null;

	private JLabel petname = null;

	private JLabel pass = null;

	private JLabel mail = null;

	private JLabel sex = null;

	private JLabel power = null;

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
	private JButton jButton = null;

	Socket s = null;
	Socket so = null;
	
	Userinfo u = null;
	private JLabel linsert = null;
	
	
	public InsertUser(Socket s ,Userinfo utemp) {
		super();
		this.s = s;
		this.u = utemp;
		initialize();
	}
	
	private void initialize() {
		this.setSize(424, 424);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/com/neusoft/chatroom/client/vo/Icon.jpg")));
		this.setContentPane(getJContentPane());
		this.setTitle("即时通信后台管理－－添加用户");
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
			linsert = new JLabel();
			linsert.setBounds(new Rectangle(53, 4, 336, 43));
			linsert.setText("      添   加    用    户");
			linsert.setFont(new Font("宋体",Font.BOLD,22));
			ug = new JLabel();
			ug.setBounds(new Rectangle(318, 241, 49, 27));
			ug.setText("女");
			ub = new JLabel();
			ub.setBounds(new Rectangle(201, 243, 41, 28));
			ub.setText("男");
			power = new JLabel();
			power.setBounds(new Rectangle(46, 285, 72, 33));
			power.setText("   权限");
			sex = new JLabel();
			sex.setBounds(new Rectangle(46, 237, 72, 33));
			sex.setText("    性别");
			mail = new JLabel();
			mail.setBounds(new Rectangle(45, 193, 73, 34));
			mail.setText("    邮箱");
			pass = new JLabel();
			pass.setBounds(new Rectangle(46, 146, 73, 32));
			pass.setText("    密码");
			petname = new JLabel();
			petname.setBounds(new Rectangle(45, 102, 76, 34));
			petname.setText("    昵称");
			nam = new JLabel();
			nam.setBounds(new Rectangle(45, 59, 75, 32));
			nam.setText("    用户名");			
			jContentPane = new JPanel();
			jContentPane.setBackground(new Color(255,255,220));
			jContentPane.setLayout(null);			
			jContentPane.add(nam, null);
			jContentPane.add(petname, null);
			jContentPane.add(pass, null);
			jContentPane.add(mail, null);
			jContentPane.add(sex, null);
			jContentPane.add(power, null);
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
			jContentPane.add(getJButton(), null);
			jContentPane.add(linsert, null);
		}
		return jContentPane;
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
			um.addFocusListener(new java.awt.event.FocusAdapter() {
				public void focusLost(java.awt.event.FocusEvent e) {
					boolean j = um.getText().contains("@");
					boolean i = um.getText().contains(".");
					int length = um.getText().length();
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

	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setBounds(new Rectangle(63, 339, 88, 39));
			jButton.setText("添加");
			jButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					u.setName(un.getText());
					u.setPass(upa.getText());
					u.setPetname(up.getText());
					u.setMail(um.getText());
					
					if(b.isSelected()){
						u.setSex("男");
					}else if(g.isSelected()){
						u.setSex("女");
					}
					if(upower.getSelectedItem().equals("普通用户")){
						u.setPower("普通用户");
					}else if(upower.getSelectedItem().equals("管理员")){
						u.setPower("管理员");
					}
					String ip = GetIP.getIp();
					String request = StringEdit.hechengUser(StateCode.REGISTER, u, ip);
					try {
						PrintStream p = new PrintStream(s.getOutputStream());
						p.println(request);
						BufferedReader b = new BufferedReader(new InputStreamReader(s.getInputStream()));
						String response = b.readLine();
						String head = StringEdit.getStateCode(response);
						if(head.equals(StateCode.SUCCESS)){							
							errormessage("添加成功","恭喜您添加成功",1);
							close();

						}else{
							
							errormessage("添加失败","请重新填写", 0);
						}
						
					} catch (IOException e1) {
						System.out.println("在发送添加信息的时候出错了");
					}
					
						
					
				}
			});
		
		}
		return jButton;
	}

   public void  errormessage(String title,String value,int i){
	JOptionPane.showMessageDialog(this, value,title,i);
	
   }
   
   private JButton getBack() {
		if (back == null) {
			back = new JButton();
			back.setBounds(new Rectangle(253, 339, 85, 38));
			back.setText("取消");
			back.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					close();					
				}
			}); 
		}
		return back;
	}
	
	private JComboBox getUpower() {
		if (upower == null) {
			upower = new JComboBox();
			upower.addItem("普通用户");
			upower.addItem("管理员");
			upower.setBounds(new Rectangle(151, 286, 217, 33));
			this.add(upower);
		}
		return upower;
	}
	}  //  @jve:decl-index=0:visual-constraint="175,9"

