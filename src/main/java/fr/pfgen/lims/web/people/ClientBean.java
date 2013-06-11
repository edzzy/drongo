/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pfgen.lims.web.people;

import fr.pfgen.lims.domain.Client;
import fr.pfgen.lims.domain.ClientType;
import fr.pfgen.lims.domain.PfMember;
import fr.pfgen.lims.domain.ResearchTeam;
import fr.pfgen.lims.domain.ResearchUnit;
import fr.pfgen.lims.service.ClientService;
import fr.pfgen.lims.service.ClientTypeService;
import fr.pfgen.lims.service.PfMemberService;
import fr.pfgen.lims.service.ResearchTeamService;
import fr.pfgen.lims.web.util.FacesUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.component.UIOutput;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
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
public class ClientBean implements Serializable {

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
        
        /*
         * export to independent bean
         */
        /*
        unit2teams = researchTeamService.getUnits2Teams();
        for (ResearchUnit unit : unit2teams.keySet()) {
            if (!unit.getName().equals("UMR 1087")){
                unitList.add(unit);
            }
        }
        * */
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
    /*
    public void modifyAddress() {
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
    */
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

    public void cancelDeletion() {
        FacesUtils.addMessage(null, FacesUtils.getI18nValue("label_deleteCanceled"), selectedClient.toString(), FacesMessage.SEVERITY_INFO);
    }
    
    
    
    
    /*
     * From here, should be exporte to composite component
     */
    /*
    private boolean isInterne = false;
    private boolean isAcademique = false;
    private boolean isPrive = false;
    private Map<ResearchUnit, List<ResearchTeam>> unit2teams;
    private List<ResearchUnit> unitList = new ArrayList<>();
    private List<ResearchTeam> teamList = new ArrayList<>();
    private ResearchUnit selectedUnit;
    
    @Autowired
    private ResearchTeamService researchTeamService;

    public boolean isInterne() {
        return isInterne;
    }

    public void setIsInterne(boolean isInterne) {
        this.isInterne = isInterne;
    }

    public boolean isAcademique() {
        return isAcademique;
    }

    public void setIsAcademique(boolean isAcademique) {
        this.isAcademique = isAcademique;
    }

    public boolean isPrive() {
        return isPrive;
    }

    public void setIsPrive(boolean isPrive) {
        this.isPrive = isPrive;
    }

    public Map<ResearchUnit, List<ResearchTeam>> getUnit2teams() {
        return unit2teams;
    }

    public void setUnit2teams(Map<ResearchUnit, List<ResearchTeam>> unit2teams) {
        this.unit2teams = unit2teams;
    }

    public List<ResearchUnit> getUnitList() {
        return unitList;
    }

    public void setUnitList(List<ResearchUnit> unitList) {
        this.unitList = unitList;
    }

    public List<ResearchTeam> getTeamList() {
        return teamList;
    }

    public void setTeamList(List<ResearchTeam> teamList) {
        this.teamList = teamList;
    }

    public ResearchUnit getSelectedUnit() {
        return selectedUnit;
    }

    public void setSelectedUnit(ResearchUnit selectedUnit) {
        this.selectedUnit = selectedUnit;
    }
    
    public void onTypeChanged(AjaxBehaviorEvent event) {
        ClientType type = (ClientType) ((UIOutput) event.getSource()).getValue();
        switch (type.toString()) {
            case "interne":
                isInterne = true;
                isAcademique = false;
                isPrive = false;
                teamList.clear();
                for (ResearchUnit unit : unit2teams.keySet()) {
                    if (unit.getName().equals("UMR 1087")){
                        teamList.addAll(unit2teams.get(unit));
                    }
                }
                selectedUnit = null;
                break;
            case "académique":
                isInterne = false;
                isAcademique = true;
                isPrive = false;
                teamList.clear();
                for (ResearchUnit unit : unitList) {
                    teamList.addAll(unit2teams.get(unit));
                }
                break;
            case "privé":
                isInterne = false;
                isAcademique = false;
                isPrive = true;
                selectedUnit = null;
                break;
            default:
                isInterne = false;
                isAcademique = false;
                isPrive = false;
                selectedUnit = null;
                break;
        }
    }
    
    public void onUnitChanged(AjaxBehaviorEvent event){
        teamList.clear();
        teamList.addAll(unit2teams.get(selectedUnit));
    }
    */
}
