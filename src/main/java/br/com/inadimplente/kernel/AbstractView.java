package br.com.inadimplente.kernel;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.transaction.Transactional;

@ViewScoped
public abstract class AbstractView<T> implements Serializable {

	private static final long serialVersionUID = 1L;
	protected T entity;

	protected abstract <D extends AbstractDAO<T>> D getDao();

	private Class<T> entityClass;

	public AbstractView(Class<T> entityClass) {
		this.entityClass = entityClass;
	}
	
	@PostConstruct
	public void newEntity() throws InstantiationException, IllegalAccessException {
		this.entity = entityClass.newInstance();
	}
	
	public T getEntity() {
		return entity;
	}

	public void setEntity(T entity) {
		this.entity = entity;
	}

	@Transactional
	public void create() {
		getDao().create(entity);
	}
	
	@Transactional
	public void update() {
		getDao().update(entity);
	}
	
	@Transactional
	public void delete() {
		getDao().delete(entity);
	}

}
