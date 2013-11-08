/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pfgen.lims.web.project;

import fr.pfgen.lims.domain.projects.Activity;
import fr.pfgen.lims.domain.projects.Application;
import fr.pfgen.lims.service.ApplicationService;
import fr.pfgen.lims.web.util.FacesUtils;
import fr.pfgen.lims.web.util.flows.ActivityFlow;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author eric
 */
@Component
@Scope("view")
public class ActivityBean extends ActivityFlow implements Serializable{
    
    @Autowired
    ApplicationService applicationService;
    
    private Activity activity;
    private List<Application> expAppList;
    private List<Application> infoAppList;
    
    public void initActivity(){
        if (!FacesContext.getCurrentInstance().isPostback()) {
            activity = (Activity) FacesUtils.getObjectInSessionMap("activity");
            
            if (activity==null){
                activity = new Activity();
                FacesUtils.putObjectInSessionMap("activity", activity);
            }
        }
    }
    
    @PostConstruct
    public void init(){
        expAppList = applicationService.findAllExperimentalApplications();
        infoAppList = applicationService.findAllBioinformaticsApplications();
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public List<Application> getExpAppList() {
        return expAppList;
    }

    public void setExpAppList(List<Application> expAppList) {
        this.expAppList = expAppList;
    }

    public List<Application> getInfoAppList() {
        return infoAppList;
    }

    public void setInfoAppList(List<Application> infoAppList) {
        this.infoAppList = infoAppList;
    }
}
