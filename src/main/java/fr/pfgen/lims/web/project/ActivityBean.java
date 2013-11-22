/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pfgen.lims.web.project;

import fr.pfgen.lims.domain.projects.Activity;
import fr.pfgen.lims.domain.projects.ActivityParams;
import fr.pfgen.lims.domain.projects.Application;
import fr.pfgen.lims.domain.projects.ApplicationCategory;
import fr.pfgen.lims.domain.projects.ActivityType;
import fr.pfgen.lims.service.ApplicationService;
import fr.pfgen.lims.web.util.FacesUtils;
import fr.pfgen.lims.web.util.flows.ActivityFlow;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.component.UIOutput;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;
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
    
    private Activity expActivity;
    private Activity infoActivity;
    private List<SelectItem> availableExpItems;
    private List<SelectItem> availableInfoItems;
    private boolean infoChecked;
    private boolean expChecked;

    public void initActivities() {
        if (!FacesContext.getCurrentInstance().isPostback()) {
            expActivity = (Activity) FacesUtils.getObjectInSessionMap("expActivity");
            infoActivity = (Activity) FacesUtils.getObjectInSessionMap("infoActivity");

            if (expActivity == null) {
                expActivity = new Activity();
                FacesUtils.putObjectInSessionMap("expActivity", expActivity);
            }
            if (infoActivity == null) {
                infoActivity = new Activity();
                FacesUtils.putObjectInSessionMap("infoActivity", infoActivity);
            }
        }
    }

    @PostConstruct
    public void init() {
        populateApplicationLists();
    }
    
    public void onExpAppChanged(AjaxBehaviorEvent event) {
        Application app = (Application) ((UIOutput) event.getSource()).getValue();
        switchValuesAccordingToApplication(app, ActivityType.EXPERIMENTAL);
    }
    
    public void onInfoAppChanged(AjaxBehaviorEvent event){
        Application app = (Application) ((UIOutput) event.getSource()).getValue();
        switchValuesAccordingToApplication(app, ActivityType.ANALYSIS);
    }

    public boolean isInfoChecked() {
        return infoChecked;
    }

    public void setInfoChecked(boolean infoChecked) {
        this.infoChecked = infoChecked;
    }

    public boolean isExpChecked() {
        return expChecked;
    }

    public void setExpChecked(boolean expChecked) {
        this.expChecked = expChecked;
    }

    public List<SelectItem> getAvailableExpItems() {
        return availableExpItems;
    }

    public List<SelectItem> getAvailableInfoItems() {
        return availableInfoItems;
    }

    public Activity getExpActivity() {
        return expActivity;
    }

    public void setExpActivity(Activity expActivity) {
        this.expActivity = expActivity;
    }

    public Activity getInfoActivity() {
        return infoActivity;
    }

    public void setInfoActivity(Activity infoActivity) {
        this.infoActivity = infoActivity;
    }
    
    private void switchValuesAccordingToApplication(Application app, ActivityType appType){
        //ActivityParams activityParams = new ActivityParams();
        ActivityParams actParams = appParamsToActParams(app);
        switch(appType){
            case EXPERIMENTAL:
                //expActivity.setActivityParams(actParams);
                break;
            case ANALYSIS:
                //infoActivity.setActivityParams(actParams);
                break;
            default:
                
                break;
        }
    }
    
    private ActivityParams appParamsToActParams(Application app){
        /*
        ActivityParams actParams = new ActivityParams();
        
        actParams.setNbSamples(app.getApplicationParams().getNbSamples());
        actParams.setSampleType(app.getApplicationParams().getSampleType());
        actParams.setSampleConditioning(app.getApplicationParams().getSampleConditioning());
        actParams.setSampleTemperature(app.getApplicationParams().getSampleTemperature());
        
        if (infoChecked && app.getType().equals(ApplicationType.EXPERIMENTAL)){
            actParams.setResultsSentBy(null);
        }else{
            actParams.setResultsSentBy(app.getApplicationParams().getResultsSentBy());
        }
        
        actParams.setDaysOfBackupResults(app.getApplicationParams().getDaysOfBackupResults());
        
        return actParams;
        */
        return null;
    }
    
    private void populateApplicationLists(){
        /*
        List<Application> expAppList = applicationService.findAllExperimentalApplications();
        List<Application> infoAppList = applicationService.findAllBioinformaticsApplications();

        Map<ApplicationCategory, List<Application>> expMap = getAppCat2AppList(expAppList);
        
        availableExpItems = new ArrayList<>();
        for (ApplicationCategory appCat : expMap.keySet()) {
            SelectItemGroup group = new SelectItemGroup(appCat.getName());
            SelectItem[] items = new SelectItem[expMap.get(appCat).size()];
            for (int i = 0; i < expMap.get(appCat).size(); i++) {
                SelectItem item = new SelectItem(expMap.get(appCat).get(i));
                items[i] = item;
            }
            group.setSelectItems(items);
            availableExpItems.add(group);
        }
        
        Map<ApplicationCategory, List<Application>> infoMap = getAppCat2AppList(infoAppList);
        
        availableInfoItems = new ArrayList<>();
        for (ApplicationCategory appCat : infoMap.keySet()) {
            SelectItemGroup group = new SelectItemGroup(appCat.getName());
            SelectItem[] items = new SelectItem[infoMap.get(appCat).size()];
            for (int i = 0; i < infoMap.get(appCat).size(); i++) {
                SelectItem item = new SelectItem(infoMap.get(appCat).get(i));
                items[i] = item;
            }
            group.setSelectItems(items);
            availableInfoItems.add(group);
        }
        */ 
    }
    
    private Map<ApplicationCategory, List<Application>> getAppCat2AppList(List<Application> appList){
        Map<ApplicationCategory, List<Application>> map = new HashMap<>();
        
        for (Application app : appList) {
            if (map.containsKey(app.getCategory())){
                map.get(app.getCategory()).add(app);
            }else{
                List<Application> newList = new ArrayList<>();
                newList.add(app);
                map.put(app.getCategory(), newList);
            }
        }
        
        return map;
    }
}
