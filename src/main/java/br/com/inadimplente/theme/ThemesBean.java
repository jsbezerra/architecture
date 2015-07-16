package br.com.inadimplente.theme;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class ThemesBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String theme = "sunny";
	
	public String getTheme(){
		return theme;
	}

}
