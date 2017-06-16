package br.com.inadimplente.kernel.producer;

import javax.enterprise.context.ApplicationScoped;
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
	@ApplicationScoped
	public EntityManager getEntityManager(){
		return entityManager;
	}

}
