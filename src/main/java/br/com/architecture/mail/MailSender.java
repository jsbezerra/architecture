package br.com.architecture.mail;

import javax.ejb.Asynchronous;
import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;

@Singleton
public class MailSender {
	
	@Inject
	private Session session;
	
	@Inject
	private Logger logger;
	
	//TODO transformar esse método em Assíncrono para não pendurar a aplicação
	@Asynchronous
	public void send(MailBean mailBean) {
		Message message = new MimeMessage(session);
		try {
			message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(mailBean.getTo()));
			message.setSubject(mailBean.getSubject());
			message.setText(mailBean.getContent());
			Transport.send(message);
			logger.info("e-mail enviado com sucesso para " + mailBean.getTo());
		} catch (MessagingException e) {
			//TODO internacionalização
			logger.error("Não foi possível enviar o e-mail", e);
		}
	}

}
