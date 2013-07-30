/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pfgen.lims.web.util;


import fr.pfgen.lims.domain.projects.Project;
import fr.pfgen.lims.service.ProjectService;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import org.primefaces.extensions.model.timeline.TimelineEvent;
import org.primefaces.extensions.model.timeline.TimelineModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;


/**
 *
 * @author edouard
 */
@Controller
@ManagedBean
@Scope("view")
public class TimelineBean implements Serializable {
    
    private TimelineModel model;

    public TimelineModel getModel() {
        return model;
    }

    public void setModel(TimelineModel model) {
        this.model = model;
    }
    
    @Autowired
    ProjectService projectService;
    private List<Project> projectList;
    
    @PostConstruct
    protected void initialize(){
       projectList = projectService.findAllProjects(); 
       
       model = new TimelineModel();
       Calendar cal = Calendar.getInstance();  
        cal.set(2013, Calendar.AUGUST, 15, 0, 0, 0);  
         Project p = new Project();
         Project p2 = new Project();
         cal.set(2013,Calendar.JULY,29,0,0,0);
         p.setCreated(cal.getTime());
         cal.set(2013,Calendar.AUGUST,29,0,0,0);
         p2.setCreated(cal.getTime());
         cal.set(2013,Calendar.SEPTEMBER,29,0,0,0);
        // p.setEndDate(cal.getTime());
         cal.set(2013,Calendar.OCTOBER,29,0,0,0);
         projectList.add(p);
         projectList.add(p2);
         p.setName("Projet 1");
         p2.setName("Projet 2");
       //  p.setEndDate(cal.getTime());
               for(Project project : projectList){
                 
                if(project.getCreated() != null){
                    model.add(new TimelineEvent(project.getName(),project.getCreated(),cal.getTime()));
           }
       }
       
        
    }
}
