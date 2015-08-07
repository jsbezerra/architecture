package br.com.inadimplente.kernel.producer;

import javax.annotation.Resource;
import javax.enterprise.inject.Produces;
import javax.mail.Session;

public class MailSessionProducer {
	
	@Resource(lookup = "java:/jboss/mail/gmail")
    @Produces
    private Session mailSession;

}
