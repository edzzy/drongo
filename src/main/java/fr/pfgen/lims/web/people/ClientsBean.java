/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pfgen.lims.web.people;

import fr.pfgen.lims.domain.people.Client;
import fr.pfgen.lims.domain.people.ClientType;
import fr.pfgen.lims.domain.people.PfMember;
import fr.pfgen.lims.domain.people.ResearchTeam;
import fr.pfgen.lims.domain.people.ResearchUnit;
import fr.pfgen.lims.service.ClientService;
import fr.pfgen.lims.service.ClientTypeService;
import fr.pfgen.lims.service.PfMemberService;
import fr.pfgen.lims.service.ResearchTeamService;
import fr.pfgen.lims.web.util.FacesUtils;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
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
public class ClientsBean implements Serializable {

    @Autowired
    ClientService clientService;
    
    @Autowired
    ClientTypeService clientTypeService;
    
    @Autowired
    PfMemberService pfMemberService;
    
    @Autowired
    private ResearchTeamService researchTeamService;
    
    private List<Client> clientList;
    private List<ClientType> clientTypeList;
    private List<Client> filteredClients;
    private Client selectedClient;
    private Map<ResearchUnit, List<ResearchTeam>> unit2teams;
    
    @PostConstruct
    public void init() {
        clientList = clientService.findAllActiveClients();
        clientTypeList = clientTypeService.findAllClientTypes();
        unit2teams = researchTeamService.getUnits2Teams();
    }

    public Map<ResearchUnit, List<ResearchTeam>> getUnit2teams() {
        return unit2teams;
    }

    public void setUnit2teams(Map<ResearchUnit, List<ResearchTeam>> unit2teams) {
        this.unit2teams = unit2teams;
    }

    public Client getSelectedClient() {
        return selectedClient;
    }

    public void setSelectedClient(Client selectedClient) {
        this.selectedClient = selectedClient;
    }

    public List<ClientType> getClientTypeList() {
        return clientTypeList;
    }

    public void setClientTypeList(List<ClientType> clientTypeList) {
        this.clientTypeList = clientTypeList;
    }

    public ClientService getClientService() {
        return clientService;
    }

    public void setClientService(ClientService clientService) {
        this.clientService = clientService;
    }

    public int getClientNumber() {
        return clientList.size();
    }

    public List<Client> getClientList() {
        return clientList;
    }

    public void setClientList(List<Client> clientList) {
        this.clientList = clientList;
    }

    public List<Client> getFilteredClients() {
        return filteredClients;
    }

    public void setFilteredClients(List<Client> filteredClients) {
        this.filteredClients = filteredClients;
    }

    /*
    public void onEdit(RowEditEvent event) {
        Client clientToEdit = (Client) event.getObject();
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            Client clientUpdated = clientService.updateClient(clientToEdit);
            int index = clientList.indexOf(clientToEdit);
            clientList.remove(index);
            clientList.add(index, clientUpdated);
            RequestContext rcontext = RequestContext.getCurrentInstance();
            rcontext.update("clientTable");

            FacesUtils.addMessage(null, FacesUtils.getI18nValue("edit_done"), ((Client) event.getObject()).toString(), FacesMessage.SEVERITY_INFO);
        } catch (Exception e) {
            context.validationFailed();
            FacesUtils.addMessage(null, FacesUtils.getI18nValue("edit_error"), e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

    public void onCancel(RowEditEvent event) {
        FacesUtils.addMessage(null, FacesUtils.getI18nValue("edit_cancelled"), ((Client) event.getObject()).toString(), FacesMessage.SEVERITY_INFO);
    }

    public void onEditInit(RowEditEvent event) {
        Client clientToEdit = (Client) event.getObject();
        Client clientIdDb = clientService.findClient(clientToEdit.getId());

        if (clientToEdit.getVersion() != clientIdDb.getVersion()) {
            int index = clientList.indexOf(clientToEdit);
            clientList.remove(index);
            clientList.add(index, clientIdDb);
            RequestContext rcontext = RequestContext.getCurrentInstance();
            rcontext.update("clientTable");

            FacesUtils.addMessage(null, FacesUtils.getI18nValue("edit_rowchanged"), clientToEdit.toString(), FacesMessage.SEVERITY_WARN);
        }
    }
    */
    
    public void cancelModify() {
        FacesUtils.addMessage(null, FacesUtils.getI18nValue("edit_cancelled"), selectedClient.toString(), FacesMessage.SEVERITY_INFO);
    }

    public void validateEmail(FacesContext context, UIComponent component, Object value) {
        String email = ((String) value).toLowerCase();

        Client existingClient = clientService.findByEmail(email);
        PfMember existingPfMember = pfMemberService.findByEmail(email);

        if ((existingClient != null && existingClient.getId() != (Long) component.getAttributes().get("clientID")) || (existingPfMember != null && existingPfMember.getId() != (Long) component.getAttributes().get("clientID"))) {
            ((UIInput) component).setValid(false);
            FacesUtils.addMessage(component.getClientId(context), FacesUtils.getI18nValue("edit_error"), "\""+email+"\" "+FacesUtils.getI18nValue("label_alreadyExists"), FacesMessage.SEVERITY_ERROR);
        }
    }
    
    public void modifySelectedClient(){
        try {
            Client clientUpdated = clientService.updateClient(selectedClient);
            int index = clientList.indexOf(selectedClient);
            clientList.remove(index);
            clientList.add(index, clientUpdated);
            RequestContext rcontext = RequestContext.getCurrentInstance();
            rcontext.update("clientTable");

            FacesUtils.addMessage(null, FacesUtils.getI18nValue("edit_done"), selectedClient.toString(), FacesMessage.SEVERITY_INFO);
        } catch (Exception e) {
            FacesUtils.addMessage(null, FacesUtils.getI18nValue("edit_error"), e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

    public void deleteClient() {
        try {
            clientService.deleteClient(selectedClient);
            clientList.remove(selectedClient);
            FacesUtils.addMessage(null, FacesUtils.getI18nValue("label_deleteDone"), selectedClient.toString(), FacesMessage.SEVERITY_INFO);
        } catch (Exception e) {
            FacesUtils.addMessage(null, FacesUtils.getI18nValue("label_error"), e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }
    
    public String editClient(){
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("client");
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("client", selectedClient);
        return "client?faces-redirect=true";
    }

    public void cancelDeletion() {
        FacesUtils.addMessage(null, FacesUtils.getI18nValue("label_deleteCanceled"), selectedClient.toString(), FacesMessage.SEVERITY_INFO);
    }
    
    public String createNewClient(){
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("client");
        return "client?faces-redirect=true";
    }
}
