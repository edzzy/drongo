package fr.pfgen.lims.web.converters;

import fr.pfgen.lims.domain.equipments.EquipmentStatus;
import fr.pfgen.lims.domain.equipments.PlateformType;
import org.springframework.stereotype.Component;

import javax.faces.convert.EnumConverter;

/**
 * Created by edouard on 12/03/14.
 */
@Component("plateformTypeConverter")
public class PlateformTypeConverter extends EnumConverter{

    public PlateformTypeConverter(){
        super(PlateformType.class);
    }
}
