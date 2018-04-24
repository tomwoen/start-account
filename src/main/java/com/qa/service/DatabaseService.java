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
	JSONUtil jUtil;

	public String getAllAccounts() { 
		
		Query query = em.createQuery("SELECT a FROM ACCOUNT a", Account.class);
		Collection<Account> account = (Collection<Account>) query.getResultList();
		String result = jUtil.getJSONForObject(account);
		return result;
	}
	
	public Account findAccount(String accountNumber) {
		
		return em.find(Account.class, accountNumber);
	}
	
	@Transactional(REQUIRED)
	public String getAccount (Account account) {
		
		em.persist(account);
		
		return "{\"message\"; \"account successfully added\"}";
		
	}
	
	@Transactional(REQUIRED)
	public String updateAccount (String accountNumber, String NewJSON) {
		
		em.getTransaction().begin();
		Account oldrecord = em.find(Account.class, accountNumber);
		Account newrecord = jUtil.getObjectForJSON(NewJSON, Account.class);
		
		if (oldrecord !=null) {
			
			em.merge(newrecord);
		}
		em.getTransaction().commit();
		
		return "{\"message\"; \"account successfully updated\"}";		
		
	}
	
	@Transactional(REQUIRED)
	public String deleteAccount(Account account) {
		
		em.getTransaction().begin();
		em.remove(account);
		em.getTransaction().commit();
		return "{\"message\"; \"account successfully deleted\"}";
		
	}
	
	public void setManager(EntityManager em) {
		this.em = em;
	} 
}
