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
	private DbUtils(){//不允许在类的外边创建对象
		
	}
	private Properties getProperties(){
		Properties p = new Properties();// 该对象用于操作配置文件
		try {
			p.load(this.getClass().getResourceAsStream("init.properties"));
		} catch (IOException e) {
			System.out.println("配置文件加载失败" + e.getMessage());
		}// 该方法会把配置文件中的内容加载到内存中。并且p对象用来管理这些内容
		// 要求配置文件与dbutils必须在同一目录中
		return p;
	}
	/**
	 * 该方法的作用 获取与数据库的链接
	 *  //TODO  我的注释待完善
	 * @return
	 */
	public static Connection getConnection() {
		Connection conn = null;
		// 创建用于操作配置文件的对象
		Properties p = d.getProperties();
		String sidordbname = p.getProperty("sidordbname");// 根据键去取对应的值
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
				// myDB为数据库名
				conn = DriverManager.getConnection(url);

			}
		} catch (Exception e) {
			e.printStackTrace();
//			System.out.println("数据库链接创建失败" + e.getMessage());
		}
		return conn;
	}
	public static void close(ResultSet rs , PreparedStatement stat ,Connection conn){
		if(rs != null){
			try {
				rs.close();
			} catch (SQLException e) {
				System.out.println("在关闭游标的时候出错了" + e.getMessage());
			}
		}
		if(stat != null){
			try {
				stat.close();
			} catch (SQLException e) {
				System.out.println("在关闭操作对象的时候出错了" + e.getMessage());
			}
		}
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				System.out.println("在关闭链接对象的时候出错了" + e.getMessage());
			}
		}
	}
	
}
