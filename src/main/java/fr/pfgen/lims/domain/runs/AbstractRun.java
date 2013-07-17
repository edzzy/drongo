/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pfgen.lims.domain.runs;

import fr.pfgen.lims.domain.people.PfMember;
import fr.pfgen.lims.domain.projects.Activity;
import fr.pfgen.lims.domain.util.AbstractGenericEntity;
import fr.pfgen.lims.domain.util.RunType;
import java.util.Date;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
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
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "runs")
public class AbstractRun extends AbstractGenericEntity{
    
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date beginDate;
    
    @NotNull
    @ManyToOne
    private PfMember launchedBy;
    
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(length = 30)
    private RunType runType;
    
    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "runs")
    private Set<Activity> activities;
    
    @NotNull
    private boolean finished = false;

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public Set<Activity> getActivities() {
        return activities;
    }

    public void setActivities(Set<Activity> activities) {
        this.activities = activities;
    }

    public RunType getRunType() {
        return runType;
    }

    public void setRunType(RunType runType) {
        this.runType = runType;
    }
   
    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public PfMember getLaunchedBy() {
        return launchedBy;
    }

    public void setLaunchedBy(PfMember launchedBy) {
        this.launchedBy = launchedBy;
    }
}
