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
public class CreateClientBean implements Serializable {

    /**
     * Creates a new instance of CreateClientBean
     */
    private static final String internUnitName = "UMR 1087";
    private Client newClient;
    private List<ClientType> clientTypeList;
    private boolean isInterne = false;
    private boolean isAcademique = false;
    private boolean isPrive = false;
    private Map<ResearchUnit, List<ResearchTeam>> unit2teams;
    private List<ResearchUnit> unitList = new ArrayList<>();
    private List<ResearchTeam> teamList = new ArrayList<>();
    private ResearchUnit selectedUnit;
    private String clientid;
    
    @Autowired
    private ClientService clientService;
    @Autowired
    private ClientTypeService clientTypeService;
    @Autowired
    private ResearchTeamService researchTeamService;
    @Autowired
    private PfMemberService pfMemberService;
    

    @PostConstruct
    public void init() {

        clientTypeList = clientTypeService.findAllClientTypes();
        unit2teams = researchTeamService.getUnits2Teams();
        for (ResearchUnit unit : unit2teams.keySet()) {
            if (!unit.getName().equals(internUnitName)) {
                unitList.add(unit);
            }
        }
    }

    public void initClient() {
        if (!FacesContext.getCurrentInstance().isPostback()) {
            if (clientid == null || clientid.isEmpty()) {
                newClient = new Client();
            } else {
                newClient = clientService.findClient(Long.parseLong(clientid));
                if (newClient.getResearchTeam()!=null){
                    ResearchUnit unit = newClient.getResearchTeam().getResearchUnit();
                    selectedUnit = unit;
                    switchAccordingToType(newClient.getType());
                    switchAccordingToUnit(unit);
                }
            }
        }
    }

    public String getClientid() {
        return clientid;
    }

    public void setClientid(String clientid) {
        this.clientid = clientid;
    }

    public List<ResearchUnit> getUnitList() {
        return unitList;
    }

    public void setUnitList(List<ResearchUnit> unitList) {
        this.unitList = unitList;
    }

    public ResearchUnit getSelectedUnit() {
        return selectedUnit;
    }

    public void setSelectedUnit(ResearchUnit selectedUnit) {
        this.selectedUnit = selectedUnit;
    }

    public List<ResearchTeam> getTeamList() {
        return teamList;
    }

    public void setTeamList(List<ResearchTeam> teamList) {
        this.teamList = teamList;
    }

    public Map<ResearchUnit, List<ResearchTeam>> getUnit2teams() {
        return unit2teams;
    }

    public void setUnit2teams(Map<ResearchUnit, List<ResearchTeam>> unit2teams) {
        this.unit2teams = unit2teams;
    }

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

    public List<ClientType> getClientTypeList() {
        return clientTypeList;
    }

    public void setClientTypeList(List<ClientType> clientTypeList) {
        this.clientTypeList = clientTypeList;
    }

    public ClientTypeService getClientTypeService() {
        return clientTypeService;
    }

    public void setClientTypeService(ClientTypeService clientTypeService) {
        this.clientTypeService = clientTypeService;
    }

    public Client getNewClient() {
        return newClient;
    }

    public void setNewClient(Client newClient) {
        this.newClient = newClient;
    }

    public String saveNewClient() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            if (newClient.getId()==null){
                clientService.saveClient(newClient);
                FacesUtils.addMessage(null, FacesUtils.getI18nValue("newClient_added"), newClient.toString(), FacesMessage.SEVERITY_INFO);

            }else{
                clientService.updateClient(newClient);
                FacesUtils.addMessage(null, FacesUtils.getI18nValue("edit_done"), newClient.toString(), FacesMessage.SEVERITY_INFO);
            }
            context.getExternalContext().getFlash().setKeepMessages(true);
            return "clients?faces-redirect=true";
        } catch (Exception e) {
            FacesUtils.addMessage(null, FacesUtils.getI18nValue("label_error"), e.getMessage(), FacesMessage.SEVERITY_ERROR);
            return null;
        }
    }

    public void validateEmail(FacesContext context, UIComponent component, Object value) {
        String email = ((String) value).toLowerCase();

        Client existingClient = clientService.findByEmail(email);
        PfMember existingPfMember = pfMemberService.findByEmail(email);

        if ((existingClient != null && existingClient.getId() != newClient.getId()) || (existingPfMember != null && existingPfMember.getId() != newClient.getId())) {
            ((UIInput) component).setValid(false);
            FacesUtils.addMessage(component.getClientId(context), FacesUtils.getI18nValue("edit_error"), "\""+email+"\" "+FacesUtils.getI18nValue("label_alreadyExists"), FacesMessage.SEVERITY_ERROR);
        }
    }

    public void onTypeChanged(AjaxBehaviorEvent event) {
        ClientType type = (ClientType) ((UIOutput) event.getSource()).getValue();
        switchAccordingToType(type);
    }

    private void switchAccordingToType(ClientType type) {
        if (type != null) {
            switch (type.toString()) {
                case "interne":
                    isInterne = true;
                    isAcademique = false;
                    isPrive = false;
                    teamList.clear();
                    for (ResearchUnit unit : unit2teams.keySet()) {
                        if (unit.getName().equals(internUnitName)) {
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
    }

    public void onUnitChanged(AjaxBehaviorEvent event) {
        ResearchUnit unit = (ResearchUnit) ((UIOutput) event.getSource()).getValue();
        switchAccordingToUnit(unit);
    }
    
    private void switchAccordingToUnit(ResearchUnit unit){
        if (unit == null) {
            teamList.clear();
            for (ResearchUnit u : unitList) {
                teamList.addAll(unit2teams.get(u));
            }
        } else {
            teamList.clear();
            teamList.addAll(unit2teams.get(unit));
        }
    }
}
