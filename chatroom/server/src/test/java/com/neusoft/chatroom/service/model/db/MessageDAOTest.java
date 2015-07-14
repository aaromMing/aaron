package com.neusoft.chatroom.service.model.db;

import static org.junit.Assert.fail;

import java.sql.Connection;

import org.junit.Before;
import org.junit.Test;

import com.neusoft.chatroom.service.model.db.entity.Message;

public class MessageDAOTest {
	
	private MessageDAO dao;

	@Before
	public void setUp() throws Exception {
		Connection con = DbUtils.getConnection();
		dao = new MessageDAO(con);
	}


	@Test
	public void testInsert() {
		Message msg = new Message();
		msg.setMessage("tell me");
		dao.insert(msg);
	}

	@Test
	public void testUpdate() {
		fail("Not yet implemented");
	}

	@Test
	public void testDelete() {
		fail("Not yet implemented");
	}

	@Test
	public void testSelectAll() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindMessage() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindId() {
		fail("Not yet implemented");
	}

}
