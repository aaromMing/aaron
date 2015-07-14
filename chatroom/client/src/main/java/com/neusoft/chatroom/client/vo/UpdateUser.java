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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
import com.neusoft.chatroom.service.model.db.entity.Userinfo;

public class UpdateUser extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private JLabel jLabel = null;

	private JLabel jLabel1 = null;

	private JLabel jLabel11 = null;

	private JLabel jLabel13 = null;

	private JLabel jLabel14 = null;

	private JTextField yonghuming = null;

	private JTextField mail = null;

	private JTextField nicheng = null;

	private JPasswordField Password = null;

	private JLabel jLabel2 = null;

	private JRadioButton nan = null;

	private JRadioButton nv = null;

	private JButton xiugai = null;

	private JButton shanchu = null;

	private JComboBox putongyonghu = null;

	private JComboBox jComboBox = null;

	private JLabel jLabel3 = null;

	private JLabel quanxian = null;
	private Socket s = null;
	Userinfo u = null;
	ButtonGroup bg = new ButtonGroup();

	/**
	 * This is the default constructor
	 */
	public UpdateUser(Socket s , Userinfo utemp) {
		super();
		this.s = s;
		this.u = utemp;
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	//public userinfo  
	private void initialize() {
		this.setSize(390, 426);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/com/neusoft/chatroom/client/vo/Icon.jpg")));
		this.setContentPane(getJContentPane());
		this.setTitle("用户信息修改");
		this.setDefaultCloseOperation(3);
		this.setResizable(false);//设置窗体的大小是否可以修改
		bg.add(nan);
		bg.add(nv);
		ini();
		this.setLocation(600, 300);		

		this.setVisible(true);
	}
    private void ini(){
    	String ip =  GetIP.getIp();
    	String request = StringEdit.hechengUser(StateCode.SELECTALL, u, ip);
    	try {
			PrintStream p = new PrintStream(s.getOutputStream());
			p.println(request);
			BufferedReader b = new BufferedReader(new InputStreamReader(s.getInputStream()));
			String response = b.readLine();
			String head = StringEdit.getStateCode(response);
			if(head.equals(StateCode.SUCCESS)){
				String a[] = StringEdit.chaifenUsers(response);
				for(int i = 0;i < a.length;i++){
					int j = a[i].indexOf(".");
					jComboBox.addItem(a[i].substring(j+1));
				}
			}	
		} catch (IOException e1) {
			System.out.println("在发送的时候出错了");
		}
    }
	 private void close(){
		   this.dispose();
	  }
	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			quanxian = new JLabel();
			quanxian.setBounds(new Rectangle(40, 301, 80, 31));
			quanxian.setText("         权限：");
			jLabel3 = new JLabel();
			jLabel3.setBounds(new Rectangle(31, 50, 84, 30));
			jLabel3.setText("   用户列表:");
			jLabel2 = new JLabel();
			jLabel2.setBounds(new Rectangle(43, 252, 75, 32));
			jLabel2.setText("        性别：");
			jLabel14 = new JLabel();
			jLabel14.setBounds(new Rectangle(41, 212, 76, 32));
			jLabel14.setText("        昵称：");
			jLabel13 = new JLabel();
			jLabel13.setBounds(new Rectangle(40, 170, 75, 31));
			jLabel13.setText("        邮箱：");
			jLabel11 = new JLabel();
			jLabel11.setBounds(new Rectangle(41, 126, 73, 32));
			jLabel11.setText("       1密码：");
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(39, 87, 75, 30));
			jLabel1.setText("    用户名：");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(36, 5, 320, 38));
			jLabel.setText("       用   户   信   息");
			jLabel.setFont(new Font("楷体",Font.BOLD,20));
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.setBackground(new Color(255,255,220));
			jContentPane.add(jLabel, null);
			jContentPane.add(jLabel1, null);
			jContentPane.add(jLabel11, null);
			jContentPane.add(jLabel13, null);
			jContentPane.add(jLabel14, null);
			jContentPane.add(getYonghuming(), null);
			jContentPane.add(getMail(), null);
			jContentPane.add(getNicheng(), null);
			jContentPane.add(getPassword(), null);
			jContentPane.add(jLabel2, null);
			jContentPane.add(getNv(), null);
			jContentPane.add(getNan(), null);
			jContentPane.add(getXiugai(), null);
			jContentPane.add(getShanchu(), null);
			jContentPane.add(getPutongyonghu(), null);
			jContentPane.add(getJComboBox(), null);
			jContentPane.add(jLabel3, null);
			jContentPane.add(quanxian, null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes yonghuming	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getYonghuming() {
		if (yonghuming == null) {
			yonghuming = new JTextField();
			yonghuming.setBounds(new Rectangle(144, 89, 162, 27));
		}
		return yonghuming;
	}

	/**
	 * This method initializes mail	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getMail() {
		if (mail == null) {
			mail = new JTextField();
			mail.setBounds(new Rectangle(145, 172, 160, 27));
		}
		return mail;
	}

	/**
	 * This method initializes nicheng	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getNicheng() {
		if (nicheng == null) {
			nicheng = new JTextField();
			nicheng.setBounds(new Rectangle(146, 214, 160, 29));
		}
		return nicheng;
	}

	/**
	 * This method initializes Password	
	 * 	
	 * @return javax.swing.JPasswordField	
	 */
	private JPasswordField getPassword() {
		if (Password == null) {
			Password = new JPasswordField();
			Password.setBounds(new Rectangle(145, 128, 160, 29));
		}
		return Password;
	}

	/**
	 * This method initializes nan	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getNan() {
		if (nan == null) {
			nan = new JRadioButton();
			nan.setBounds(new Rectangle(145, 260, 61, 21));
			nan.setText("男");
		}
		return nan;
	}

	/**
	 * This method initializes nv	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getNv() {
		if (nv == null) {
			nv = new JRadioButton();
			nv.setBounds(new Rectangle(234, 259, 45, 21));
			nv.setText("女");
		}
		return nv;
	}
	 private static boolean getEmail(String line){
	        Pattern p = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
	        Matcher m = p.matcher(line);
	        return m.find();
	    }
	/**
	 * This method initializes xiugai	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getXiugai() {
		if (xiugai == null) {
			xiugai = new JButton();
			xiugai.setBounds(new Rectangle(89, 358, 68, 27));
			xiugai.setText("修改");
			xiugai.addActionListener(new java.awt.event.ActionListener() {   
				public void actionPerformed(java.awt.event.ActionEvent e) {    
				 if(yonghuming.getText().equals("")||Password.getText().equals("")
						 ||mail.getText().equals("")
						 ||nicheng.getText().equals("")){
					 errormessage("修改错误","选项必须全部填写！", 0);
				 }else if(!nan.isSelected()&&!nv.isSelected()){
					 errormessage("修改错误","选项必须全部填写！", 0);
				 }else if(!getEmail(mail.getText())) {
					 errormessage("修改错误","邮箱格式错误！", 0);
				 }else if(Password.getText().length() < 3) {
					 errormessage("密码过短","密码过短，您输入的密码必须大于等于3位！", 0);
				 } else{
					//u.setName(jTextField1.getText());
					u.setPass(Password.getText());
					u.setPetname(nicheng.getText());
					u.setMail(mail.getText());
					if(nan.isSelected()){
						u.setSex("男");
					}
					if(nv.isSelected()){
						u.setSex("女");
					}
					if(putongyonghu.getSelectedIndex() == 0){
						u.setPower("普通用户");
					}else
					{
						u.setPower("管理员");
					}
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
					}}
					
			});
		}
		return xiugai;
	}

	public void  errormessage(String title,String value,int i){
		JOptionPane.showMessageDialog(this, value,title,i);
		//弹出消息框 的四个参数： 第一个参数是：窗体对象（基于哪个窗体弹出）第二个参数：显示的内容 第三个参数：标题 第四个参数：图标(0~3)
	}
	/**
	 * This method initializes shanchu	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getShanchu() {
		if (shanchu == null) {
			shanchu = new JButton();
			shanchu.setBounds(new Rectangle(210, 360, 72, 26));
			shanchu.setText("删除");
			shanchu.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
				String ip = GetIP.getIp();
				int user_id = u.getId();
				String request = StringEdit.hechengUser(StateCode.DELETEUSER, u, ip);
				try {
					PrintStream p = new PrintStream(s.getOutputStream());
					p.println(request);
					BufferedReader b = new BufferedReader(new InputStreamReader(s.getInputStream()));
					String response = b.readLine();
					String head = StringEdit.getStateCode(response);
					if(head.equals(StateCode.SUCCESS)){
						//注册成功
						errormessage("删除成功","恭喜您删除成功", 1);
						close();
						
					}else{
						//注册信息有问题
						errormessage("删除失败","删除失败，请重新删除", 0);
					}
					
				} catch (IOException e1) {
					System.out.println("在发送的时候出错了");
				  }	
				String request1 = StringEdit.hechengUserID1(StateCode.DELETEONLINEUSER, user_id, ip);
				try {
					PrintStream p = new PrintStream(s.getOutputStream());
					p.println(request1);
					BufferedReader b = new BufferedReader(new InputStreamReader(s.getInputStream()));
					String response1 = b.readLine();
					String head = StringEdit.getStateCode(response1);
					if(head.equals(StateCode.SUCCESS)){
						//注册成功
						errormessage("删除成功","恭喜您删除成功", 1);
						close();
						
					}else{
						errormessage("删除失败","删除失败，请重新删除", 0);
					}
					
				} catch (IOException e1) {
					System.out.println("在发送的时候出错了");
				  }	
				}
			});
		}
		return shanchu;
	}

	/**
	 * This method initializes putongyonghu	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getPutongyonghu() {
		if (putongyonghu == null) {
			putongyonghu = new JComboBox();
			putongyonghu.setBounds(new Rectangle(146, 299, 162, 32));
			putongyonghu.addItem("普通用户");
			putongyonghu.addItem("管理员");
		}
		return putongyonghu;
	}
	
	
	private String out(String l){
		
	String ip =  GetIP.getIp();
	String request = StringEdit.hechengUser(StateCode.SELECTALL, u, ip);
	try {
		PrintStream p = new PrintStream(s.getOutputStream());
		p.println(request);
		BufferedReader b = new BufferedReader(new InputStreamReader(s.getInputStream()));
		String response = b.readLine();
		String head = StringEdit.getStateCode(response);
		if(head.equals(StateCode.SUCCESS)){
			 String a[] = StringEdit.chaifenUsers(response);
				for(int i = 0;i <a.length;i++){
					int j = a[i].indexOf(".");
					if(l.equals(a[i].substring(j+1))){
						String c = a[i].substring(0, j);
						return c;
					}
				}
			}
	} catch (IOException e1) {
		System.out.println("在发送的时候出错了");
	}
	return null;
	}
	

	/**
	 * This method initializes jComboBox	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getJComboBox() {
		
		if (jComboBox == null) {
			jComboBox = new JComboBox();
			jComboBox.setBounds(new Rectangle(144, 49, 231, 31));
			jComboBox.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					String a = (String)jComboBox.getSelectedItem();
					String id = out(a);
					String ip =  GetIP.getIp();
					String request = StringEdit.hechengUserID(StateCode.SELECTUSER, id, ip);
					try {
						PrintStream p = new PrintStream(s.getOutputStream());
						p.println(request);
						BufferedReader b = new BufferedReader(new InputStreamReader(s.getInputStream()));
						String response = b.readLine();
						String head = StringEdit.getStateCode(response);
						
						if(head.equals(StateCode.SUCCESS)){
							 u = StringEdit.chaifenUser(response);
							}
					} catch (IOException e1) {
						System.out.println("在发送的时候出错了");
					}
					yonghuming.setText(u.getName());
					mail.setText(u.getMail());
					nicheng.setText(u.getPetname());
					if(u.getSex().equals("男")){
						nan.setSelected(true);
					}else{
						nv.setSelected(true);
					}
					yonghuming.setEnabled(false);
					putongyonghu.setSelectedItem(u.getPower());
				
				}
			});
		}
		return jComboBox;
	}

}  //  @jve:decl-index=0:visual-constraint="313,4"