/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pfgen.lims.service;

import fr.pfgen.lims.domain.ResearchTeam;
import fr.pfgen.lims.domain.ResearchUnit;
import java.util.List;
import java.util.Map;

/**
 *
 * @author eric
 */
public interface ResearchTeamService {

    public abstract long countAllResearchTeams();

    public abstract void deleteResearchTeam(ResearchTeam researchTeam);

    public abstract ResearchTeam findResearchTeam(Long id);

    public abstract List<ResearchTeam> findAllResearchTeams();

    public abstract List<ResearchTeam> findResearchTeamEntries(int firstResult, int maxResults);

    public abstract void saveResearchTeam(ResearchTeam researchTeam);

    public abstract ResearchTeam updateResearchTeam(ResearchTeam researchTeam);
    
    public abstract List<ResearchTeam> findByResearchUnit(ResearchUnit researchUnit);
    
    public abstract Map<ResearchUnit, List<ResearchTeam>> getUnits2Teams();
}
