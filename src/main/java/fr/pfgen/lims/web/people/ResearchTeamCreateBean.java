/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pfgen.lims.web.people;

import fr.pfgen.lims.domain.people.Client;
import fr.pfgen.lims.domain.people.ResearchTeam;
import fr.pfgen.lims.domain.people.ResearchUnit;
import fr.pfgen.lims.service.ResearchTeamService;
import fr.pfgen.lims.web.util.FacesUtils;
import fr.pfgen.lims.web.util.flows.FlowType;
import fr.pfgen.lims.web.util.flows.ResearchTeamFlow;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author eric
 */
@Component
@Scope("request")
public class ResearchTeamCreateBean extends ResearchTeamFlow{

    @Autowired
    private ResearchTeamService researchTeamService;
    private Map<ResearchUnit, List<ResearchTeam>> unit2teams;
    private List<ResearchUnit> unitList = new ArrayList<>();
    private ResearchTeam newResearchTeam;

    @PostConstruct
    public void init() {
        newResearchTeam = new ResearchTeam();
        unit2teams = researchTeamService.getUnits2Teams();
        unitList.addAll(unit2teams.keySet());
    }

    public String createNewResearchUnit() {
        return enterFlow(FlowType.RESEARCHUNIT);
    }

    public String saveNewTeam() {

        for (ResearchTeam team : unit2teams.get(newResearchTeam.getResearchUnit())) {
            if (team.getName().equalsIgnoreCase(newResearchTeam.getName())) {
                FacesUtils.addMessage(null, FacesUtils.getI18nValueInMessages("label_error"), "\"" + newResearchTeam.getName() + "\" " + FacesUtils.getI18nValueInMessages("label_alreadyExistsInUnit"), FacesMessage.SEVERITY_ERROR);
                return null;
            }
        }

        try {
            researchTeamService.saveResearchTeam(newResearchTeam);

            Client client = ((Client) FacesUtils.getObjectInSessionMap("client"));
            
            if (client != null) {
                client.setResearchTeam(newResearchTeam);
                FacesUtils.putObjectInSessionMap("wizStep", "typeTab");
                return endFlowAndRedirect();
            } else {
                //TODO redirect to company list when created !!!!
                return null;
            }
        } catch (Exception e) {
            FacesUtils.addMessage(null, FacesUtils.getI18nValueInMessages("label_error"), e.getMessage(), FacesMessage.SEVERITY_ERROR);
            return null;
        }
    }

    public String cancelTeamCreation() {
        Client client = ((Client) FacesUtils.getObjectInSessionMap("client"));

        if (client != null) {
            FacesUtils.putObjectInSessionMap("wizStep", "typeTab");
            return endFlowAndRedirect();
        }else{
            //TODO redirect to company list when created !!!!
            return null;
        }
    }

    public ResearchTeam getNewResearchTeam() {
        return newResearchTeam;
    }

    public void setNewResearchTeam(ResearchTeam newResearchTeam) {
        this.newResearchTeam = newResearchTeam;
    }

    public List<ResearchUnit> getUnitList() {
        return unitList;
    }

    public void setUnitList(List<ResearchUnit> unitList) {
        this.unitList = unitList;
    }

    public Map<ResearchUnit, List<ResearchTeam>> getUnit2teams() {
        return unit2teams;
    }

    public void setUnit2teams(Map<ResearchUnit, List<ResearchTeam>> unit2teams) {
        this.unit2teams = unit2teams;
    }
}
