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
import fr.pfgen.lims.repository.ActivityRepository;
import fr.pfgen.lims.repository.ActivityStepRepository;
import fr.pfgen.lims.repository.ApplicationCategoryRepository;
import fr.pfgen.lims.repository.ApplicationRepository;
import fr.pfgen.lims.repository.StepRepository;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
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
public class ApplicationServiceImpl implements ApplicationService {

    @Autowired
    ApplicationRepository applicationRepository;
    @Autowired
    ApplicationCategoryRepository applicationCategoryRepository;
    @Autowired
    ActivityRepository activityRepository;
    @Autowired
    ActivityStepRepository activityStepRepository;
    @Autowired
    StepRepository stepRepository;
    
    @Override
    public void saveApplication(Application application) {
        applicationRepository.save(application);
    }

    @Override
    public void saveApplicationCategory(ApplicationCategory applicationCategory) {
        applicationCategoryRepository.save(applicationCategory);
    }

    @Override
    public List<ApplicationCategory> findAllApplicationCategories() {
        return applicationCategoryRepository.findAll();
    }

    @Override
    public List<Application> findAllApplications() {
        return applicationRepository.findAll();
    }

    @Override
    public List<Activity> findActivitiesForApplication(Application app) {
        return activityRepository.findByApplication(app);
    }

    @Override
    public List<ActivityStep> findActivityStepsForActivity(Activity act) {
        List<ActivityStep> steps = activityStepRepository.findByActivity(act);
        
        LinkedList<ActivityStep> orderedSteps = new LinkedList<>();
        
        for (ActivityStep step : steps) {
            if (step.getParentActivityStep() == null){
                orderedSteps.addFirst(step);
            }else{
                if (orderedSteps.contains(step.getParentActivityStep())){
                    orderedSteps.add(orderedSteps.indexOf(step.getParentActivityStep()) + 1, step);
                }else{
                    orderedSteps.addLast(step);
                }
            }
        }
        
        return orderedSteps;
    }

    @Override
    public Application findApplicationByCode(String code) {
        return applicationRepository.findByCode(code);
    }

    @Override
    public Application findApplicationByName(String name) {
        return applicationRepository.findByName(name);
    }

    @Override
    public ApplicationCategory findApplicationCategoryByName(String name) {
        return applicationCategoryRepository.findByName(name);
    }

    @Override
    public List<Step> findAllSteps() {
        List<Step> stepList = stepRepository.findAll();
        Collections.sort(stepList, new Comparator<Step>() {

            @Override
            public int compare(Step o1, Step o2) {
                return o1.getName().compareToIgnoreCase(o2.getName());
            }
        });
        return stepList;
    }

    @Override
    public void saveStep(Step step) {
        stepRepository.save(step);
    }
    
    @Override
    public void saveActivity(Activity activity){
        activityRepository.save(activity);
    }
}
