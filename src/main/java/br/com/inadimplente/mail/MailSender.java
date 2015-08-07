package br.com.inadimplente.mail;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;

@Named
@RequestScoped
public class MailSender {
	
	@Inject
	private Session session;
	
	@Inject
	private Logger logger;
	
	//TODO transformar esse método em Assíncrono para não pendurar a aplicação
	public void send(MailBean mailBean) {
		Message message = new MimeMessage(session);
		try {
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mailBean.getTo()));
			message.setSubject(mailBean.getSubject());
			message.setText(mailBean.getContent());
			Transport.send(message);
		} catch (MessagingException e) {
			//TODO internacionalização
			logger.error("Não foi possível enviar o e-mail", e);
		}
	}

}
