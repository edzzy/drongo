/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pfgen.lims.web.project;

import fr.pfgen.lims.domain.people.Client;
import fr.pfgen.lims.domain.projects.Project;
import fr.pfgen.lims.service.ClientService;
import fr.pfgen.lims.service.ProjectService;
import fr.pfgen.lims.web.util.FacesUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.component.UIOutput;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import org.primefaces.event.FlowEvent;
import org.primefaces.model.DualListModel;
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
public class ProjectBean implements Serializable {

    @Autowired
    ProjectService projectService;
    
    @Autowired
    ClientService clientService;
    
    private Project project = new Project();
    private List<Client> clientList;
    private List<Client> clientSource = new ArrayList<>();
    private List<Client> clientTarget = new ArrayList<>();
    private DualListModel<Client> allClients;

    @PostConstruct
    public void init() {
        clientList = clientService.findAllActiveClients();
        clientSource.addAll(clientList);
        allClients = new DualListModel<>(clientSource, clientTarget);
    }
    
    public void validateMainClient(FacesContext context, UIComponent component, Object value) {
        Client c = (Client) value;

        Project existingProject = projectService.findProjectByNameAndClient(project.getName(), c);

        if ((existingProject != null && existingProject.getId() != project.getId())) {
            ((UIInput) component).setValid(false);
            FacesUtils.addMessage(component.getClientId(context), FacesUtils.getI18nValue("label_error"), "\"" + c.toString() + "\" " + FacesUtils.getI18nValue("project_nameClientExist"), FacesMessage.SEVERITY_ERROR);
        }
    }
    
    public void onMainClientSelect(AjaxBehaviorEvent event){
        clientSource.clear();
        clientSource.addAll(clientList);
        clientSource.remove((Client) ((UIOutput) event.getSource()).getValue());
    }
    
    public String createNewClient(){
        FacesUtils.removeObjectFromSessionMap("client");
        FacesUtils.putObjectInSessionMap("project", project);
        return "/pages/people/client?faces-redirect=true";
    }
    
    public String createNewContract(){
        FacesUtils.removeObjectFromSessionMap("project");
        FacesUtils.putObjectInSessionMap("project", project);
        return "/pages/projects/contract?faces-redirect=true";
    }
    
    public String saveNewProject(){
        try{
            if (project.getId() == null){
                projectService.saveProject(project);
                FacesUtils.addMessage(null, FacesUtils.getI18nValue("newProject_added"), project.toString(), FacesMessage.SEVERITY_INFO);
            }else{
                projectService.updateProject(project);
                FacesUtils.addMessage(null, FacesUtils.getI18nValue("edit_done"), project.toString(), FacesMessage.SEVERITY_INFO);
            }
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
            return "projects?faces-redirect=true";
        }catch(Exception e){
            FacesUtils.addMessage(null, FacesUtils.getI18nValue("label_error"), e.getMessage(), FacesMessage.SEVERITY_ERROR);
            return null;
        }
    }
    
    public String onFlowProcess(FlowEvent event) {  
        if (event.getOldStep().equalsIgnoreCase("clientsTab")){
            project.setClients(new HashSet<>(allClients.getTarget()));
        }
        return event.getNewStep();
    }  

    public List<Client> getClientTarget() {
        return clientTarget;
    }

    public void setClientTarget(List<Client> clientTarget) {
        this.clientTarget = clientTarget;
    }

    public List<Client> getClientSource() {
        return clientSource;
    }

    public void setClientSource(List<Client> clientSource) {
        this.clientSource = clientSource;
    }

    public DualListModel<Client> getAllClients() {
        return allClients;
    }

    public void setAllClients(DualListModel<Client> allClients) {
        this.allClients = allClients;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public List<Client> getClientList() {
        return clientList;
    }

    public void setClientList(List<Client> clientList) {
        this.clientList = clientList;
    }
}
