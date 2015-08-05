package br.com.inadimplente.kernel;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

import org.slf4j.Logger;

import br.com.inadimplente.utils.EntityUtils;

//TODO internacionalização
@ViewScoped
public abstract class AbstractView<T> implements Serializable {

	private static final long serialVersionUID = 1L;
	protected T entity;

	@Inject
	private MessagesHandler messagesHandler;
	@Inject 
	private Logger logger;

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
			logger.error("Não foi possível intanciar uma nova entidade", e);
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
		} catch (Exception e) {
			getMessagesHandler().error("#{messages['crud.error.create']}");
			logger.error("Não foi possível criar a entidade", e);
		}
	}

	public void update() {
		try {
			getDao().update(entity);
			messagesHandler.info("#{messages['crud.success.update']}");
		} catch (Exception e) {
			getMessagesHandler().error("#{messages['crud.error.update']}");
			logger.error("Não foi possível salvar as modificações na entidade", e);
		}
	}

	public void delete() {
		try {
			getDao().delete(entity);
			messagesHandler.info("#{messages['crud.success.delete']}");
		} catch (Exception e) {
			getMessagesHandler().error("#{messages['crud.error.delete']}");
			logger.error("Não foi possível deletar a entidade", e);
		}
	}

	protected MessagesHandler getMessagesHandler() {
		return messagesHandler;
	}

	public boolean isPersisted() {
		return EntityUtils.getId(entity, entityClass) != null;
	}

}
