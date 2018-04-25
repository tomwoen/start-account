package com.qa.service;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MapServiceTest {
	
	@InjectMocks
	private MapService mapService;
	
	@Mock
	private EntityManager em;
	
	@Before
	public void setUp() {
		
		mapService.setManager(em);
		
	}
	
	@Test
	public void testaddAccount() {
		
		String actual = mapService.addAccount();
		
	}

}
