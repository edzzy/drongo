/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pfgen.lims.repository;

import fr.pfgen.lims.domain.projects.Application;
import fr.pfgen.lims.domain.projects.ApplicationType;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 *
 * @author eric
 */
@Repository
public interface ApplicationRepository extends JpaSpecificationExecutor<Application>, JpaRepository<Application, Long>{
    
    public List<Application> findByType(ApplicationType type); 
}
