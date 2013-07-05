/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pfgen.lims.web.util;

import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;


/**
 *
 * @author eric
 */
public class FacesUtils {
    
    public static void addMessage(String key, String summary, String detail, FacesMessage.Severity severity){
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage msg = new FacesMessage();
        msg.setSeverity(severity);
        msg.setSummary(summary);
        msg.setDetail(detail);
        context.addMessage(key, msg);
    }
    
    public static String getI18nValue(String key){
        FacesContext context = FacesContext.getCurrentInstance();
        ResourceBundle bundle = context.getApplication().getResourceBundle(context, "messages");
        
        return bundle.getString(key);
    }
}
