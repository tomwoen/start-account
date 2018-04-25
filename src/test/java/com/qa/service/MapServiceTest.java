package com.qa.service;

import static org.junit.Assert.assertEquals;

import javax.enterprise.inject.Alternative;


import org.junit.Before;
import org.junit.Test;

import com.qa.util.JSONUtil;

@Alternative
public class MapServiceTest {
	
	private MapService mapService;
	private String mock = "{\"firstName\":\"Bill\",\"secondName\":\"Boe\",\"accountNumber\":\"1235\"}";
	private String mockupdate = "{\"firstName\":\"Bill\",\"secondName\":\"Bow\",\"accountNumber\":\"1235\"}";
	private JSONUtil util;
	
	@Before
	public void setUp() {
		
		util = new JSONUtil();
		mapService = new MapService();
		mapService.setUtil(util);
		
	} 
	
	@Test
	public void testDeleteAccount() {
		String actualValue = mapService.deleteAccount(1);
		String expectedValue = "{\"message\"; \"account successfully deleted\"}";
		assertEquals(expectedValue, actualValue);
	}
	
	@Test
	public void testAddAccount() {
		String actualValue = mapService.addAccount(mock);
		String expectedValue = "{\"message\"; \"account successfully added\"}";
		assertEquals(expectedValue, actualValue);
	}
	
	@Test
	public void testUpdateAccount() {
		String actualValue = mapService.updateAccount(1, mockupdate);
		String expectedValue = "{\"message\"; \"account successfully updated\"}";
		assertEquals(expectedValue, actualValue);
	}
	
	@Test
	public void testGetAllAccounts() {
		
		mapService.initAccountMap();
		String expected = "{\"1\":{\"firstName\":\"Bill\",\"secondName\":\"Boe\",\"accountNumber\":\"1235\"}}";
		assertEquals(expected, mapService.getAllAccounts());
	}
	
}