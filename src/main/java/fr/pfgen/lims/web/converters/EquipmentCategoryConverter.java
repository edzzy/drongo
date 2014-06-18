/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pfgen.lims.web.converters;

import fr.pfgen.lims.domain.equipments.EquipmentCategory;
import javax.faces.convert.EnumConverter;
import org.omnifaces.converter.SelectItemsConverter;
import org.springframework.stereotype.Component;

/**
 *
 * @author eric
 */
@Component("equipmentCategoryConverter")
public class EquipmentCategoryConverter extends EnumConverter{
    
    public EquipmentCategoryConverter(){
        super(EquipmentCategory.class);
    }
    
}
