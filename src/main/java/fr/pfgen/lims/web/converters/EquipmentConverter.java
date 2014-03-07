/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pfgen.lims.web.converters;

import fr.pfgen.lims.domain.equipments.Equipment;
import fr.pfgen.lims.domain.equipments.Intervention;
import fr.pfgen.lims.service.EquipmentService;
import org.omnifaces.converter.SelectItemsConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

/**
 *
 * @author edouard
 */
@Component("equipmentConverter")
public class EquipmentConverter implements Converter{
    @Autowired
    EquipmentService equipmentService;
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        try {
            Equipment i = equipmentService.findEquipmentById(Long.valueOf(value));
            if (i==null) throw new ConverterException();
            return i;
        } catch (NumberFormatException e) {
            throw new ConverterException();
        }
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
        try{
            Equipment i = (Equipment) value;
            if (i==null || i.getId()==null) return null;
            return i.getId().toString();
        }catch(NullPointerException e){
            return null;
        }

    }
}
