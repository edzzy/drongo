/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pfgen.lims.service;

import fr.pfgen.lims.domain.projects.Activity;
import fr.pfgen.lims.domain.projects.ActivityStep;
import fr.pfgen.lims.domain.projects.Application;
import fr.pfgen.lims.domain.projects.ApplicationCategory;
import fr.pfgen.lims.domain.projects.Step;
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
    
    public abstract List<Activity> findActivitiesForApplication(Application app);
    
    public abstract List<ActivityStep> findActivityStepsForActivity(Activity act);
    
    public abstract Application findApplicationByCode(String code);
    
    public abstract Application findApplicationByName(String name);
    
    public abstract ApplicationCategory findApplicationCategoryByName(String name);
    
    public abstract List<Step> findAllSteps();
    
    public abstract void saveStep(Step step);
}
