/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pfgen.lims.web.project;

import fr.pfgen.lims.domain.projects.ApplicationCategory;
import fr.pfgen.lims.service.ApplicationService;
import fr.pfgen.lims.web.util.FacesUtils;
import fr.pfgen.lims.web.util.flows.ApplicationCategoryFlow;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author eric
 */
@Component
@Scope("request")
public class ApplicationCategoryBean extends ApplicationCategoryFlow implements Serializable{
    
    @Autowired
    ApplicationService applicationService;
    
    private ApplicationCategory appCat = new ApplicationCategory();
    
    public void initApplicationCategory(){
        if (!FacesContext.getCurrentInstance().isPostback()) {
            appCat = (ApplicationCategory) FacesUtils.getObjectInSessionMap("applicationCategory");

            if (appCat == null) {
                appCat = new ApplicationCategory();
                FacesUtils.putObjectInSessionMap("applicationCategory", appCat);
            }
        }
    }
    
    public void validateName(FacesContext context, UIComponent component, Object value){
        String name = ((String) value).toUpperCase();
        
        if (applicationService.findApplicationCategoryByName(name) != null){
            ((UIInput) component).setValid(false);
            FacesUtils.addMessage(component.getClientId(context), FacesUtils.getI18nValueInMessages("label_error"), "\"" + name + "\" " + FacesUtils.getI18nValueInMessages("label_alreadyExists"), FacesMessage.SEVERITY_ERROR);
        }
    }
    
    public String saveApplicationCategory(){
        try {
            applicationService.saveApplicationCategory(appCat);
            FacesUtils.addMessage(null, FacesUtils.getI18nValueInMessages("newAppCat_added"), appCat.toString(), FacesMessage.SEVERITY_INFO);
            FacesUtils.keepMessageInFlash();
            return endFlowAndRedirect();
        } catch (Exception e) {
            FacesUtils.addMessage(null, FacesUtils.getI18nValueInMessages("label_error"), e.getMessage(), FacesMessage.SEVERITY_ERROR);
            return null;
        }
    }

    public ApplicationCategory getAppCat() {
        return appCat;
    }

    public void setAppCat(ApplicationCategory appCat) {
        this.appCat = appCat;
    }
}
