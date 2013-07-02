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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author eric
 */
@Component
@Scope("request")
@ManagedBean
public class ResearchTeamCreateBean {

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
        return "researchUnitCreate?faces-redirect=true";
    }

    public String saveNewTeam() {

        for (ResearchTeam team : unit2teams.get(newResearchTeam.getResearchUnit())) {
            if (team.getName().equalsIgnoreCase(newResearchTeam.getName())) {
                FacesUtils.addMessage(null, FacesUtils.getI18nValue("label_error"), "\"" + newResearchTeam.getName() + "\" " + FacesUtils.getI18nValue("label_alreadyExistsInUnit"), FacesMessage.SEVERITY_ERROR);
                return null;
            }
        }

        try {
            researchTeamService.saveResearchTeam(newResearchTeam);
            Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
            Client client = (Client) sessionMap.get("client");

            if (client != null) {
                client.setResearchTeam(newResearchTeam);
                sessionMap.put("wizStep", "typeTab");
                return "client?faces-redirect=true";
            } else {
                //TODO redirect to company list when created !!!!
                return null;
            }
        } catch (Exception e) {
            FacesUtils.addMessage(null, FacesUtils.getI18nValue("label_error"), e.getMessage(), FacesMessage.SEVERITY_ERROR);
            return null;
        }
    }

    public String cancelTeamCreation() {
        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        Client client = (Client) sessionMap.get("client");

        if (client != null) {
            sessionMap.put("wizStep", "typeTab");
            return "client?faces-redirect=true";
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
