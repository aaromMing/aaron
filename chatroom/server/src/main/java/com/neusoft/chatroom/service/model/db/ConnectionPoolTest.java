package com.neusoft.chatroom.service.model.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConnectionPoolTest {

	public static void main(String[] args) throws Exception {
		String sql = "select id,name,phone from guestbook";
		long start = System.currentTimeMillis();
		ConnectionPool pool = null;

		for (int i = 0; i < 100; i++) {
			pool = ConnectionPool.getInstance();
			Connection conn = pool.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
			}
			rs.close();
			stmt.close();
			pool.release(conn);
		}
		pool.closePool();
		System.out.println("经过100次的循环调用，使用连接池花费的时间:" + (System.currentTimeMillis() - start) + "ms\n");

		String hostName = "192.168.1.20";
		String driverClass = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@" + hostName + ":1521:ora9";
		String user = "scott";
		String password = "tiger";
		start = System.currentTimeMillis();
		
		for (int i = 0; i < 100; i++) {
			Class.forName(driverClass);
			Connection conn = DriverManager.getConnection(url, user, password);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
			}
			rs.close();
			stmt.close();
			conn.close();
		}
		System.out.println("经过100次的循环调用，不使用连接池花费的时间:" + (System.currentTimeMillis() - start) + "ms");
	}
}
