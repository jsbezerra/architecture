package br.com.inadimplente.mail;

import java.util.ArrayList;
import java.util.List;

public class MailBean {

	private String to;
	private String subject;
	private String content;

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
