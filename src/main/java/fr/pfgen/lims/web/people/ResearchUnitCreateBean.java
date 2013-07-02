/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pfgen.lims.web.people;

import fr.pfgen.lims.domain.people.ResearchUnit;
import fr.pfgen.lims.service.ResearchTeamService;
import fr.pfgen.lims.web.util.FacesUtils;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
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
@ManagedBean
public class ResearchUnitCreateBean {
    
    private ResearchUnit researchUnit = new ResearchUnit();
    
    @Autowired
    private ResearchTeamService researchTeamService;

    public void validateName(FacesContext context, UIComponent component, Object value) {
        String name = ((String) value).toLowerCase();

        ResearchUnit existingResearchUnit = researchTeamService.findResearchUnitByName(name);

        if (existingResearchUnit != null) {
            ((UIInput) component).setValid(false);
            FacesUtils.addMessage(component.getClientId(context), null, "\"" + name + "\" " + FacesUtils.getI18nValue("label_alreadyExists"), FacesMessage.SEVERITY_ERROR);
        }
    }

    public String saveNewResearchUnit() {
        try {
            researchTeamService.saveResearchUnit(researchUnit);
            return "researchTeamCreate?faces-redirect=true";
        } catch (Exception e) {
            FacesUtils.addMessage(null, FacesUtils.getI18nValue("label_error"), e.getMessage(), FacesMessage.SEVERITY_ERROR);
            return null;
        }
    }

    public String cancelResearchUnitCreation() {
        return "researchTeamCreate?faces-redirect=true";
    }
    
    public ResearchUnit getResearchUnit() {
        return researchUnit;
    }

    public void setResearchUnit(ResearchUnit researchUnit) {
        this.researchUnit = researchUnit;
    }
}
