package fr.pfgen.lims.web.converters;

import fr.pfgen.lims.domain.equipments.EquipmentStatus;
import org.omnifaces.converter.SelectItemsConverter;
import org.springframework.stereotype.Component;

import javax.faces.convert.EnumConverter;
import javax.faces.model.SelectItem;

/**
 * Created by edouard on 11/03/14.
 */
@Component("statusTypeConverter")
public class StatusTypeConverter extends EnumConverter{

    public StatusTypeConverter(){
        super(EquipmentStatus.class);
    }
}
