package com.qa.service;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import com.qa.domain.Account;
import com.qa.util.JSONUtil;

public class MapService {

	
	private Map <Integer, Account> accountMap;
	private int ID;
	
	
	public MapService() {
		
		this.accountMap = new HashMap<Integer, Account>();
		ID = 1;
		initAccountMap();
	}
		
	@Inject
	JSONUtil jUtil;

	public void initAccountMap() {
		
		Account account = new Account("Bill","Boe","1235");
		accountMap.put(ID,account);
		
	}
	
	
	public String getAllAccounts() {
		
		return jUtil.getJSONForObject(accountMap);
		
	}


	public String deleteAccount(int ID) {
		
		accountMap.remove(ID);
		return "{\"message\"; \"account successfully deleted\"}";
	}
	
	public String addAccount(String account) {
		ID++;
		Account newaccount = jUtil.getObjectForJSON(account,Account.class);
		accountMap.put(ID,newaccount);
		return "{\"message\"; \"account successfully added\"}";
	}


	public String updateAccount(int ID, String Update) {
		Account updateaccount = jUtil.getObjectForJSON(Update,Account.class);
		accountMap.put(ID,updateaccount);
		return "{\"message\"; \"account successfully updated\"}";
	}
	
	public void setUtil(JSONUtil jUtil) {
		this.jUtil = jUtil;
	}	
	
}
