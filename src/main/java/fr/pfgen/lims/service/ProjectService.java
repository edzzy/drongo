/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pfgen.lims.service;

import fr.pfgen.lims.domain.projects.Contract;
import fr.pfgen.lims.domain.projects.Project;
import java.util.List;

/**
 *
 * @author edouard
 */
public interface ProjectService {

    public abstract void saveProject(Project project);  
    public abstract List<Project> findAllProjects();
    public abstract Project updateProject(Project project);
    public abstract Project findProject(Long id);
    public abstract void deleteProject(Project project);
    public abstract Project findProjectByName(String name);
    
    public abstract void saveContract(Contract contract);
    public abstract Contract updateContract(Contract contract);
    public abstract List<Contract> findAllContracts();

}

