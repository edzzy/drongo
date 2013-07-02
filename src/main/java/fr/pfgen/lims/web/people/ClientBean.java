/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pfgen.lims.web.people;

import fr.pfgen.lims.domain.people.Client;
import fr.pfgen.lims.domain.people.ClientType;
import fr.pfgen.lims.domain.people.Company;
import fr.pfgen.lims.domain.people.PfMember;
import fr.pfgen.lims.domain.people.ResearchTeam;
import fr.pfgen.lims.domain.people.ResearchUnit;
import fr.pfgen.lims.service.ClientService;
import fr.pfgen.lims.service.ClientTypeService;
import fr.pfgen.lims.service.CompanyService;
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
public class ClientBean implements Serializable {

    /**
     * Creates a new instance of CreateClientBean
     */
    private static final String internUnitName = "UMR 1087";
    private Client client;
    private List<ClientType> clientTypeList;
    private boolean isInterne = false;
    private boolean isAcademique = false;
    private boolean isPrive = false;
    private Map<ResearchUnit, List<ResearchTeam>> unit2teams;
    private List<ResearchUnit> unitList = new ArrayList<>();
    private List<ResearchTeam> teamList = new ArrayList<>();
    private ResearchUnit selectedUnit;
    private List<Company> companyList;
    @Autowired
    private ClientService clientService;
    @Autowired
    private ClientTypeService clientTypeService;
    @Autowired
    private ResearchTeamService researchTeamService;
    @Autowired
    private PfMemberService pfMemberService;
    @Autowired
    private CompanyService companyService;
    private String wizStep;

    @PostConstruct
    public void init() {

        clientTypeList = clientTypeService.findAllClientTypes();
        unit2teams = researchTeamService.getUnits2Teams();
        for (ResearchUnit unit : unit2teams.keySet()) {
            if (!unit.getName().equals(internUnitName)) {
                unitList.add(unit);
            }
        }

        companyList = companyService.findAllCompanies();
    }

    public void initClient() {
        if (!FacesContext.getCurrentInstance().isPostback()) {
            Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

            client = (Client) sessionMap.get("client");
            //sessionMap.remove("client");
            wizStep = (String) sessionMap.get("wizStep");
            sessionMap.remove("wizStep");
            if (wizStep == null) {
                wizStep = "personalTab";
            }

            if (client == null) {
                client = new Client();
                sessionMap.put("client", client);
            } else {
                switchAccordingToType(client.getType());
                if (client.getResearchTeam() != null) {
                    ResearchUnit unit = client.getResearchTeam().getResearchUnit();
                    selectedUnit = unit;
                    switchAccordingToUnit(unit);
                }
            }
        }
    }

    public String getWizStep() {
        return wizStep;
    }

    public void setWizStep(String wizStep) {
        this.wizStep = wizStep;
    }

    public List<Company> getCompanyList() {
        return companyList;
    }

    public void setCompanyList(List<Company> companyList) {
        this.companyList = companyList;
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

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String saveClient() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            if (client.getId() == null) {
                clientService.saveClient(client);
                FacesUtils.addMessage(null, FacesUtils.getI18nValue("newClient_added"), client.toString(), FacesMessage.SEVERITY_INFO);

            } else {
                clientService.updateClient(client);
                FacesUtils.addMessage(null, FacesUtils.getI18nValue("edit_done"), client.toString(), FacesMessage.SEVERITY_INFO);
            }
            context.getExternalContext().getFlash().setKeepMessages(true);
            return "clients?faces-redirect=true";
        } catch (Exception e) {
            FacesUtils.addMessage(null, FacesUtils.getI18nValue("label_error"), e.getMessage(), FacesMessage.SEVERITY_ERROR);
            return null;
        }
    }

    public String cancelClient() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (client.getId() == null) {
            FacesUtils.addMessage(null, FacesUtils.getI18nValue("label_createCanceled"), null, FacesMessage.SEVERITY_INFO);
        } else {
            FacesUtils.addMessage(null, FacesUtils.getI18nValue("edit_cancelled"), client.toString(), FacesMessage.SEVERITY_INFO);
        }
        context.getExternalContext().getFlash().setKeepMessages(true);
        return "clients?faces-redirect=true";
    }

    public void validateEmail(FacesContext context, UIComponent component, Object value) {
        String email = ((String) value).toLowerCase();

        Client existingClient = clientService.findByEmail(email);
        PfMember existingPfMember = pfMemberService.findByEmail(email);

        if ((existingClient != null && existingClient.getId() != client.getId()) || (existingPfMember != null && existingPfMember.getId() != client.getId())) {
            ((UIInput) component).setValid(false);
            FacesUtils.addMessage(component.getClientId(context), FacesUtils.getI18nValue("edit_error"), "\"" + email + "\" " + FacesUtils.getI18nValue("label_alreadyExists"), FacesMessage.SEVERITY_ERROR);
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

    private void switchAccordingToUnit(ResearchUnit unit) {
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

    public String createNewCompany() {
        return "companyCreate?faces-redirect=true";
    }
    
    public String createNewResearchTeam(){
        return "researchTeamCreate?faces-redirect=true";
    }

    public String getSaveOrEditLabel() {
        if (client.getId() != null) {
            return FacesUtils.getI18nValue("label_edit");
        } else {
            return FacesUtils.getI18nValue("label_save");
        }
    }

    public String getPlusOrPen() {
        if (client.getId() != null) {
            return "ui-icon-pencil";
        } else {
            return "ui-icon-disk";
        }
    }
}
