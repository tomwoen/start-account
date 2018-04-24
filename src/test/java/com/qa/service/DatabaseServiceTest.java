package com.qa.service;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.domain.Account;


@RunWith(MockitoJUnitRunner.class)
public class DatabaseServiceTest {
	
	@InjectMocks
	private DatabaseService databaseService;
	
	@Mock
	private EntityManager em;
	
	private Account account1 = new Account("Phil","Mitchell","1237");
	
	@Before
	
	 public void setUp() {
		
		databaseService.setManager(em);
		
	} 
	
	@Test
	public void testGetAccount() {
		String actualValue = databaseService.getAccount(account1);
		String expectedValue = "{\"message\"; \"account successfully added\"}";
		assertEquals(expectedValue, actualValue);
	}
	
	@Test
	public void testDeleteAccount() {
		String actualValue = databaseService.deleteAccount(account1);
		String expectedValue = "{\"message\"; \"account successfully deleted\"}";
		assertEquals(expectedValue, actualValue);
	}

	@Test
	public void testUpdateAccount() {
		String actualValue = databaseService.updateAccount("1237,");
		String expectedValue = "{\"message\"; \"account successfully updated\"}";
		assertEquals(expectedValue, actualValue);
	}
	
	@Test
	public void testGetAllAccounts() {
		
		Mockito.when(em.createQuery(account1))
		String actualValue = databaseService.getAllAccounts();
		String ExpectedValue = "{\"0\":{\"firstName\":\"Joe\",\"secondName\":\"Bloggs\",\"accountNumber\":\"1234\"},\"1\":{\"firstName\":\"Jane\",\"secondName\":\"Bloggs\",\"accountNumber\":\"1235\"}:{\"firstName\":\"Jim\",\"secondName\":\"Taylor\",\"accountNumber\":\"1236\"}}";
		assertEquals(ExpectedValue, actualValue);
	} 
}
