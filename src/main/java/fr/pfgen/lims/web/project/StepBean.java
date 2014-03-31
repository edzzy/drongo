/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pfgen.lims.web.project;

import fr.pfgen.lims.domain.projects.Step;
import fr.pfgen.lims.service.ApplicationService;
import fr.pfgen.lims.web.util.FacesUtils;
import fr.pfgen.lims.web.util.flows.StepFlow;
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
public class StepBean extends StepFlow implements Serializable {

    @Autowired
    ApplicationService applicationService;
    private Step step = new Step();

    public String saveStep() {
        try {
            applicationService.saveStep(step);
            FacesUtils.addMessage(null, FacesUtils.getI18nValueInMessages("newStep_added"), step.toString(), FacesMessage.SEVERITY_INFO);
            FacesUtils.keepMessageInFlash();
            return endFlowAndRedirect();
        } catch (Exception e) {
            FacesUtils.addMessage(null, FacesUtils.getI18nValueInMessages("label_error"), e.getMessage(), FacesMessage.SEVERITY_ERROR);
            return null;
        }
    }
    
    public String cancelStep(){
        FacesUtils.addMessage(null, FacesUtils.getI18nValueInMessages("edit_cancelled"), step.toString(), FacesMessage.SEVERITY_INFO);
        FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        return endFlowAndRedirect();
    }

    public void validateName(FacesContext context, UIComponent component, Object value) {
        String name = ((String) value).toUpperCase();

        if (applicationService.findApplicationByName(name) != null) {
            ((UIInput) component).setValid(false);
            FacesUtils.addMessage(component.getClientId(context), FacesUtils.getI18nValueInMessages("label_error"), "\"" + name + "\" " + FacesUtils.getI18nValueInMessages("label_alreadyExists"), FacesMessage.SEVERITY_ERROR);
        }
    }

    public Step getStep() {
        return step;
    }

    public void setStep(Step step) {
        this.step = step;
    }
}
