/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pfgen.lims.service;

import fr.pfgen.lims.domain.people.Client;
import fr.pfgen.lims.domain.projects.Project;
import fr.pfgen.lims.repository.ActivityRepository;
import fr.pfgen.lims.repository.ContractRepository;
import fr.pfgen.lims.repository.ProjectRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author edouard
 */

@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {
    
    @Autowired
    ProjectRepository projectRepository;
    
    @Autowired
    ContractRepository contractRepository;
    
    @Autowired
    ActivityRepository activityRepository;
    
    
    @Override
    public void saveProject(Project project){
        projectRepository.save(project);
    }

    @Override
    public List<Project> findAllProjects() {
        return projectRepository.findAll();
    }

    @Override
    public Project updateProject(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public Project findProject(Long id) {
        return projectRepository.findOne(id);
    }

    @Override
    public Project findProjectByNameAndClient(String name, Client client) {
        return projectRepository.findOneByNameAndMainClient(name, client);
    }
}