package com.neusoft.chatroom.client.vo;

import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ManageRui extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private JMenuBar jmenubar = null;

	private JMenu juser = null;

	private JMenuItem juserinsert = null;

	private JMenu jmexit = null;

	private JMenu jmfile = null;

	private JMenuItem juserupdate = null;

	private JMenuItem juserdelete = null;

	private JMenuItem jfreceive = null;

	private JMenuItem jfsend = null;

	private JLabel jl1 = null;

	private JComboBox jcfriend = null;

	private JScrollPane js1 = null;

	private JTextArea jtshowmessage = null;

	private JLabel jlm = null;

	private JComboBox jcword = null;

	private JCheckBox jCBbold = null;

	private JCheckBox jCBqx = null;

	private JScrollPane jS2 = null;

	private JTextArea jtsend = null;

	private JButton jbexit = null;

	private JButton jbsend = null;

	private JComboBox jcbcolor = null;

	/**
	 * This is the default constructor
	 */
	public ManageRui() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(440, 432);
		this.setJMenuBar(getJmenubar());
		this.setContentPane(getJContentPane());
		this.setTitle("即时聊天系统主界面");
		this.setVisible(true);
		this.setDefaultCloseOperation(3);
		this.setResizable(false);
		this.setLocation(350, 280);
		
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
			jContentPane.add(getJcword(), null);
			jContentPane.add(getJCBbold(), null);
			jContentPane.add(getJCBqx(), null);
			jContentPane.add(getJS2(), null);
			jContentPane.add(getJbexit(), null);
			jContentPane.add(getJbsend(), null);
			jContentPane.add(getJcbcolor(), null);
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
			jmenubar.add(getJmexit());
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
			juser.add(getJuserdelete());
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
		}
		return juserinsert;
	}

	/**
	 * This method initializes jmexit	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getJmexit() {
		if (jmexit == null) {
			jmexit = new JMenu();
			jmexit.setText("退出");
		}
		return jmexit;
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
		}
		return juserupdate;
	}

	/**
	 * This method initializes juserdelete	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getJuserdelete() {
		if (juserdelete == null) {
			juserdelete = new JMenuItem();
			juserdelete.setText("删除用户");
		}
		return juserdelete;
	}

	/**
	 * This method initializes jfreceive	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getJfreceive() {
		if (jfreceive == null) {
			jfreceive = new JMenuItem();
			jfreceive.setText("接收文件");
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
	 * This method initializes jcword	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getJcword() {
		if (jcword == null) {
			jcword = new JComboBox();
			jcword.setBounds(new Rectangle(83, 233, 122, 27));
		}
		return jcword;
	}

	/**
	 * This method initializes jCBbold	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	private JCheckBox getJCBbold() {
		if (jCBbold == null) {
			jCBbold = new JCheckBox();
			jCBbold.setBounds(new Rectangle(307, 233, 59, 26));
			jCBbold.setText("加粗");
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
			jCBqx.setBounds(new Rectangle(369, 233, 51, 28));
			jCBqx.setText("倾斜");
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
		}
		return jbsend;
	}

	/**
	 * This method initializes jcbcolor	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getJcbcolor() {
		if (jcbcolor == null) {
			jcbcolor = new JComboBox();
			jcbcolor.setBounds(new Rectangle(209, 234, 93, 27));
		}
		return jcbcolor;
	}

}  //  @jve:decl-index=0:visual-constraint="195,11"
