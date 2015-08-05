package br.com.inadimplente.access;

import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

import br.com.inadimplente.kernel.MessagesHandler;

@Named
@RequestScoped
public class LoginController {

	private static final Logger LOG = Logger.getLogger(LoginController.class.getName());
	
	@Inject
	private MessagesHandler messagesHandler;

	private String username;
	private String password;
	boolean remember = false;

	public String login() {
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		token.setRememberMe(remember);
		Subject currentUser = SecurityUtils.getSubject();
		try {
			currentUser.login(token);
		} catch (AuthenticationException e) {
			LOG.warning(e.getMessage());
			messagesHandler.error("#{messages['login.failed']}");
			/*FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Login Failed: " + e.getMessage(), e.toString()));*/
			return "/index";
		}
		return "/home.xhtml?faces-redirect=true";
	}

	public String logout() {
		return "";
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isRemember() {
		return remember;
	}

	public void setRemember(boolean remember) {
		this.remember = remember;
	}
	
	

}
