/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pfgen.lims.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import fr.pfgen.lims.domain.projects.Activity;
import fr.pfgen.lims.domain.projects.Application;
import fr.pfgen.lims.domain.projects.ApplicationType;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
/**
 *
 * @author edouard
 */
@Repository
public interface ActivityRepository extends JpaSpecificationExecutor<Activity>, JpaRepository<Activity, Long> {
    
    public Activity findByApplicationAndType(Application app, ApplicationType type);
}
