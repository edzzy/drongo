/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pfgen.lims.service;

import fr.pfgen.lims.domain.projects.Contract;
import fr.pfgen.lims.domain.projects.Project;
import fr.pfgen.lims.repository.ActivityRepository;
import fr.pfgen.lims.repository.ContractRepository;
import fr.pfgen.lims.repository.ProjectRepository;
import fr.pfgen.lims.web.util.FacesUtils;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
    public void deleteProject(Project project) {
        projectRepository.delete(project);
    }
    
    @Override
    public Project findProjectByName(String name) {
        return projectRepository.findByName(name);
    }

    @Override
    public void saveContract(Contract contract) {
        // get the latest internal number in DB.
        Pattern p = Pattern.compile("-?\\d+");
        Integer newIn = 0;
        for (Contract c : findAllContracts()) {
            Matcher m = p.matcher(c.getContractNumber());
            while (m.find()) {
                Integer i = Integer.parseInt(m.group());
                if (i > newIn) {
                    newIn = i;
                }
            }
        }
        
        // condition is always true (for the moment)
        if (contract.getContractNumber()==null || contract.getContractNumber().isEmpty()){
            contract.setContractNumber("Co" + String.format("%04d", newIn + 1));
        }
        contractRepository.save(contract);
    }

    @Override
    public Contract updateContract(Contract contract) {
        if (contract.isSigned()){
            throw new RuntimeException(FacesUtils.getI18nValueInMessages("contract_alreadySigned"));
        }
        return contractRepository.save(contract);
    }

    @Override
    public List<Contract> findAllContracts() {
        return contractRepository.findAll();
    }
}