package com.neusoft.chatroom.service.model.db;

import static org.junit.Assert.fail;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

public class DbUtilsTest {

	private static final Logger LOG = LogManager.getLogger(DbUtilsTest.class);
	
	@Test
	public void testGetConnection() {
		LOG.info("test conn begin");
		DbUtils.getConnection();
	}

	@Test
	public void testClose() {
		fail("Not yet implemented");
	}

}
