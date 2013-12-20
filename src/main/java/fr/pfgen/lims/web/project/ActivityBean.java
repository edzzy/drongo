/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pfgen.lims.web.project;

import fr.pfgen.lims.domain.people.PfMember;
import fr.pfgen.lims.domain.projects.Activity;
import fr.pfgen.lims.domain.projects.ActivityParams;
import fr.pfgen.lims.domain.projects.ActivityStep;
import fr.pfgen.lims.domain.projects.ActivityType;
import fr.pfgen.lims.domain.projects.Application;
import fr.pfgen.lims.domain.projects.ResultsSentByType;
import fr.pfgen.lims.domain.projects.SampleType;
import fr.pfgen.lims.domain.projects.Step;
import fr.pfgen.lims.domain.util.TimeType;
import fr.pfgen.lims.domain.util.TimeUtil;
import fr.pfgen.lims.service.ApplicationService;
import fr.pfgen.lims.service.PfMemberService;
import fr.pfgen.lims.web.util.FacesUtils;
import fr.pfgen.lims.web.util.flows.ActivityFlow;
import fr.pfgen.lims.web.util.flows.FlowType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
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
    
    @Autowired
    PfMemberService pfMemberService;
    
    private Application application;
    private List<ActivityType> availableTypes;
    private ActivityType selectedType;
    private List<Step> stepList;
    private DualListModel<Step> stepDualListModel;
    private List<Step> stepSource = new ArrayList<>();
    private List<Step> stepTarget = new ArrayList<>();
    private ActivityParams activityParams = new ActivityParams();
    private List<PfMember> pfMemberList;
    private PfMember selectedReferent;
    
   
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
        pfMemberList = pfMemberService.findAllPfMembers();
        initActivityParams();
    }
    
    public String createNewStep(){
        FacesUtils.putObjectInSessionMap("activityType", selectedType);
        return enterFlow(FlowType.STEP);
    }
    
    public String saveActivity(){
        try {
            if (application==null){
                FacesUtils.addMessage(null, FacesUtils.getI18nValueInMessages("label_error"), "application is null", FacesMessage.SEVERITY_ERROR);
                return endFlowAndRedirect();
            }
            //test if everything's ok
            
            Activity activity = new Activity();
            activity.setActivityParams(activityParams);
            activity.setSteps(stepList2ActivityStepsSet(stepDualListModel.getTarget(), activity));
            activity.setApplication(application);
            activity.setType(selectedType);
            activity.setReferent(selectedReferent);
            
            if (application.getId()!=null){
                System.out.println("app id: "+application.getId());
                applicationService.saveActivity(activity);
            }else{
                if (application.getActivities()==null){
                    Set<Activity> as = new HashSet<>();
                    as.add(activity);
                    application.setActivities(as);
                }else{
                    application.getActivities().add(activity);
                }
                FacesUtils.removeObjectFromSessionMap("application");
                FacesUtils.putObjectInSessionMap("application", application);
            }
            FacesUtils.addMessage(null, FacesUtils.getI18nValueInMessages("newActivity_added"), FacesUtils.getI18nValueInMessages("for_application")+" "+application.toString(), FacesMessage.SEVERITY_INFO);
            FacesUtils.keepMessageInFlash();
            return endFlowAndRedirect();
        } catch (Exception e) {
            e.printStackTrace();
            FacesUtils.addMessage(null, FacesUtils.getI18nValueInMessages("label_error"), e.getMessage(), FacesMessage.SEVERITY_ERROR);
            return null;
        }
    }
    
    public String cancelActivity(){
        return endFlowAndRedirect();
    }
    
    public TimeType[] getAvailableTimeTypes(){
        return TimeType.values();
    }

    public SampleType[] getAvailableSampleType() {
        return SampleType.values();
    }
    
    public ResultsSentByType[] getAvailableSentByType(){
        return ResultsSentByType.values();
    }

    public ActivityParams getActivityParams() {
        return activityParams;
    }

    public List<PfMember> getPfMemberList() {
        return pfMemberList;
    }

    public void setPfMemberList(List<PfMember> pfMemberList) {
        this.pfMemberList = pfMemberList;
    }

    public PfMember getSelectedReferent() {
        return selectedReferent;
    }

    public void setSelectedReferent(PfMember selectedReferent) {
        this.selectedReferent = selectedReferent;
    }

    public void setActivityParams(ActivityParams activityParams) {
        this.activityParams = activityParams;
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
    
    private Set<ActivityStep> stepList2ActivityStepsSet(List<Step> stepList, Activity activity){
        Set<ActivityStep> set = new HashSet<>();
        
        List<ActivityStep> activityStepList = new ArrayList<>();
        for (int i = 0; i < stepList.size(); i++) {
            
            ActivityStep activityStep = new ActivityStep();
            activityStep.setActivity(activity);
            activityStep.setStep(stepList.get(i));
            if (i==0){
                activityStep.setParentActivityStep(null);
            }else{
                activityStep.setParentActivityStep(activityStepList.get(i-1));
            }
            activityStepList.add(activityStep);
        }
        
        set.addAll(activityStepList);
        return set;
    }
    
    private void initActivityParams(){
        activityParams.setTuTimeOfBackupResults(new TimeUtil());
        activityParams.setTuTimeToAccomplish(new TimeUtil());
        activityParams.setTuTimeToDestructionSamples(new TimeUtil());
    }
}
