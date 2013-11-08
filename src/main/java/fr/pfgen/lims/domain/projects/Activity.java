/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pfgen.lims.domain.projects;

import fr.pfgen.lims.domain.runs.AbstractRun;
import fr.pfgen.lims.domain.util.AbstractGenericEntity;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author eric
 */
@Entity
@Table(name = "activities")
public class Activity extends AbstractGenericEntity {
    
    @OneToOne(optional=false)
    @JoinColumn(
    	name="activity_params_id", unique=true, nullable=false, updatable=false)
    private ActivityParams activityParams;
    
    @NotNull
    @ManyToOne
    private Application application;
    
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "runs_in_activities",
            joinColumns = { @JoinColumn(name = "run_id")},
            inverseJoinColumns = { @JoinColumn(name = "activity_id")})
    private Set<AbstractRun> runs;
    
    @NotNull
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Contract contract;

    public ActivityParams getActivityParams() {
        return activityParams;
    }

    public void setActivityParams(ActivityParams activityParams) {
        this.activityParams = activityParams;
    }

    public Set<AbstractRun> getRuns() {
        return runs;
    }

    public void setRuns(Set<AbstractRun> runs) {
        this.runs = runs;
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.application);
        hash = 97 * hash + Objects.hashCode(this.contract);
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
        if (!Objects.equals(this.application, other.application)) {
            return false;
        }
        if (!Objects.equals(this.contract, other.contract)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.application.getName();
    }
}
