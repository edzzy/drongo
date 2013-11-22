/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pfgen.lims.domain.projects;

import fr.pfgen.lims.domain.people.PfMember;
import fr.pfgen.lims.domain.util.AbstractGenericEntity;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
    
    @NotNull
    @ManyToOne
    private Application application;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ActivityType type;
    
    @NotNull
    @ManyToOne
    private PfMember referent;
    
    @OneToMany(mappedBy = "activity")
    private Set<Contract> contracts;
    
    @OneToOne(optional=false, cascade = CascadeType.ALL)
    @JoinColumn(name="activity_params_id", unique=true, nullable=false, updatable=true)
    private ActivityParams activityParams;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "activity")
    private Set<ActivityStep> steps;

    public Set<ActivityStep> getSteps() {
        return steps;
    }

    public void setSteps(Set<ActivityStep> steps) {
        this.steps = steps;
    }

    public Set<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(Set<Contract> contracts) {
        this.contracts = contracts;
    }

    public ActivityParams getActivityParams() {
        return activityParams;
    }

    public void setActivityParams(ActivityParams activityParams) {
        this.activityParams = activityParams;
    }

    public PfMember getReferent() {
        return referent;
    }

    public void setReferent(PfMember referent) {
        this.referent = referent;
    }

    public ActivityType getType() {
        return type;
    }

    public void setType(ActivityType type) {
        this.type = type;
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.application);
        hash = 89 * hash + Objects.hashCode(this.type);
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
        if (this.type != other.type) {
            return false;
        }
        return true;
    }
}
