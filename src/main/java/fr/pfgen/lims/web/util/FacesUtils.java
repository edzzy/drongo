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

    public static String getI18nValueInMessages(String key){
        return getI18nValue("messages", key);
    }

    public static String getI18nValueInEnums(String key){
        return getI18nValue("enums", key);
    }

    public static String getI18nValue(String resourceBundleName, String key){
        FacesContext context = FacesContext.getCurrentInstance();
        ResourceBundle bundle = context.getApplication().getResourceBundle(context, resourceBundleName);

        return bundle.getString(key);
    }


    public static void putObjectInSessionMap(String key, Object value){
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(key, value);
    }
    
    public static Object getObjectInSessionMap(String key){
        return FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(key);
    }
    
    public static void removeObjectFromSessionMap(String key){
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove(key);
    }
    
    public static void keepMessageInFlash(){
        FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
    }
}
