package com.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public abstract class BaseDao {
	
	EntityManagerFactory entityManagerFactory;
	
	public EntityManager getEntityManager() {
		return this.entityManagerFactory.createEntityManager();
	}
}
