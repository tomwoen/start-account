package com.qa.service;

import static org.junit.Assert.*;

import org.junit.Test;

public class BlockAccountTest {
	
	private BlockAccount blockAccount;

	@Test
	public void testBlocked() {
		String actual = blockAccount.Blocked("9999");
		String expected = "{\"message\": \"This account is blocked\"}";

	}

}
