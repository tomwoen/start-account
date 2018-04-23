package com.qa.service;

import java.util.List;

import javax.persistence.*;
import javax.transaction.Transactional;
import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;
import com.qa.domain.Account;
import com.qa.util.JSONUtil;

@Transactional(SUPPORTS)
public class DatabaseService {
	
	@PersistenceContext(unitName = "primary")
	private EntityManager em;

	public List <Account> getAllAccounts() { 
		
		TypedQuery<Account> query = em.createQuery("SELECT a FROM ACCOUNT a", Account.class);
		
		return query.getResultList();
	}
	
	public Account findAccount(String accountNumber) {
		
		return em.find(Account.class, accountNumber);
	}
	
	@Transactional(REQUIRED)
	public Account getAccount (Account account) {
		
		em.persist(account);
		
		return account;
		
	}
	
	@Transactional(REQUIRED)
	public Account updateAccount (String accountNumber, String NewJSON) {
		
		JSONUtil jUtil = new JSONUtil();	
		
		em.getTransaction().begin();
		Account oldrecord = em.find(Account.class, accountNumber);
		Account newrecord = jUtil.getObjectForJSON(NewJSON, Account.class);
		
		if (oldrecord !=null) {
			
			em.merge(newrecord);
		}
		em.getTransaction().commit();
		
		return newrecord;		
		
	}
	
	@Transactional(REQUIRED)
	public void deleteAccount(Account account) {
		
		em.getTransaction().begin();
		em.remove(account);
		em.getTransaction().commit();
		
		
	}
}
