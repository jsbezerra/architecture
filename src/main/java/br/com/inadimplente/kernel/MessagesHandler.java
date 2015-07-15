package br.com.inadimplente.kernel;

import java.io.Serializable;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;
import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/*
 * Componente em experimentação
 * */

@Named
@Dependent // Verificar um melhor escopo para este componente
public class MessagesHandler implements Serializable {

	private static final long serialVersionUID = 1L;

	public void addMessage(Severity severity, String messageKey) {
		String summary = null;
		String message = evaluateMessage(messageKey);
		if (FacesMessage.SEVERITY_INFO == severity) {
			summary = evaluateMessage("#{messages['message.summary.info']}");
		} else if (FacesMessage.SEVERITY_WARN == severity) {
			summary = evaluateMessage("#{messages['message.summary.warn']}");
		} else if (FacesMessage.SEVERITY_ERROR == severity) {
			summary = evaluateMessage("#{messages['message.summary.error']}");
		} else if (FacesMessage.SEVERITY_FATAL == severity) {
			summary = evaluateMessage("#{messages['message.summary.fatal']}");
		}
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, summary, message));
	}

	public void info(String messageKey) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", evaluateMessage(messageKey)));
	}

	public void warn(String messageKey) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", evaluateMessage(messageKey)));
	}

	public void error(String messageKey) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", evaluateMessage(messageKey)));
	}

	public void fatal(String messageKey) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fatal!", evaluateMessage(messageKey)));
	}

	private String evaluateMessage(String messageKey) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExpressionFactory expressionFactory = facesContext.getApplication().getExpressionFactory();
		ELContext elContext = facesContext.getELContext();
		ValueExpression valueExpression = expressionFactory.createValueExpression(elContext, messageKey, String.class);
		return (String) valueExpression.getValue(elContext);
	}

}
