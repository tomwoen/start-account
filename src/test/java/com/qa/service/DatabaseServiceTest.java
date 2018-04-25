package com.qa.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.domain.Account;
import com.qa.util.JSONUtil;

import junit.framework.Assert;


@RunWith(MockitoJUnitRunner.class)
public class DatabaseServiceTest {
	
	@InjectMocks
	private DatabaseService databaseService;
	
	@Mock
	private EntityManager em;
	
	@Mock
	private Query query;
	
	private JSONUtil util;
	private Account account1 = new Account("Phil","Mitchell","1237");
	private String mock = "{\"firstName\":\"John\",\"secondName\":\"Doe\",\"accountNumber\":\"1234\"}";
	private String mockArray = "[{\"firstName\":\"John\",\"secondName\":\"Doe\",\"accountNumber\":\"1234\"}]";
	
	@Before
	
	 public void setUp() {
		
		databaseService.setManager(em);
		util = new JSONUtil();
		databaseService.setUtil(util);
		
	} 
	
	@Test
	public void testGetAccount() {
		String actualValue = databaseService.getAccount(account1);
		String expectedValue = "{\"message\"; \"account successfully added\"}";
		assertEquals(expectedValue, actualValue);
	}
	
	@Test
	public void testDeleteAccount() {
		String actualValue = databaseService.deleteAccount("1235");
		String expectedValue = "{\"message\"; \"account successfully deleted\"}";
		assertEquals(expectedValue, actualValue);
	}

	@Test
	public void testUpdateAccount() {
		String actualValue = databaseService.updateAccount("1234", mock);
		String expectedValue = "{\"message\"; \"account successfully updated\"}";
		assertEquals(expectedValue, actualValue);
	}
	
	@Test
	public void testGetAllAccounts() {
		
		Mockito.when(em.createQuery(Mockito.anyString())).thenReturn(query);
		List<Account> accounts = new ArrayList<Account>();
		accounts.add(new Account("John", "Doe", "1234"));
		Mockito.when(query.getResultList()).thenReturn(accounts);
		assertEquals(mockArray, databaseService.getAllAccounts());
	}
		
		
}
