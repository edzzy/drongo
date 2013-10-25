/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pfgen.lims.web.people;

import fr.pfgen.lims.domain.people.PfMember;
import fr.pfgen.lims.service.ClientService;
import fr.pfgen.lims.service.PfMemberService;
import fr.pfgen.lims.web.util.FacesUtils;
import fr.pfgen.lims.web.util.flows.FlowType;
import fr.pfgen.lims.web.util.flows.GenericFlow;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
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
public class PfMembersBean extends GenericFlow implements Serializable{
    
    @Autowired
    PfMemberService pfMemberService;
    
    @Autowired
    ClientService clientService;
    
    private List<PfMember> pfMemberList;
    private List<PfMember> filteredPfMembers;
    private PfMember selectedPfMember;
    
    @PostConstruct
    public void init() {
        pfMemberList = pfMemberService.findAllActivePfMembers();
    }

    public PfMember getSelectedPfMember() {
        return selectedPfMember;
    }

    public void setSelectedPfMember(PfMember selectedPfMember) {
        this.selectedPfMember = selectedPfMember;
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
    
    public void cancelModify() {
        FacesUtils.addMessage(null, FacesUtils.getI18nValue("edit_cancelled"), selectedPfMember.toString(), FacesMessage.SEVERITY_INFO);
    }
    
    public void deletePfMember() {
        try {
            pfMemberService.deletePfMember(selectedPfMember);
            pfMemberList.remove(selectedPfMember);
            FacesUtils.addMessage(null, FacesUtils.getI18nValue("label_deleteDone"), selectedPfMember.toString(), FacesMessage.SEVERITY_INFO);
        } catch (Exception e) {
            FacesUtils.addMessage(null, FacesUtils.getI18nValue("label_error"), e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }
    
    public String editPfMember(){
        FacesUtils.removeObjectFromSessionMap("pfMember");
        FacesUtils.putObjectInSessionMap("pfMember", selectedPfMember);
        return enterFlow(FlowType.PFMEMBER);
    }

    public void cancelDeletion() {
        FacesUtils.addMessage(null, FacesUtils.getI18nValue("label_deleteCanceled"), selectedPfMember.toString(), FacesMessage.SEVERITY_INFO);
    }
    
    public String createNewPfMember(){
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("pfMember");
        return enterFlow(FlowType.PFMEMBER);
    }
}
