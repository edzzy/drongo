/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pfgen.lims.domain.projects;

import fr.pfgen.lims.domain.util.AbstractGenericEntity;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author eric
 */
@Entity
@Table(name = "activity_steps")
public class ActivityStep extends AbstractGenericEntity {

    @NotNull
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    private Activity activity;
    
    @NotNull
    @ManyToOne
    private Step step;
    
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "parent_activity_step")
    private ActivityStep parentActivityStep;
    
    @OneToMany(mappedBy = "parentActivityStep", cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    private Set<ActivityStep> childActivityStep;

    public ActivityStep getParentActivityStep() {
        return parentActivityStep;
    }

    public void setParentActivityStep(ActivityStep parentActivityStep) {
        this.parentActivityStep = parentActivityStep;
    }
    
    public Set<ActivityStep> getChildActivityStep() {
        return childActivityStep;
    }

    public void setChildActivityStep(Set<ActivityStep> childActivityStep) {
        this.childActivityStep = childActivityStep;
    }

    public Step getStep() {
        return step;
    }

    public void setStep(Step step) {
        this.step = step;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.activity);
        hash = 97 * hash + Objects.hashCode(this.step);
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
        final ActivityStep other = (ActivityStep) obj;
        if (!Objects.equals(this.activity, other.activity)) {
            return false;
        }
        if (!Objects.equals(this.step, other.step)) {
            return false;
        }
        return true;
    }
}
