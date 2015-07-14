package br.com.inadimplente.kernel;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class EntityManagerProducer {
	
	@PersistenceContext(unitName="inadimplentes")
	private EntityManager entityManager;
	
	@Produces
	@Default
	@RequestScoped
	public EntityManager getEntityManager(){
		return entityManager;
	}

}
