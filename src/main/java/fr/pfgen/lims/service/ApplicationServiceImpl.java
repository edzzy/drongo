/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pfgen.lims.service;

import fr.pfgen.lims.domain.projects.Application;
import fr.pfgen.lims.domain.projects.ApplicationCategory;
import fr.pfgen.lims.domain.projects.ApplicationType;
import fr.pfgen.lims.repository.ApplicationCategoryRepository;
import fr.pfgen.lims.repository.ApplicationRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author eric
 */
@Service
@Transactional
public class ApplicationServiceImpl implements ApplicationService{
    
    @Autowired
    ApplicationRepository applicationRepository;
    
    @Autowired
    ApplicationCategoryRepository applicationCategoryRepository;

    @Override
    public void saveApplication(Application application) {
        applicationRepository.save(application);
    }

    @Override
    public void saveApplicationCategory(ApplicationCategory applicationCategory) {
        applicationCategoryRepository.save(applicationCategory);
    }

    @Override
    public List<Application> findAllBioinformaticsApplications() {
        return applicationRepository.findByType(ApplicationType.BIOINFORMATICS);
    }

    @Override
    public List<Application> findAllExperimentalApplications() {
        return applicationRepository.findByType(ApplicationType.EXPERIMENTAL);
    }

    @Override
    public List<ApplicationCategory> findAllApplicationCategories() {
        return applicationCategoryRepository.findAll();
    }
}
