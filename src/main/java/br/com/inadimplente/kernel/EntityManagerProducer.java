package br.com.inadimplente.kernel;

import javax.enterprise.inject.Default;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.picketlink.annotations.PicketLink;

public class EntityManagerProducer {
	
	@PersistenceContext(unitName="inadimplentes")
	@Produces
	@Default
	@PicketLink
	private EntityManager entityManager;
	
}
