/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pfgen.lims.web.project;

import fr.pfgen.lims.domain.people.Client;
import fr.pfgen.lims.domain.projects.Project;
import fr.pfgen.lims.service.ClientService;
import fr.pfgen.lims.service.ProjectService;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import org.apache.commons.lang3.text.WordUtils;
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

public class CreateProjectBean implements Serializable{
    
    
    private Project newProject = new Project();
    
    private List<Client> clientList;

    public List<Client> getClientList() {
        return clientList;
    }

    public void setClientList(List<Client> clientList) {
        this.clientList = clientList;
    }
    @Autowired
    private ProjectService projectService;
    
    @Autowired
    private ClientService clientService;
    
    @PostConstruct
    public void init(){
        clientList = clientService.findAllClients();
    }
    
    public Project getNewProject(){
        return newProject;
    }
    
    public void setNewProject(Project newProject){
    
        this.newProject = newProject;
    }
    
    public String saveNewProject(){
    
        
        try{
            newProject.setName(newProject.getName());
            newProject.setBegin_date(newProject.getBegin_date());
            newProject.setDue_date(newProject.getDue_date());
            newProject.setResponsable(newProject.getResponsable());
            projectService.saveProject(newProject);
            FacesContext context = FacesContext.getCurrentInstance();
            String text = context.getApplication().getResourceBundle(context,"messages").getString("newProject_added");
            FacesMessage msg = new FacesMessage(text, newProject.toString());
            context.addMessage(null, msg);
            context.getExternalContext().getFlash().setKeepMessages(true);
            return "project?faces-redirect=true";
        }catch (Exception e){
            FacesContext context = FacesContext.getCurrentInstance();
            String text = context.getApplication().getResourceBundle(context, "messages").getString("label_error");
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, text, e.getMessage());
            context.addMessage(null, msg);
            return null;
        }
    }
    
    
}
