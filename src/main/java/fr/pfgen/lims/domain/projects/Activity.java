/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pfgen.lims.domain.projects;

import fr.pfgen.lims.domain.runs.AbstractRun;
import fr.pfgen.lims.domain.util.AbstractGenericEntity;
import java.util.Date;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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
@Table(name = "activities")
public class Activity extends AbstractGenericEntity {

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
    
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "runs_in_activities",
            joinColumns = { @JoinColumn(name = "run_id")},
            inverseJoinColumns = { @JoinColumn(name = "activity_id")})
    private Set<AbstractRun> runs;
    
    @NotNull
    @ManyToOne
    private Contract contract;

    public Set<AbstractRun> getRuns() {
        return runs;
    }

    public void setRuns(Set<AbstractRun> runs) {
        this.runs = runs;
    }

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

    

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + Objects.hashCode(this.scheduledBeginDate);
        hash = 23 * hash + Objects.hashCode(this.type);
        hash = 23 * hash + Objects.hashCode(this.contract);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Activity other = (Activity) obj;
        if (!Objects.equals(this.scheduledBeginDate, other.scheduledBeginDate)) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        if (!Objects.equals(this.contract, other.contract)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        //a revoir !!
        return "TODO";
    }
}
