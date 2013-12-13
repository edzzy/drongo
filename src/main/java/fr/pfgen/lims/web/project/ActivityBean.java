/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pfgen.lims.web.project;

import fr.pfgen.lims.domain.projects.Activity;
import fr.pfgen.lims.domain.projects.ActivityType;
import fr.pfgen.lims.domain.projects.Application;
import fr.pfgen.lims.domain.projects.Step;
import fr.pfgen.lims.service.ApplicationService;
import fr.pfgen.lims.web.util.FacesUtils;
import fr.pfgen.lims.web.util.flows.ActivityFlow;
import fr.pfgen.lims.web.util.flows.FlowType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.component.UIOutput;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;
import org.primefaces.model.DualListModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author eric
 */
@Component
@Scope("view")
public class ActivityBean extends ActivityFlow implements Serializable {

    @Autowired
    ApplicationService applicationService;
    
    private Application application;
    private List<ActivityType> availableTypes;
    private ActivityType selectedType;
    private List<Step> stepList;
    private DualListModel<Step> stepDualListModel;
    private List<Step> stepSource = new ArrayList<>();
    private List<Step> stepTarget = new ArrayList<>();
   
    public void initActivities() {
        if (!FacesContext.getCurrentInstance().isPostback()) {
            application = (Application) FacesUtils.getObjectInSessionMap("application");
            selectedType = (ActivityType) FacesUtils.getObjectInSessionMap("activityType");
            FacesUtils.removeObjectFromSessionMap("activityType");
        }
    }

    @PostConstruct
    public void init() {
        availableTypes = findAvailableTypes();
        stepList = applicationService.findAllSteps();
        stepSource.addAll(stepList);
        stepDualListModel = new DualListModel<>(stepSource, stepTarget);
    }
    
    public void onTypeChanged(AjaxBehaviorEvent event){
        ActivityType type = (ActivityType) ((UIOutput) event.getSource()).getValue();
        
        
    }
    
    public String createNewStep(){
        FacesUtils.putObjectInSessionMap("activityType", selectedType);
        return enterFlow(FlowType.STEP);
    }

    public DualListModel<Step> getStepDualListModel() {
        return stepDualListModel;
    }

    public void setStepDualListModel(DualListModel<Step> stepDualListModel) {
        this.stepDualListModel = stepDualListModel;
    }

    public List<Step> getStepSource() {
        return stepSource;
    }

    public void setStepSource(List<Step> stepSource) {
        this.stepSource = stepSource;
    }

    public List<Step> getStepTarget() {
        return stepTarget;
    }

    public void setStepTarget(List<Step> stepTarget) {
        this.stepTarget = stepTarget;
    }

    public List<Step> getStepList() {
        return stepList;
    }

    public void setStepList(List<Step> stepList) {
        this.stepList = stepList;
    }
    
    public ActivityType getSelectedType() {
        return selectedType;
    }

    public void setSelectedType(ActivityType selectedType) {
        this.selectedType = selectedType;
    }

    public List<ActivityType> getAvailableTypes() {
        return availableTypes;
    }

    public void setAvailableTypes(List<ActivityType> availableTypes) {
        this.availableTypes = availableTypes;
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    private List<ActivityType> findAvailableTypes(){
        List<Activity> activityList = applicationService.findActivitiesForApplication(application);
        List<ActivityType> aT = new ArrayList<>();
        for (ActivityType type: ActivityType.values()) {
            boolean found = false;
            for (Activity activity : activityList) {
                if (activity.getType().equals(type)){
                    found = true;
                }
            }
            if (!found) aT.add(type);
        }
        return aT;
    }
}
