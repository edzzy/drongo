/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pfgen.lims.web.converters;

import fr.pfgen.lims.domain.projects.Activity;
import fr.pfgen.lims.web.util.FacesUtils;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import org.omnifaces.converter.SelectItemsConverter;
import org.springframework.stereotype.Component;

/**
 *
 * @author eric
 */
@Component("activityConverter")
public class ActivityConverter extends SelectItemsConverter{

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Activity activity = (Activity) value;
        return FacesUtils.getI18nValueInEnums(activity.getType().getLabel());
    }
    
}
