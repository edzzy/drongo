/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pfgen.lims.web.converters;

import fr.pfgen.lims.domain.ShippingAddress;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.springframework.stereotype.Component;

/**
 *
 * @author eric
 */
@Component
@FacesConverter("shippingAddressConverter")
public class ShippingAddressConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value.trim() != null && !value.trim().isEmpty()){
            ShippingAddress ad = new ShippingAddress();
            ad.setAddress(value);
            return ad;
        }else{
            return null;
        }
    }
        
    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value==null){ 
            return null;
        }else{
            return value.toString();
        }
    }
}
