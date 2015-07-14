package br.com.inadimplente.kernel;

import static javax.transaction.Transactional.TxType.SUPPORTS;
import static javax.transaction.Transactional.TxType.MANDATORY;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

@Transactional(value = SUPPORTS)
@RequestScoped
public abstract class AbstractDAO<T> {

	private Class<T> entityClass;

	@Inject
	protected EntityManager entityManager;

	public AbstractDAO(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	private EntityManager getEntityManager() {
		return entityManager;
	}

	@Transactional(value = MANDATORY)
	protected void create(T entity) {
		getEntityManager().persist(entity);
	}

	@Transactional(value=MANDATORY)
	public void update(T entity) {
		getEntityManager().merge(entity);
	}

	@Transactional(value=MANDATORY)
	public void delete(T entity) {
		getEntityManager().remove(entity);
	}

	public T find(Object id) {
		return getEntityManager().find(entityClass, id);
	}

	public List<T> listAll() {
		CriteriaQuery<T> cq = getNewQuery();
		cq.select(cq.from(entityClass));
		return getEntityManager().createQuery(cq).getResultList();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public int count() {
		CriteriaQuery cq = getNewQuery();
		Root<T> rt = cq.from(entityClass);
		cq.select(getEntityManager().getCriteriaBuilder().count(rt));
		Query q = getEntityManager().createQuery(cq);
		return ((Long) q.getSingleResult()).intValue();
	}

	@SuppressWarnings("unchecked")
	private CriteriaQuery<T> getNewQuery() {
		return (CriteriaQuery<T>) getEntityManager().getCriteriaBuilder().createQuery();
	}

}
