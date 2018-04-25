package com.qa.service;

import static org.junit.Assert.assertEquals;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.util.JSONUtil;

@RunWith(MockitoJUnitRunner.class)
public class MapServiceTest {
	
	private MapService mapService;
	
	private JSONUtil util;
	
	@Before
	public void setUp() {
			
			mapService.setManager(em);
			util = new JSONUtil();
			mapService.setUtil(util);
			
	} 
	
	@Test
	public void testDeleteAccount() {
		String actualValue = mapService.deleteAccount("1235");
		String expectedValue = "{\"message\"; \"account successfully deleted\"}";
		assertEquals(expectedValue, actualValue);
	}
	
	@Test
	public void testAddAccount() {
		String actualValue = mapService.addAccount(account1);
		String expectedValue = "{\"message\"; \"account successfully added\"}";
		assertEquals(expectedValue, actualValue);
	}
	
	@Test
	public void testUpdateAccount() {
		String actualValue = mapService.updateAccount("1234", mock);
		String expectedValue = "{\"message\"; \"account successfully updated\"}";
		assertEquals(expectedValue, actualValue);
	}
	
	@Test
	public void testGetAllAccounts() {
		
		String expected = "{\"firstName\":\"John\",\"secondName\":\"Doe\",\"accountNumber\":\"1234\"}";
		assertEquals(expected,mapService.getAllAccounts);
	}
	
}