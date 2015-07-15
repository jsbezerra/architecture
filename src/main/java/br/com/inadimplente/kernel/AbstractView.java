package br.com.inadimplente.kernel;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

@ViewScoped
public abstract class AbstractView<T> implements Serializable {

	private static final long serialVersionUID = 1L;
	protected T entity;
	
	@Inject
	private MessagesHandler messagesHandler;

	protected abstract <D extends AbstractDAO<T>> D getDao();

	private Class<T> entityClass;

	public AbstractView(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	@PostConstruct
	public void newInstance() {
		try {
			this.entity = entityClass.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			//TODO Criar o Log da aplicação
			e.printStackTrace();
		}
	}

	public T getEntity() {
		return entity;
	}

	public void setEntity(T entity) {
		this.entity = entity;
	}

	public void create() {
		try {
		getDao().create(entity);
		} catch(Exception exception) {
			getMessagesHandler().error("#{messages['crud.error.create']}");
			exception.getCause();
		}
		newInstance();
	}

	public void update() {
		getDao().update(entity);
	}

	public void delete() {
		getDao().delete(entity);
	}
	
	protected MessagesHandler getMessagesHandler() {
		return messagesHandler;
	}

}
