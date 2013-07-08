/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pfgen.lims.domain.projects;

import fr.pfgen.lims.domain.people.ActivityType;
import fr.pfgen.lims.domain.util.AbstractGenericEntity;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author eric
 */
@Entity
@Table(name = "activities")
public class Activity extends AbstractGenericEntity{
    
    @NotNull
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(style = "M-")
    private Date scheduledBeginDate;
    
    @NotNull
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(style = "M-")
    private Date scheduledDueDate;
   
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(style = "M-")
    private Date beginDate;
    
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(style = "M-")
    private Date endDate;

    @NotNull
    @ManyToOne
    private ActivityType type;
    
    @NotNull
    @ManyToOne
    private Project project;

    public Date getScheduledBeginDate() {
        return scheduledBeginDate;
    }

    public void setScheduledBeginDate(Date scheduledBeginDate) {
        this.scheduledBeginDate = scheduledBeginDate;
    }

    public Date getScheduledDueDate() {
        return scheduledDueDate;
    }

    public void setScheduledDueDate(Date scheduledDueDate) {
        this.scheduledDueDate = scheduledDueDate;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public ActivityType getType() {
        return type;
    }

    public void setType(ActivityType type) {
        this.type = type;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
