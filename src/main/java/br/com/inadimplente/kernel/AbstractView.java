package br.com.inadimplente.kernel;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

import br.com.inadimplente.utils.EntityUtils;

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
			// TODO Criar o Log da aplicação
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
			getDao().create(getEntity());
			messagesHandler.info("#{messages['crud.success.create']}");
		} catch (Exception exception) {
			getMessagesHandler().error("#{messages['crud.error.create']}");
			// TODO Criar o Log da aplicação
			exception.printStackTrace();
		}
		newInstance();
	}

	public void update() {
		try {
			getDao().update(entity);
			messagesHandler.info("#{messages['crud.success.update']}");
		} catch (Exception exception) {
			getMessagesHandler().error("#{messages['crud.error.update']}");
			// TODO Criar o Log da aplicação
			exception.printStackTrace();
		}
	}

	public void delete() {
		try {
			getDao().delete(entity);
			messagesHandler.info("#{messages['crud.success.delete']}");
		} catch (Exception exception) {
			getMessagesHandler().error("#{messages['crud.error.delete']}");
			// TODO Criar o Log da aplicação
			exception.printStackTrace();
		}
	}

	protected MessagesHandler getMessagesHandler() {
		return messagesHandler;
	}

	public boolean isPersisted() {
		return (EntityUtils.getId(entity, entityClass) != null);
	}

}
