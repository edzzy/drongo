/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pfgen.lims.web.converters;

import fr.pfgen.lims.domain.people.Address;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import org.springframework.stereotype.Component;

/**
 *
 * @author eric
 */
@Component("addressConverter")
public class AddressConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value==null || value.isEmpty()){
            return null;
        }else{
            Address ad = new Address();
            ad.setAddress(value);
            return ad;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value==null){
            return null;
        }else{
            return ((Address) value).toString();
        }
    }
}
