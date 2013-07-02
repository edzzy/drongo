/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pfgen.lims.repository;

import fr.pfgen.lims.domain.projects.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 *
 * @author edouard
 */
@Repository
public interface ProjectRepository extends JpaSpecificationExecutor<Project>, JpaRepository<Project, Long> {
    
    public Project findOneByName(String value);
   
    
}
