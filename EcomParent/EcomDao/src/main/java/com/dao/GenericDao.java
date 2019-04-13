package com.dao;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class GenericDao extends BaseDao {
	
	@Autowired
	GenericDao() {
		this.entityManagerFactory = Persistence.createEntityManagerFactory("my_mysql");
	}
	
	public Object getEntity(Class<?> classType, int id) {
		EntityManager entityManager = super.getEntityManager();
		return entityManager.find(classType, id);
	}
	
	@Transactional
	public void insertEntity(final Object object) {
		EntityManager entityManager = super.getEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(object);
		entityManager.getTransaction().commit();
		entityManager.close();
	}
	
	public List executeQuery(final String query, final Map<String, String> paramMap) {
		EntityManager entityManager = super.getEntityManager();
		Query q = entityManager.createQuery(query);
		paramMap.entrySet().forEach(entry -> q.setParameter(entry.getKey(), entry.getValue()));
		System.out.println("Executing query:" + q);
		return q.getResultList();
	}
}
