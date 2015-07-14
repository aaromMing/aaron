package com.neusoft.chatroom.client.vo;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Toolkit;
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
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.neusoft.chatroom.client.model.GetIP;
import com.neusoft.chatroom.client.model.StateCode;
import com.neusoft.chatroom.client.model.StringEdit;
import com.neusoft.chatroom.client.tool.GetFileThread;
import com.neusoft.chatroom.client.tool.MessageThread;
import com.neusoft.chatroom.service.model.db.entity.Message;
import com.neusoft.chatroom.service.model.db.entity.Userinfo;

public class HoutaimainFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private JMenuBar jmenubar = null;

	private JMenu juser = null;

	private JMenuItem juserinsert = null;

	private JMenu jmfile = null;

	private JMenuItem juserupdate = null;

	

	private JMenuItem jfreceive = null;

	private JMenuItem jfsend = null;

	private JLabel jl1 = null;

	private JComboBox jcfriend = null;

	private JScrollPane js1 = null;

	private JTextArea jtshowmessage = null;

	private JLabel jlm = null;

	private JCheckBox jCBbold = null;

	private JCheckBox jCBqx = null;

	private JScrollPane jS2 = null;

	private JTextArea jtsend = null;

	private JButton jbexit = null;

	private JButton jbsend = null;
	Color colorStyle;
	private List st;

	Socket s = null;
	private Socket so = null;
	Userinfo u = null;

	private JButton jbcolor = null;

	private JComboBox jfont = null;

	/**
	 * This is the default constructor
	 */
	public HoutaimainFrame(Socket s,Userinfo u2,Socket so) {
		super();
		initialize();
		this.s = s;
		this.so = so;
		aboutmessage();
		u = u2;
	}
	private void aboutmessage(){
		Thread t = new MessageThread(s,so,this.jtshowmessage);
		t.start();
		}
	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(440, 432);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/com/neusoft/chatroom/client/vo/Icon.jpg")));
		this.setJMenuBar(getJmenubar());
		this.setContentPane(getJContentPane());
		this.setTitle("即时聊天系统主界面");
		this.setVisible(true);
		this.setDefaultCloseOperation(3);
		this.setResizable(false);
		this.setLocation(350, 280);
		this.addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowOpened(java.awt.event.WindowEvent e) {
				Userinfo u1 = new  Userinfo();
				u1.setId(u.getId());
				String ip = GetIP.getIp();
				String request = StringEdit.hechengUser(StateCode.SELECTALL,u1,ip);
				try {
					PrintStream p = new PrintStream(s.getOutputStream());
					p.println(request);
					BufferedReader b = new BufferedReader(new InputStreamReader(s.getInputStream()));
					String response = b.readLine();
					
					String head = StringEdit.getStateCode(response);
					String id_nickname[] = StringEdit.chaifenUsers(response);
					for(int i = 0; i < id_nickname.length; i++){
						jcfriend.addItem(id_nickname[i]);
					}
					
				} catch (IOException e1) {
					System.out.println("在发送的时候出错了");
				}		
			}
		});
		this.addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent e) {
				try {
					PrintStream p = new PrintStream(s.getOutputStream());
					p.println(StateCode.EXIT+"|");
				} catch (IOException e1) {
					System.out.println("发送关闭信息的时候出错了");
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
			jlm = new JLabel();
			jlm.setBounds(new Rectangle(6, 232, 70, 27));
			jlm.setText("       消   息");
			jl1 = new JLabel();
			jl1.setBounds(new Rectangle(7, 4, 80, 27));
			jl1.setText("发 送 给：");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(jl1, null);
			jContentPane.add(getJcfriend(), null);
			jContentPane.add(getJs1(), null);
			jContentPane.add(jlm, null);
			jContentPane.add(getJCBbold(), null);
			jContentPane.add(getJCBqx(), null);
			jContentPane.add(getJS2(), null);
			jContentPane.add(getJbexit(), null);
			jContentPane.add(getJbsend(), null);
			jContentPane.add(getJbcolor(), null);
			jContentPane.add(getJfont(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jmenubar	
	 * 	
	 * @return javax.swing.JMenuBar	
	 */
	private JMenuBar getJmenubar() {
		if (jmenubar == null) {
			jmenubar = new JMenuBar();
			jmenubar.add(getJuser());
			jmenubar.add(getJmfile());
		}
		return jmenubar;
	}

	/**
	 * This method initializes juser	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getJuser() {
		if (juser == null) {
			juser = new JMenu();
			juser.setText("后台管理");
			juser.add(getJuserinsert());
			juser.add(getJuserupdate());
			
		}
		return juser;
	}

	/**
	 * This method initializes juserinsert	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getJuserinsert() {
		if (juserinsert == null) {
			juserinsert = new JMenuItem();
			juserinsert.setText("添加用户");
			juserinsert.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					InsertUser iu = new InsertUser(s,u);
				}
			});
		}
		return juserinsert;
	}

	public void close(){
		this.setVisible(false);
	}

	/**
	 * This method initializes jmfile	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getJmfile() {
		if (jmfile == null) {
			jmfile = new JMenu();
			jmfile.setText("文件管理");
			jmfile.add(getJfreceive());
			jmfile.add(getJfsend());
		}
		return jmfile;
	}

	/**
	 * This method initializes juserupdate	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getJuserupdate() {
		if (juserupdate == null) {
			juserupdate = new JMenuItem();
			juserupdate.setText("修改用户");
			juserupdate.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					UpdateUser ci = new UpdateUser(s,u);
				}
			});
		}
		return juserupdate;
	}

	/**
	 * This method initializes juserdelete	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	

	/**
	 * This method initializes jfreceive	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getJfreceive() {
		if (jfreceive == null) {
			jfreceive = new JMenuItem();
			jfreceive.setText("接收文件");
			jfreceive.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {					
				   String send_ip =MessageThread.ip();
				   String filename = MessageThread.filename();
				
				   GetFileThread gt = new GetFileThread(send_ip,filename,jtshowmessage);
				   gt.start();
				}
			});
		}
		return jfreceive;
	}

	/**
	 * This method initializes jfsend	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getJfsend() {
		if (jfsend == null) {
			jfsend = new JMenuItem();
			jfsend.setText("发送文件");
			jfsend.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					FileTransfer f= new FileTransfer(s,u);
				}
			});
		}
		return jfsend;
	}

	/**
	 * This method initializes jcfriend	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getJcfriend() {
		if (jcfriend == null) {
			jcfriend = new JComboBox();
			jcfriend.setBounds(new Rectangle(91, 1, 318, 31));
			jcfriend.addItem("全部用户");
			jcfriend.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {					
					jcfriend.removeAllItems();
					jcfriend.addItem("全部用户");
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
							for(int i=0; i<a.length-1;i++){
								int j = a[i].indexOf(".");
								
								st.add(a[i]);
								jcfriend.addItem(a[i]);
							}
						}else{
							
						}	
					} catch (IOException e1) {
						System.out.println("在发送的时候出错了");
					  }	
				}
			});
			
		}
		return jcfriend;
	}

	/**
	 * This method initializes js1	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJs1() {
		if (js1 == null) {
			js1 = new JScrollPane();
			js1.setBounds(new Rectangle(5, 33, 419, 195));
			js1.setViewportView(getJtshowmessage());
		}
		return js1;
	}

	/**
	 * This method initializes jtshowmessage	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getJtshowmessage() {
		if (jtshowmessage == null) {
			jtshowmessage = new JTextArea();
		}
		return jtshowmessage;
	}

	/**
	 * This method initializes jCBbold	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	private void setNewFont(){
		int fontStyle = Font.PLAIN;
		fontStyle +=(jCBbold.isSelected()?Font.BOLD:Font.PLAIN);
		fontStyle +=(jCBqx.isSelected()?Font.ITALIC:Font.PLAIN);
		String str = (String) jfont.getSelectedItem();
		
		jtshowmessage.setFont(new Font(str,fontStyle,22));
	}
	private JCheckBox getJCBbold() {
		if (jCBbold == null) {
			jCBbold = new JCheckBox();
			jCBbold.setBounds(new Rectangle(294, 232, 59, 26));
			jCBbold.setText("加粗");
			jCBbold.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					setNewFont();
				}
			});
		}
		return jCBbold;
	}

	/**
	 * This method initializes jCBqx	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	private JCheckBox getJCBqx() {
		if (jCBqx == null) {
			jCBqx = new JCheckBox();
			jCBqx.setBounds(new Rectangle(356, 230, 51, 28));
			jCBqx.setText("倾斜");
			jCBqx.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					setNewFont();
				}
			});
		}
		return jCBqx;
	}

	/**
	 * This method initializes jS2	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJS2() {
		if (jS2 == null) {
			jS2 = new JScrollPane();
			jS2.setBounds(new Rectangle(5, 261, 418, 68));
			jS2.setViewportView(getJtsend());
		}
		return jS2;
	}

	/**
	 * This method initializes jtsend	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getJtsend() {
		if (jtsend == null) {
			jtsend = new JTextArea();
		}
		return jtsend;
	}

	/**
	 * This method initializes jbexit	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJbexit() {
		if (jbexit == null) {
			jbexit = new JButton();
			jbexit.setBounds(new Rectangle(230, 331, 82, 37));
			jbexit.setText("  关  闭");
			jbexit.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
						PrintStream p = new PrintStream(s.getOutputStream());
						p.println(StateCode.EXIT+"|");
					} catch (IOException e1) {
						System.out.println("发送关闭信息的时候出错了");
					}
					System.exit(0);
					
				}
			});
		}
		return jbexit;
	}

	/**
	 * This method initializes jbsend	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJbsend() {
		if (jbsend == null) {
			jbsend = new JButton();
			jbsend.setBounds(new Rectangle(337, 330, 84, 37));
			jbsend.setText("   发  送");			
			jbsend.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(jcfriend.getSelectedItem().toString().equals("全部用户")){
						for(int i = 1; i<(jcfriend.getItemCount());i++){					
							String ip = GetIP.getIp();
							Message us = new Message();					
							String name = (String)jcfriend.getItemAt(i);
							int j = name.indexOf(".");
							us.setGet_id(Integer.parseInt(name.substring(0,j)));							
							us.setSend_id(u.getId());
							us.setMessage(jtsend.getText());
							Date time  = new Date();
							Timestamp tm = new Timestamp(time.getTime());	
							us.setTime(tm);					
							
							String request = StringEdit.hechengmessage(us);
							try {
								PrintStream p = new PrintStream(s.getOutputStream());
								p.println(request);
								BufferedReader b = new BufferedReader(new InputStreamReader(s.getInputStream()));
								String response = b.readLine();
								String head = StringEdit.getStateCode(response);
								if(head.equals(StateCode.SUCCESS)){
									
								}else{
									errormessage("发送错误","发送失败！", 0);
								}
								
							} catch (IOException e1) {
								System.out.println("在发送的时候出错了");
							}
						}
						SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//定义格式，不显示毫秒
						Timestamp now = new Timestamp(System.currentTimeMillis());//获取系统当前时间
						String str = df.format(now);						
						String temp = str +"\n"+u.getPetname()+"说："+jtsend.getText()+"\n";
						jtshowmessage.append(temp+"\n");
						
						jtsend.setText("");
					}else{
						String ip = GetIP.getIp();
						Message us= new Message();
						us.setSend_id(u.getId());
						String name = (String)jcfriend.getSelectedItem();
						int i = name.indexOf(".");
						us.setGet_id(Integer.parseInt(name.substring(0,i)));
						us.setMessage(jtsend.getText());
						Date time  = new Date();
						Timestamp tm = new Timestamp(time.getTime());	
						us.setTime(tm);
						
						SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//定义格式，不显示毫秒
						Timestamp now = new Timestamp(System.currentTimeMillis());//获取系统当前时间
						String str = df.format(now);						
						String temp = str +"\n"+u.getPetname()+"说："+jtsend.getText()+"\n";
						jtshowmessage.append(temp+"\n");
						String request = StringEdit.hechengmessage(us);
						try {
							PrintStream p = new PrintStream(s.getOutputStream());
							p.println(request);
							BufferedReader b = new BufferedReader(new InputStreamReader(s.getInputStream()));
							String response = b.readLine();
							String head = StringEdit.getStateCode(response);
							if(head.equals(StateCode.SUCCESS)){
								
							}else{
								errormessage("发送错误","发送失败！", 0);
							}
							
						} catch (IOException e1) {
							System.out.println("在发送的时候出错了");
						}
					}	
					jtsend.setText("");
				}
			});
		}
		return jbsend;
	}

	
	

	/**
	 * This method initializes jbcolor	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJbcolor() {
		if (jbcolor == null) {
			jbcolor = new JButton();
			jbcolor.setBounds(new Rectangle(201, 233, 71, 25));
			jbcolor.setText("颜色");
			jbcolor.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(e.getSource()==jbcolor){//改变颜色
						  colorStyle = JColorChooser.showDialog(HoutaimainFrame.this,"选择字体颜色",colorStyle);
						jbcolor.setForeground(colorStyle);
						jtshowmessage.setForeground(colorStyle);
						}
				}
			});
		}
		return jbcolor;
	}

	/**
	 * This method initializes jfont	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	
	public void  errormessage(String title,String value,int i){
		JOptionPane.showMessageDialog(this, value,title,i);
		//弹出消息框 的四个参数： 第一个参数是：窗体对象（基于哪个窗体弹出）第二个参数：显示的内容 第三个参数：标题 第四个参数：图标(0~3)
	}
	private JComboBox getJfont() {
		String str;
		if (jfont == null) {
			GraphicsEnvironment gg=GraphicsEnvironment.getLocalGraphicsEnvironment(); 
			String ss[]=gg.getAvailableFontFamilyNames(); 
			jfont = new JComboBox(ss);
			jfont.setEditable(false);			
			jfont.setMaximumRowCount(10);            
			jfont.setBounds(new Rectangle(93, 233, 84, 28));
			jfont.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					setNewFont();
				}
			});
		}
		return jfont;
	}
}  //  @jve:decl-index=0:visual-constraint="10,0"
