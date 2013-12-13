/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pfgen.lims.web.project;

import fr.pfgen.lims.domain.projects.Application;
import fr.pfgen.lims.domain.projects.ApplicationCategory;
import fr.pfgen.lims.service.ApplicationService;
import fr.pfgen.lims.web.util.FacesUtils;
import fr.pfgen.lims.web.util.flows.ApplicationFlow;
import fr.pfgen.lims.web.util.flows.FlowType;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
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
@Scope("view")
public class ApplicationBean extends ApplicationFlow implements Serializable{
    
    @Autowired
    ApplicationService applicationService;
    
    private Application application;
    private List<ApplicationCategory> applicationCategoryList;
    
    public void initApplication(){
        if (!FacesContext.getCurrentInstance().isPostback()) {
            application = (Application) FacesUtils.getObjectInSessionMap("application");

            if (application == null) {
                application = new Application();
                FacesUtils.putObjectInSessionMap("application", application);
            }
        }
    }
    
    @PostConstruct
    public void init(){
        applicationCategoryList = applicationService.findAllApplicationCategories();
    }
    
    public void validateName(FacesContext context, UIComponent component, Object value){
        String name = ((String) value).toUpperCase();
        
        if (applicationService.findApplicationByName(name)!=null){
            ((UIInput) component).setValid(false);
            FacesUtils.addMessage(component.getClientId(context), FacesUtils.getI18nValueInMessages("label_error"), "\"" + name + "\" " + FacesUtils.getI18nValueInMessages("label_alreadyExists"), FacesMessage.SEVERITY_ERROR);
        }
    }
    
    public void validateCode(FacesContext context, UIComponent component, Object value){
        String code = ((String) value).toUpperCase();
        
        if (applicationService.findApplicationByCode(code)!=null){
            ((UIInput) component).setValid(false);
            FacesUtils.addMessage(component.getClientId(context), FacesUtils.getI18nValueInMessages("label_error"), "\"" + code + "\" " + FacesUtils.getI18nValueInMessages("label_alreadyExists"), FacesMessage.SEVERITY_ERROR);
        }
    }
    
    public String saveApplication(){
        try {
            applicationService.saveApplication(application);
            FacesUtils.addMessage(null, FacesUtils.getI18nValueInMessages("newApp_added"), application.toString(), FacesMessage.SEVERITY_INFO);
            FacesUtils.keepMessageInFlash();
            return endFlowAndRedirect();
        } catch (Exception e) {
            FacesUtils.addMessage(null, FacesUtils.getI18nValueInMessages("label_error"), e.getMessage(), FacesMessage.SEVERITY_ERROR);
            return null;
        }
    }
    
    public String createNewActivity(){
        return enterFlow(FlowType.ACTIVITY);
    }
    
    public String createNewApplicationCategory(){
        return enterFlow(FlowType.APPLICATIONCATEGORY);
    }

    public List<ApplicationCategory> getApplicationCategoryList() {
        return applicationCategoryList;
    }

    public void setApplicationCategoryList(List<ApplicationCategory> applicationCategoryList) {
        this.applicationCategoryList = applicationCategoryList;
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }
}
