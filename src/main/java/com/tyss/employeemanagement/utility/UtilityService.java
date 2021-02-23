package com.tyss.employeemanagement.utility;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

import org.springframework.stereotype.Service;

@Service
public class UtilityService {

	@PersistenceUnit
	private EntityManagerFactory emf;

	@PersistenceContext
	private EntityManager em;

	public EntityManager dbConnector() {
		if (emf == null) {
			em = emf.createEntityManager();
			return em;
		}
		return em;
	}
}
