/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pfgen.lims.web.project;

import fr.pfgen.lims.domain.projects.Project;
import fr.pfgen.lims.service.ProjectService;
import fr.pfgen.lims.web.util.FacesUtils;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.model.SelectItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author eric
 */
@Component
@Scope("view")
public class ProjectsBean implements Serializable{
    
    private List<Project> projectList;
    private List<Project> filteredProjects;
    //private SelectItem[] projectClosedOptions;
    private Project selectedProject;
    
    @Autowired
    ProjectService projectService;
    
    @PostConstruct
    private void init() {
        projectList = projectService.findAllProjects();
    }
    
    public String createNewProject(){
        return "project?faces-redirect=true";
    }

    public SelectItem[] getProjectClosedOptions() {
        SelectItem[] options = new SelectItem[3];
        
        options[0] = new SelectItem("", FacesUtils.getI18nValue("label_select"));
        options[1] = new SelectItem("false", FacesUtils.getI18nValue("label_opened"));
        options[2] = new SelectItem("true", FacesUtils.getI18nValue("label_closed"));
         
        return options;
    }

    public void deleteProject() {
        try {
            projectService.deleteProject(selectedProject);
            projectList.remove(selectedProject);
            FacesUtils.addMessage(null, FacesUtils.getI18nValue("label_deleteDone"), selectedProject.toString(), FacesMessage.SEVERITY_INFO);
        } catch (Exception e) {
            FacesUtils.addMessage(null, FacesUtils.getI18nValue("label_error"), e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

    public void cancelDeletion() {
        FacesUtils.addMessage(null, FacesUtils.getI18nValue("label_deleteCanceled"), selectedProject.toString(), FacesMessage.SEVERITY_INFO);
    }
    
    public Project getSelectedProject() {
        return selectedProject;
    }

    public void setSelectedProject(Project selectedProject) {
        this.selectedProject = selectedProject;
    }
    
    public int getProjectNumber() {
        return projectList.size();
    }
    
    public int getOpenProjectNumber(){
        int i = 0;
        for (Project project : projectList) {
            if (!project.isClosed()) i++;
        }
        return i;
    }
    
    public int getClosedProjectNumber(){
        int i = 0;
        for (Project project : projectList) {
            if (project.isClosed()) i++;
        }
        return i;
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
}
