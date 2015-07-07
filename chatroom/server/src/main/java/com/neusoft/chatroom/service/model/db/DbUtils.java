package com.neusoft.chatroom.service.model.db;



import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class DbUtils {
	private static DbUtils d = new DbUtils();
	private DbUtils(){//�������������ߴ�������
		
	}
	private Properties getProperties(){
		Properties p = new Properties();// �ö������ڲ��������ļ�
		try {
			p.load(this.getClass().getResourceAsStream("init.properties"));
		} catch (IOException e) {
			System.out.println("�����ļ�����ʧ��" + e.getMessage());
		}// �÷�����������ļ��е����ݼ��ص��ڴ��С�����p��������������Щ����
		// Ҫ�������ļ���dbutils������ͬһĿ¼��
		return p;
	}
	/**
	 * �÷��������� ��ȡ�����ݿ������
	 *  //TODO  �ҵ�ע�ʹ�����
	 * @return
	 */
	public static Connection getConnection() {
		Connection conn = null;
		// �������ڲ��������ļ��Ķ���
		Properties p = d.getProperties();
		String sidordbname = p.getProperty("sidordbname");// ���ݼ�ȥȡ��Ӧ��ֵ
		String username = p.getProperty("username");
		String password = p.getProperty("password");
		String port = p.getProperty("port");
		String ip = p.getProperty("ip");
		String database = p.getProperty("database");
		database = database.toLowerCase();
		database = database.trim();
		try {
			if (database.equals("oracle")) {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				String url = "jdbc:oracle:thin:@" + ip + ":" + port + ":"
						+ sidordbname;
				conn = DriverManager.getConnection(url, username, password);
			}
			if (database.equals("sqlserver")) {
				Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver");
				String url = "jdbc:microsoft:sqlserver://" + ip + ":" + port
						+ ";DatabaseName=" + sidordbname;
				conn = DriverManager.getConnection(url, username, password);

			}
			if (database.equals("mysql")) {
				Class.forName("org.gjt.mm.mysql.Driver");
				String url = "jdbc:mysql://" + ip + ":" + port + "/"
						+ sidordbname + "?user=" + username + "&password="
						+ password + "&useUnicode=true&characterEncoding=utf8";
				// myDBΪ���ݿ���
				conn = DriverManager.getConnection(url);

			}
		} catch (Exception e) {
			e.printStackTrace();
//			System.out.println("���ݿ����Ӵ���ʧ��" + e.getMessage());
		}
		return conn;
	}
	public static void close(ResultSet rs , PreparedStatement stat ,Connection conn){
		if(rs != null){
			try {
				rs.close();
			} catch (SQLException e) {
				System.out.println("�ڹر��α��ʱ�������" + e.getMessage());
			}
		}
		if(stat != null){
			try {
				stat.close();
			} catch (SQLException e) {
				System.out.println("�ڹرղ��������ʱ�������" + e.getMessage());
			}
		}
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				System.out.println("�ڹر����Ӷ����ʱ�������" + e.getMessage());
			}
		}
	}
	
}
