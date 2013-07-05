/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pfgen.lims.service;

import fr.pfgen.lims.domain.people.ResearchTeam;
import fr.pfgen.lims.domain.people.ResearchUnit;
import java.util.List;
import java.util.Map;

/**
 *
 * @author eric
 */
public interface ResearchTeamService {

    //methodes for Research Teams
    public abstract long countAllResearchTeams();

    public abstract void deleteResearchTeam(ResearchTeam researchTeam);

    public abstract ResearchTeam findResearchTeam(Long id);

    public abstract List<ResearchTeam> findAllResearchTeams();

    public abstract List<ResearchTeam> findResearchTeamEntries(int firstResult, int maxResults);

    public abstract void saveResearchTeam(ResearchTeam researchTeam);

    public abstract ResearchTeam updateResearchTeam(ResearchTeam researchTeam);
    
    public abstract List<ResearchTeam> findByResearchUnit(ResearchUnit researchUnit);
    
    public abstract Map<ResearchUnit, List<ResearchTeam>> getUnits2Teams();
    
    
    //methodes for Research Units
    public abstract long countAllResearchUnits();

    public abstract void deleteResearchUnit(ResearchUnit researchUnit);

    public abstract ResearchUnit findResearchUnit(Long id);

    public abstract List<ResearchUnit> findAllResearchUnits();

    public abstract List<ResearchUnit> findResearchUnitEntries(int firstResult, int maxResults);

    public abstract void saveResearchUnit(ResearchUnit researchUnit);

    public abstract ResearchUnit updateResearchUnit(ResearchUnit researchUnit);
    
    public abstract ResearchUnit findResearchUnitByName(String name);
}
