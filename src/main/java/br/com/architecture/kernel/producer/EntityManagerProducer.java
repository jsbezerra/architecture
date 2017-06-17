package br.com.architecture.kernel.producer;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class EntityManagerProducer {
	
	@PersistenceContext(unitName="architecture")
	private EntityManager entityManager;
	
	@Produces
	@Default
	@RequestScoped
	public EntityManager getEntityManager(){
		return entityManager;
	}

}
