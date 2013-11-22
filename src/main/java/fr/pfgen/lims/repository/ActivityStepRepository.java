/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pfgen.lims.repository;

import fr.pfgen.lims.domain.projects.Activity;
import fr.pfgen.lims.domain.projects.ActivityStep;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 *
 * @author eric
 */
public interface ActivityStepRepository extends JpaSpecificationExecutor<ActivityStep>, JpaRepository<ActivityStep, Long>{
    
    public List<ActivityStep> findByActivity(Activity activity);
}
