/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pfgen.lims.web.people;

import fr.pfgen.lims.domain.Client;
import fr.pfgen.lims.domain.PfMember;
import fr.pfgen.lims.service.ClientService;
import fr.pfgen.lims.service.PfMemberService;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author eric
 */
@Controller
@Scope("view")
@ManagedBean
public class PfMemberBean implements Serializable{
    
    @Autowired
    PfMemberService pfMemberService;
    
    @Autowired
    ClientService clientService;
    
    private List<PfMember> pfMemberList;
    private List<PfMember> filteredPfMembers;
    
    @PostConstruct
    public void init() {
        pfMemberList = pfMemberService.findAllPfMembers();
    }
    
    public Date getMaxDate(){
        return new Date();
    }
    
    public int getPfMemberNumber(){
        return pfMemberList.size();
    }

    public List<PfMember> getFilteredPfMembers() {
        return filteredPfMembers;
    }

    public void setFilteredPfMembers(List<PfMember> filteredPfMembers) {
        this.filteredPfMembers = filteredPfMembers;
    }

    public List<PfMember> getPfMemberList() {
        return pfMemberList;
    }

    public void setPfMemberList(List<PfMember> pfMemberList) {
        this.pfMemberList = pfMemberList;
    }
    
    public void onEdit(RowEditEvent event) {
        PfMember pfMemberToEdit = (PfMember) event.getObject();
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            PfMember pfMemberUpdated = pfMemberService.updatePfMember(pfMemberToEdit);
            int index = pfMemberList.indexOf(pfMemberToEdit);
            pfMemberList.remove(index);
            pfMemberList.add(index, pfMemberUpdated);
            RequestContext rcontext = RequestContext.getCurrentInstance();
            rcontext.update("pfMembersTable");

            String text = context.getApplication().getResourceBundle(context, "messages").getString("edit_done");
            FacesMessage msg = new FacesMessage(text, ((PfMember) event.getObject()).toString());

            context.addMessage(null, msg);
        } catch (Exception e) {
            String text = context.getApplication().getResourceBundle(context, "messages").getString("edit_error");
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, text, e.getMessage());
            context.validationFailed();
            context.addMessage(null, msg);
        }
    }

    public void onCancel(RowEditEvent event) {
        FacesContext context = FacesContext.getCurrentInstance();
        String text = context.getApplication().getResourceBundle(context, "messages").getString("edit_cancelled");
        FacesMessage msg = new FacesMessage(text, ((PfMember) event.getObject()).toString());

        context.addMessage(null, msg);
    }

    public void onEditInit(RowEditEvent event) {
        PfMember pfMemberToEdit = (PfMember) event.getObject();
        PfMember pfMemberIdDb = pfMemberService.findPfMember(pfMemberToEdit.getId());

        if (pfMemberToEdit.getVersion() != pfMemberIdDb.getVersion()) {
            int index = pfMemberList.indexOf(pfMemberToEdit);
            pfMemberList.remove(index);
            pfMemberList.add(index, pfMemberIdDb);
            RequestContext rcontext = RequestContext.getCurrentInstance();
            rcontext.update("pfMembersTable");

            FacesContext context = FacesContext.getCurrentInstance();
            String text = context.getApplication().getResourceBundle(context, "messages").getString("edit_rowchanged");
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, text, pfMemberToEdit.toString());

            context.addMessage(null, msg);
        }
    }
    
    public void validateEmail(FacesContext context, UIComponent component, Object value) {
        String email = (String) value;
        
        Client existingClient = clientService.findByEmail(email);
        PfMember existingPfMember = pfMemberService.findByEmail(email);
        
        if ((existingClient != null && existingClient.getId() != (Long) component.getAttributes().get("pfMemberID")) || (existingPfMember != null && existingPfMember.getId() != (Long) component.getAttributes().get("pfMemberID"))) {
            ((UIInput) component).setValid(false);
            ResourceBundle bundle = context.getApplication().getResourceBundle(context, "messages");
            String error = bundle.getString("edit_error");
            String text = bundle.getString("label_alreadyExists");
            FacesMessage msg = new FacesMessage(error, email + " " + text);
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            context.addMessage(component.getClientId(context), msg);
        }
    }
}
