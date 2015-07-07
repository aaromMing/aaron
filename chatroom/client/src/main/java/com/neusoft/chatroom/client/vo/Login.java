package com.neusoft.chatroom.client.vo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.neusoft.chatroom.client.model.GetIP;
import com.neusoft.chatroom.client.model.StateCode;
import com.neusoft.chatroom.client.model.StringEdit;
import com.neusoft.chatroom.client.model.Userinfo;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;  //  @jve:decl-index=0:visual-constraint="216,13"

	private JLabel ltu = null;

	private JTextField tu = null;

	private JLabel ltp = null;

	private JPasswordField tp = null;

	private JButton bl = null;

	private JButton b3 = null;
	private Socket s = null;
	private Socket so = null;
	Userinfo u = new Userinfo();

	private JLabel jlabel = null;
	/**
	 * This is the default constructor
	 */
	public Login(Socket s,Socket so) {
		super();
		this.s = s;
		this.so = so;
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(400, 280);	
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/com/neusoft/chatroom/client/vo/Icon.jpg")));
		this.setContentPane(getJContentPane());
		this.setTitle("QQ2012-Bata��");
		this.setDefaultCloseOperation(3);
		this.setLocation(350, 280);
		this.setResizable(false);
		this.setVisible(true);
		this.addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent e) {
				try {
					PrintStream p = new PrintStream(s.getOutputStream());
					p.println(StateCode.EXIT+"|");
				} catch (IOException e1) {
					System.out.println("���͹ر���Ϣ��ʱ�������");
				}
				System.exit(0);
			}
		});
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jlabel = new JLabel("/com/neusoft/chatroom/client/vo/Icon1.gif");
			jlabel.setBounds(new Rectangle(47, 14, 328, 55));
			jlabel.setText("      ��ӭʹ�ü�ʱ����ϵͳ");
			jlabel.setFont(new Font("����",Font.BOLD,20));
			ltp = new JLabel();
			ltp.setBounds(new Rectangle(17, 130, 78, 26));
			ltp.setText("  ���룺");
			ltp.setFont(new Font("����",Font.BOLD,15));
			ltu = new JLabel();
			ltu.setBounds(new Rectangle(18, 89, 78, 26));
			ltu.setText("  �û���");
			ltu.setFont(new Font("����",Font.BOLD,15));
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.setBackground(new Color(255,255,220));
			
			jContentPane.setSize(new Dimension(409, 254));
			jContentPane.add(ltu, null);
			jContentPane.add(getTu(), null);
			jContentPane.add(ltp, null);
			jContentPane.add(getTp(), null);
			jContentPane.add(getBl(), null);
			jContentPane.add(getB3(), null);
			jContentPane.add(jlabel, null);
			
		}
		return jContentPane;
	}
	

	/**
	 * This method initializes tu	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTu() {
		if (tu == null) {
			tu = new JTextField();
			tu.setBounds(new Rectangle(107, 82, 230, 32));
		}
		return tu;
	}

	/**
	 * This method initializes tp	
	 * 	
	 * @return javax.swing.JPasswordField	
	 */
	private JPasswordField getTp() {
		if (tp == null) {
			tp = new JPasswordField();
			tp.setBounds(new Rectangle(108, 128, 226, 32));
			tp.addKeyListener(new java.awt.event.KeyAdapter() {
				public void keyTyped(java.awt.event.KeyEvent e) {
					if(e.getKeyChar() == '\n'){
						//����¼����
//						��ȡ�û���������
						u.setName(tu.getText());
						u.setPass(tp.getText());
						String ip = GetIP.getIp();
						
						String request = StringEdit.hechengUser(StateCode.LOGIN, u, ip);
						//����������ַ�������������
						boolean blogin = false;
						try {
							PrintStream p = new PrintStream(s.getOutputStream());
							p.println(request);
							//��ȡ����������Ӧ�ַ���
							BufferedReader b = new BufferedReader(new InputStreamReader(s.getInputStream()));
							String response = b.readLine();
							String head = StringEdit.getStateCode(response);
							if(head.equals(StateCode.SUCCESS)){
								blogin = true;
								u = StringEdit.chaifenUser(response);
							}
						} catch (IOException e1) {
							System.out.println("������Ϣ");
						}
						
						
						//������֤
						if(blogin){
						//�ɹ�
							//�رյ�ǰ�ĵ�½ҳ��
							close();
							//����ҳ��
							errormessage("��¼�ɹ�", u.getName()+"���ã� ����Ȩ����:" + u.getPower(),1);
							
							if(u.getPower().equals("����Ա")){
								HoutaimainFrame m = new HoutaimainFrame(s,u,so);
							}else{
								QiantaiFrame m = new QiantaiFrame(s,u,so);
							}
							
						}else{
						//���ɹ�
							//������Ϣ����ʾ�û����û������������
							errormessage("��½����", "�û��������������������д",0);
						}
					}
				}
			});
		}
		return tp;
	}
	public void close(){
		this.setVisible(false);
	}
	public void  errormessage(String title,String value,int i){
		JOptionPane.showMessageDialog(this, value,title,i);
		//������Ϣ�� ���ĸ������� ��һ�������ǣ�������󣨻����ĸ����嵯�����ڶ�����������ʾ������ ���������������� ���ĸ�������ͼ��(0~3)
	}
	/**
	 * This method initializes bl	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBl() {
		if (bl == null) {
			bl = new JButton();
			bl.setBounds(new Rectangle(73, 180, 69, 30));
			bl.setText("��¼");
			bl.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
//					����¼����
//					��ȡ�û���������
					u.setName(tu.getText());
					u.setPass(tp.getText());
					String ip = GetIP.getIp();
					String request = StringEdit.hechengUser(StateCode.LOGIN, u, ip);
					//����������ַ�������������
					boolean blogin = false;
					try {
						PrintStream p = new PrintStream(s.getOutputStream());
						p.println(request);
						//��ȡ����������Ӧ�ַ���
						BufferedReader b = new BufferedReader(new InputStreamReader(s.getInputStream()));
						String response = b.readLine();
						String head = StringEdit.getStateCode(response);
						if(head.equals(StateCode.SUCCESS)){
							blogin = true;
							u = StringEdit.chaifenUser(response);
						}
					} catch (IOException e1) {
						System.out.println("������Ϣ");
					}
					
					
					//������֤
					if(blogin){
					//�ɹ�
						//�رյ�ǰ�ĵ�½ҳ��
						close();
						//����ҳ��
						errormessage("��¼�ɹ�", u.getName()+"���ã� ����Ȩ����:" + u.getPower(),1);
						if(u.getPower().equals("����Ա")){
							HoutaimainFrame m = new HoutaimainFrame(s,u,so);
						}else{
							QiantaiFrame m = new QiantaiFrame(s,u,so);
						}
						
					}else{
					//���ɹ�
						//������Ϣ����ʾ�û����û������������
						errormessage("��½����", "�û��������������������д",0);
					}
				}
			});
		}
		return bl;
	}

	/**
	 * This method initializes b3	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getB3() {
		if (b3 == null) {
			b3 = new JButton();
			b3.setBounds(new Rectangle(191, 180, 69, 30));
			b3.setText("ע��");
			b3.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					close();
					Register z = new Register(s,so);
				}
			});
		}
		return b3;
	}

}  //  @jve:decl-index=0:visual-constraint="259,45"
