package fr.pfgen.lims.web.converters;

import fr.pfgen.lims.domain.equipments.Intervention;
import fr.pfgen.lims.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

/**
 * Created by edouard on 06/03/14.
 */
@Component("interventionConverter")
public class InterventionConverter implements Converter{
    @Autowired
    EquipmentService equipmentService;
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        try {
            Intervention i = equipmentService.findInterventionById(Long.valueOf(value));
            if (i==null) throw new ConverterException();
            return i;
        } catch (NumberFormatException e) {
            throw new ConverterException();
        }
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
        try{
            Intervention i = (Intervention) value;
            if (i==null || i.getId()==null) return null;
            return i.getId().toString();
        }catch(NullPointerException e){
            return null;
        }
    }

}
