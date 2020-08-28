package br.com.architecture.access;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

@Named
@RequestScoped
public class LoginController {

	private static final Logger LOG = Logger.getLogger(LoginController.class.getName());
	
	private String username;
	private String password;
	boolean remember = false;

	public String login() {
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		token.setRememberMe(remember);
		Subject currentUser = SecurityUtils.getSubject();
		try {
			currentUser.login(token);
			// TODO Colocar as informações do Usuário na sessão
		    // TODO lidar com a internacionalização
		} catch (UnknownAccountException exception) {
			LOG.log(Level.SEVERE, "Usuário não encontrado", exception);
		} catch (AuthenticationException e) {
			LOG.log(Level.SEVERE, e.getMessage(), e);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário ou Senha inválidos", ""));
			return "/index";
		}
		return "/home.xhtml?faces-redirect=true";
	}

	public String logout() {
		SecurityUtils.getSubject().logout();
		return "/index";
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
