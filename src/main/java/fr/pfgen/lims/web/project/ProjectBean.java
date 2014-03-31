/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pfgen.lims.web.project;

import fr.pfgen.lims.domain.projects.Project;
import fr.pfgen.lims.service.ProjectService;
import fr.pfgen.lims.web.util.FacesUtils;
import fr.pfgen.lims.web.util.flows.ProjectFlow;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author edouard
 */
@Component
@Scope("view")
public class ProjectBean extends ProjectFlow implements Serializable {

    @Autowired
    ProjectService projectService;
    
    private Project project;
    
    public void initProject(){
        if (!FacesContext.getCurrentInstance().isPostback()) {
            project = (Project) FacesUtils.getObjectInSessionMap("project");
            if (project==null){
                project = new Project();
                FacesUtils.putObjectInSessionMap("project", project);
            }
        }
    }
    
    public void validateName(FacesContext context, UIComponent component, Object value) {
        String name = ((String) value).toLowerCase();

        Project existingProject = projectService.findProjectByName(name);

        if ((existingProject != null && existingProject.getId() != project.getId()) || (existingProject != null && existingProject.getId() != project.getId())) {
            ((UIInput) component).setValid(false);
            FacesUtils.addMessage(component.getClientId(context), FacesUtils.getI18nValueInMessages("label_error"), "\"" + name + "\" " + FacesUtils.getI18nValueInMessages("label_alreadyExists"), FacesMessage.SEVERITY_ERROR);
        }
    }
    
    public String saveProject(){
        try{
            if (project.getId() == null){
                projectService.saveProject(project);
                FacesUtils.addMessage(null, FacesUtils.getI18nValueInMessages("newProject_added"), project.toString(), FacesMessage.SEVERITY_INFO);
            }else{
                projectService.updateProject(project);
                FacesUtils.addMessage(null, FacesUtils.getI18nValueInMessages("edit_done"), project.toString(), FacesMessage.SEVERITY_INFO);
            }
            FacesUtils.keepMessageInFlash();
            return endFlowAndRedirect();
        }catch(Exception e){
            FacesUtils.addMessage(null, FacesUtils.getI18nValueInMessages("label_error"), e.getMessage(), FacesMessage.SEVERITY_ERROR);
            return null;
        }
    }
    
    public String cancelProject(){
        if (project.getId() == null) {
            FacesUtils.addMessage(null, FacesUtils.getI18nValueInMessages("label_createCanceled"), null, FacesMessage.SEVERITY_INFO);
        } else {
            FacesUtils.addMessage(null, FacesUtils.getI18nValueInMessages("edit_cancelled"), project.toString(), FacesMessage.SEVERITY_INFO);
        }
        FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        
        return endFlowAndRedirect();
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
