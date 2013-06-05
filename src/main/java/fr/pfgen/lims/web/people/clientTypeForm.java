/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pfgen.lims.web.people;

import fr.pfgen.lims.domain.Client;
import fr.pfgen.lims.domain.ClientType;
import fr.pfgen.lims.domain.ResearchTeam;
import fr.pfgen.lims.domain.ResearchUnit;
import fr.pfgen.lims.service.ClientTypeService;
import fr.pfgen.lims.service.ResearchTeamService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.component.FacesComponent;
import javax.faces.component.UINamingContainer;
import javax.faces.component.UIOutput;
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
public class clientTypeForm implements Serializable {

    private static final String internUnitName = "UMR 1087";
    private List<ClientType> clientTypeList;
    private boolean isInterne = false;
    private boolean isAcademique = false;
    private boolean isPrive = false;
    private Map<ResearchUnit, List<ResearchTeam>> unit2teams;
    private List<ResearchUnit> unitList = new ArrayList<>();
    private List<ResearchTeam> teamList = new ArrayList<>();
    private Client client;
    private ResearchUnit selectedUnit;
    @Autowired
    private ClientTypeService clientTypeService;
    @Autowired
    private ResearchTeamService researchTeamService;

    @PostConstruct
    public void init() {
        clientTypeList = clientTypeService.findAllClientTypes();
        unit2teams = researchTeamService.getUnits2Teams();
        for (ResearchUnit unit : unit2teams.keySet()) {
            if (!unit.getName().equals(internUnitName)) {
                unitList.add(unit);
            }
        }

        //teamList = unit2teams.get(client.getResearchTeam().getResearchUnit());

    }

    public ResearchUnit getSelectedUnit() {
        return selectedUnit;
    }

    public void setSelectedUnit(ResearchUnit selectedUnit) {
        this.selectedUnit = selectedUnit;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<ClientType> getClientTypeList() {
        return clientTypeList;
    }

    public void setClientTypeList(List<ClientType> clientTypeList) {
        this.clientTypeList = clientTypeList;
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

    public void onTypeChanged(AjaxBehaviorEvent event) {
        ClientType type = (ClientType) ((UIOutput) event.getSource()).getValue();
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

    public void onUnitChanged(AjaxBehaviorEvent event) {
        ResearchUnit unit = (ResearchUnit) ((UIOutput) event.getSource()).getValue();
        if (unit == null) {

            teamList.clear();
            //for (ResearchUnit u : unitList) {
            //    teamList.addAll(unit2teams.get(u));
            //}
        } else {
            teamList.clear();
            teamList.addAll(unit2teams.get(unit));
        }
    }

    public void onPanelClose(AjaxBehaviorEvent event) {
        isInterne = false;
        isAcademique = false;
        isPrive = false;
        selectedUnit = null;
        teamList.clear();
    }
}
