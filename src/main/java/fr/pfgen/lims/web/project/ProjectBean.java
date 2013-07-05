/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pfgen.lims.web.project;


import fr.pfgen.lims.domain.people.Client;
import fr.pfgen.lims.domain.projects.Project;
import fr.pfgen.lims.service.ProjectService;
import fr.pfgen.lims.service.ClientService;
import java.io.Serializable;
import java.util.List;
import org.apache.commons.lang3.text.WordUtils;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author edouard
 */
@Controller
@Scope("view")
@ManagedBean

public class ProjectBean implements Serializable{
     
        
    @Autowired
    ProjectService projectService;
    
    
    @Autowired
    ClientService clientService;
    
    private List<Project> projectList;
    private List<Project> filteredProjects; 
    private List<Client> clientList;

    public List<Client> getClientList() {
        return clientList;
    }

    public void setClientList(List<Client> clientList) {
        this.clientList = clientList;
    }
    

    public ProjectService getProjectService() {
        return projectService;
    }

    public void setProjectService(ProjectService projectService) {
        this.projectService = projectService;
    }

    public List<Project> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<Project> projectList) {
        this.projectList = projectList;
    }

    public List<Project> getFilteredProjects() {
        return filteredProjects;
    }

    public void setFilteredProjects(List<Project> filteredProjects) {
        this.filteredProjects = filteredProjects;
    }
    
   @PostConstruct
   public void init(){
       projectList = projectService.findAllProjects();
       clientList = clientService.findAllClients();
      
   }
   
   public void onEdit(RowEditEvent event){
       Project projectToEdit = (Project) event.getObject();
       FacesContext context = FacesContext.getCurrentInstance();
       
       try {
           Project projectUpdated = projectService.updateProject(projectToEdit);
           int index = projectList.indexOf(projectToEdit);
           projectList.remove(index);
           projectList.add(index,projectUpdated);
           RequestContext rcontext = RequestContext.getCurrentInstance();
           rcontext.update("projectTable");
           
           String text = context.getApplication().getResourceBundle(context, "messages").getString("edit_done");
           FacesMessage msg = new FacesMessage(text, ((Project) event.getObject()).toString());
           context.addMessage(null, msg);
       } catch (Exception e) {
           String text = context.getApplication().getResourceBundle(context, "messages").getString("edit_error");
           FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, text, e.getMessage());
           context.addMessage(null, msg);
       }
       
   }
    
   public void onCancel(RowEditEvent event) {
       FacesContext context = FacesContext.getCurrentInstance();
       String text = context.getApplication().getResourceBundle(context, "messages").getString("edit_cancelled");
       FacesMessage msg = new FacesMessage(text, ((Project) event.getObject()).toString());
       
       context.addMessage(null, msg);
   
   }
   
   public void onEditInit(RowEditEvent event){
       Project projectToEdit = (Project) event.getObject();
       Project projectIdDb = projectService.findProject(projectToEdit.getId());
       
       if(projectToEdit.getVersion() != projectIdDb.getVersion()){
           int index = projectList.indexOf(projectToEdit);
           projectList.remove(index);
           projectList.add(index, projectIdDb);
           RequestContext rcontext = RequestContext.getCurrentInstance();
           rcontext.update("projectTable");
           FacesContext context = FacesContext.getCurrentInstance();
           String text = context.getApplication().getResourceBundle(context, "messages").getString("edit_rowchanged");
           FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, text, projectToEdit.toString());
           
           context.addMessage(null, msg);
       }
   }
    
    
}
