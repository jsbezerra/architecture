package br.com.inadimplente.security;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.picketlink.Identity;
import org.picketlink.Identity.AuthenticationResult;

@Named
@RequestScoped
public class LoginView {
	
	private static final String HOME = "/home.xhtml?faces-redirect=true";
    private static final String TELA_DE_LOGIN = "/login.xhtml?faces-redirect=true";
	
	@Inject
    private Identity identity;
    
    public String login() {
        AuthenticationResult result = this.identity.login();
        if (result == AuthenticationResult.SUCCESS) {
            return HOME;
        } else {
            return TELA_DE_LOGIN;
        }
    }

    public String logout() {
        this.identity.logout();
        return TELA_DE_LOGIN;
    }

}
