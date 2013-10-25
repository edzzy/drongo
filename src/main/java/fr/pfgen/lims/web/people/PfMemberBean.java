/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pfgen.lims.web.people;

import fr.pfgen.lims.domain.people.AppCredentials;
import fr.pfgen.lims.domain.people.Client;
import fr.pfgen.lims.domain.people.PfMember;
import fr.pfgen.lims.service.ClientService;
import fr.pfgen.lims.service.PfMemberService;
import fr.pfgen.lims.web.util.FacesUtils;
import fr.pfgen.lims.web.util.flows.PfMemberFlow;
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
@Scope("view")
public class PfMemberBean extends PfMemberFlow implements Serializable{

    @Autowired
    private PfMemberService pfMemberService;
    @Autowired
    private ClientService clientService;
    private PfMember pfMember;
    private boolean isNewPfMember;
    private boolean isAuthCredChange = false;

    public void initPfMember() {
        if (!FacesContext.getCurrentInstance().isPostback()) {

            pfMember = (PfMember) FacesUtils.getObjectInSessionMap("pfMember");
            
            if (pfMember == null) {
                pfMember = new PfMember();
                pfMember.setAppCredentials(new AppCredentials());
                FacesUtils.putObjectInSessionMap("pfMember", pfMember);
                isNewPfMember = true;
            } else {
                isNewPfMember = false;
            }
        }
    }

    public String savePfMember() {
        try {
            if (pfMember.getId() == null) {
                pfMemberService.savePfMember(pfMember);
                FacesUtils.addMessage(null, FacesUtils.getI18nValue("newPfMember_added"), pfMember.toString(), FacesMessage.SEVERITY_INFO);

            } else {
                pfMemberService.updatePfMember(pfMember);
                FacesUtils.addMessage(null, FacesUtils.getI18nValue("edit_done"), pfMember.toString(), FacesMessage.SEVERITY_INFO);
            }
            FacesUtils.keepMessageInFlash();
            return endFlowAndRedirect();
        } catch (Exception e) {
            FacesUtils.addMessage(null, FacesUtils.getI18nValue("label_error"), e.getMessage(), FacesMessage.SEVERITY_ERROR);
            return null;
        }
    }

    public String cancelPfMember() {
        if (pfMember.getId() == null) {
            FacesUtils.addMessage(null, FacesUtils.getI18nValue("label_createCanceled"), null, FacesMessage.SEVERITY_INFO);
        } else {
            FacesUtils.addMessage(null, FacesUtils.getI18nValue("edit_cancelled"), pfMember.toString(), FacesMessage.SEVERITY_INFO);
        }
        FacesUtils.keepMessageInFlash();
        return endFlowAndRedirect();
    }

    public void showPassFields() {
        isNewPfMember = true;
    }
    
    public boolean isIsAuthCredChange(){
        return isAuthCredChange;
    }

    public void validateEmail(FacesContext context, UIComponent component, Object value) {
        String email = ((String) value).toLowerCase();

        Client existingClient = clientService.findByEmail(email);
        PfMember existingPfMember = pfMemberService.findByEmail(email);

        if ((existingClient != null && existingClient.getId() != pfMember.getId()) || (existingPfMember != null && existingPfMember.getId() != pfMember.getId())) {
            ((UIInput) component).setValid(false);
            FacesUtils.addMessage(component.getClientId(context), FacesUtils.getI18nValue("edit_error"), "\"" + email + "\" " + FacesUtils.getI18nValue("label_alreadyExists"), FacesMessage.SEVERITY_ERROR);
        }
    }

    public String getSaveOrEditLabel() {
        if (pfMember.getId() != null) {
            return FacesUtils.getI18nValue("label_edit");
        } else {
            return FacesUtils.getI18nValue("label_save");
        }
    }

    public String getPlusOrPen() {
        if (pfMember.getId() != null) {
            return "ui-icon-pencil";
        } else {
            return "ui-icon-disk";
        }
    }

    public PfMember getPfMember() {
        return pfMember;
    }

    public void setPfMember(PfMember pfMember) {
        this.pfMember = pfMember;
    }

    public boolean isIsNewPfMember() {
        return isNewPfMember;
    }

    public void setIsNewPfMember(boolean isNewPfMember) {
        this.isNewPfMember = isNewPfMember;
    }
}
