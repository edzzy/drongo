/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pfgen.lims.repository;

import fr.pfgen.lims.domain.people.ResearchTeam;
import fr.pfgen.lims.domain.people.ResearchUnit;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 *
 * @author eric
 */
@Repository
public interface ResearchTeamRepository extends JpaSpecificationExecutor<ResearchTeam>, JpaRepository<ResearchTeam, Long>{
    public List<ResearchTeam> findByResearchUnit(ResearchUnit researchUnit);
}
