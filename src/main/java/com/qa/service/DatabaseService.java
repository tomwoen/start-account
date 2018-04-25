package com.qa.service;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.*;
import javax.transaction.Transactional;
import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

import java.util.Collection;
import java.util.List;

import com.qa.domain.Account;
import com.qa.util.JSONUtil;

@Default
@Transactional(SUPPORTS)
public class DatabaseService {
	
	@PersistenceContext(unitName = "primary")
	private EntityManager em;
	
	@Inject
	private JSONUtil jUtil;


	public String getAllAccounts() { 
		
		Query query = em.createQuery("SELECT a FROM ACCOUNT a");
		Collection<Account> account = (Collection<Account>) query.getResultList();
		String result = jUtil.getJSONForObject(account);
		return result;
	}
	
	public Account findAccount(String accountNumber) {
		
		return em.find(Account.class, accountNumber);
	}
	
	@Transactional(REQUIRED)
	public String addAccount (Account account) {
		
		em.persist(account);
		
		return "{\"message\"; \"account successfully added\"}";
		
	}
	
	@Transactional(REQUIRED)
	public String updateAccount (String accountNumber, String NewJSON) {
		
		Account newrecord = jUtil.getObjectForJSON(NewJSON, Account.class);
		Account oldrecord = findAccount(accountNumber);
		
		
		if (NewJSON !=null) {
			
			oldrecord = newrecord;
			em.merge(oldrecord);
		}
				
		return "{\"message\"; \"account successfully updated\"}";		
		
	}
	
	@Transactional(REQUIRED)
	public String deleteAccount(String accountNumber) {
		
		Account account = findAccount(accountNumber);
		
		if (account !=null) {
			
			em.remove(account);
		}
		
		return "{\"message\"; \"account successfully deleted\"}";
		
	}
	
	public void setManager(EntityManager em) {
		this.em = em;
	} 
	
	public void setUtil(JSONUtil jUtil) {
		this.jUtil = jUtil;
	}
}
