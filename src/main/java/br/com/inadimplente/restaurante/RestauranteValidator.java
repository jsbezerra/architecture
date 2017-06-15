package br.com.inadimplente.restaurante;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;

@Named
public class RestauranteValidator implements Validator {


    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {

    }
}
