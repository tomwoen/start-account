package com.qa.service;

import com.qa.domain.Account;
import com.qa.util.JSONUtil;
import java.util.Map;

public class BlockAccount {
	
	private DatabaseService databaseService;
	private JSONUtil jUtil;

	public String Blocked(String JSON) {
		
		Account account = jUtil.getObjectForJSON(JSON, Account.class);
		int value = account.values().stream()
		.filter(eachAccount -> eachAccount.getAccountNumber().equals("9999").count();
		if (value == 1) {
			
			String block = "{\"message\": \"This account is blocked\"}";
			
		}
			
		return block;
	}

}
