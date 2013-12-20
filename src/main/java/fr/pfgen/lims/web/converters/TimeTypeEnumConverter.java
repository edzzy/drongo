/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pfgen.lims.web.converters;

import fr.pfgen.lims.domain.util.TimeType;
import javax.faces.convert.EnumConverter;
import org.springframework.stereotype.Component;

/**
 *
 * @author eric
 */
@Component("timeTypeEnumConverter")
public class TimeTypeEnumConverter extends EnumConverter{
    
    public TimeTypeEnumConverter() {
        super(TimeType.class);
    }
}
