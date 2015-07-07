package com.neusoft.chatroom.service.model.db;

import static org.junit.Assert.fail;

import org.junit.Test;

public class DbUtilsTest {

	@Test
	public void testGetConnection() {
		DbUtils.getConnection();
	}

	@Test
	public void testClose() {
		fail("Not yet implemented");
	}

}
