/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pfgen.lims.service;

import fr.pfgen.lims.domain.projects.Application;
import fr.pfgen.lims.domain.projects.ApplicationCategory;
import java.util.List;

/**
 *
 * @author eric
 */
public interface ApplicationService {
    
    public abstract void saveApplication(Application application);
    
    public abstract void saveApplicationCategory(ApplicationCategory applicationCategory);
    
    public abstract List<ApplicationCategory> findAllApplicationCategories();
    
    public abstract List<Application> findAllApplications();
    
    public abstract boolean expActivityExistsForApplication(Application app);
    
    public abstract boolean anaActivityExistsForApplication(Application app);
}
