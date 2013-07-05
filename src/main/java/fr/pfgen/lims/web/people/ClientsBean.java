/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pfgen.lims.web.people;

import fr.pfgen.lims.domain.people.Client;
import fr.pfgen.lims.domain.people.ClientType;
import fr.pfgen.lims.domain.people.ResearchTeam;
import fr.pfgen.lims.domain.people.ResearchUnit;
import fr.pfgen.lims.service.ClientService;
import fr.pfgen.lims.service.ClientTypeService;
import fr.pfgen.lims.service.ResearchTeamService;
import fr.pfgen.lims.web.util.FacesUtils;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
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
    private ResearchTeamService researchTeamService;
    
    private List<Client> clientList;
    private List<ClientType> clientTypeList;
    private List<Client> filteredClients;
    private Client selectedClient;
    private Map<ResearchUnit, List<ResearchTeam>> unit2teams;
    private SelectItem[] clientTypeOptions;
    
    @PostConstruct
    public void init() {
        clientList = clientService.findAllActiveClients();
        clientTypeList = clientTypeService.findAllClientTypes();
        unit2teams = researchTeamService.getUnits2Teams();
        clientTypeOptions = createFilterOptions(clientTypeList);
    }

    public SelectItem[] getClientTypeOptions() {
        return clientTypeOptions;
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

    public void cancelModify() {
        FacesUtils.addMessage(null, FacesUtils.getI18nValue("edit_cancelled"), selectedClient.toString(), FacesMessage.SEVERITY_INFO);
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
    
    private SelectItem[] createFilterOptions(List<ClientType> data)  {  
        SelectItem[] options = new SelectItem[data.size() + 1];  
  
        options[0] = new SelectItem("", FacesUtils.getI18nValue("label_select"));  
        for(int i = 0; i < data.size(); i++) { 
            options[i + 1] = new SelectItem(data.get(i).getName(), data.get(i).getName());  
        }  
  
        return options;  
    }  
}
