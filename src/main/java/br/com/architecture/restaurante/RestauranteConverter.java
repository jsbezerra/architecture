package br.com.architecture.restaurante;


import org.omnifaces.util.Beans;


import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.inject.Inject;
import javax.inject.Named;


//@FacesConverter("restauranteCoverter")
@Named
public class RestauranteConverter implements Converter {

    @Inject
    private RestauranteDAO restauranteDAO;

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {

        if (s == null || s.isEmpty()) {
            return null;
        }
        try {
            Restaurante r = Beans.getReference(RestauranteDAO.class).find(Long.valueOf(s));
            return r;
            //return restauranteDAO.find(Long.valueOf(s));
        } catch (NumberFormatException e) {
            throw new ConverterException(new FacesMessage(s + " is not a valid Restaurante ID"), e);
        }
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        if (o == null) {
            return "";
        } else if (o instanceof Restaurante) {
            return String.valueOf(((Restaurante) o).getId());
        } else {
            throw new ConverterException(new FacesMessage(o + " is not a valid Warehouse"));
        }
    }
}
