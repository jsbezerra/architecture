package br.com.architecture.kernel;

import static javax.transaction.Transactional.TxType.SUPPORTS;
import static javax.transaction.Transactional.TxType.REQUIRED;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

@Transactional(value = SUPPORTS)
public abstract class AbstractDAO<T> implements Serializable {

	private Class<T> entityClass;

	@Inject
	protected EntityManager entityManager;

	public AbstractDAO(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	protected EntityManager getEntityManager() {
		return entityManager;
	}

	@Transactional(value = REQUIRED)
	public void create(T entity) {
		getEntityManager().persist(entity);
	}

	@Transactional(value=REQUIRED)
	public void update(T entity) {
		getEntityManager().merge(entity);
	}

	@Transactional(value=REQUIRED)
	public void delete(T entity) {
		getEntityManager().remove(getEntityManager().contains(entity) ? entity : getEntityManager().merge(entity));
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
