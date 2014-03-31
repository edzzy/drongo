/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pfgen.lims.repository;

import fr.pfgen.lims.domain.projects.Step;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 *
 * @author eric
 */
public interface StepRepository extends JpaSpecificationExecutor<Step>, JpaRepository<Step, Long>{
    
}
