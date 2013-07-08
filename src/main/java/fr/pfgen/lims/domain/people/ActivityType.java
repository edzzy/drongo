/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pfgen.lims.domain.people;

import fr.pfgen.lims.domain.projects.Activity;
import fr.pfgen.lims.domain.util.AbstractGenericEntity;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author eric
 */
@Entity
@Table(name = "activity_types")
public class ActivityType extends AbstractGenericEntity{
    
    @NotNull
    @Size(min = 2, max = 50)
    private String name;
    
    @OneToMany(mappedBy = "type")
    private Set<Activity> activities;
    
    @NotNull
    @ManyToOne
    private PfMember referent;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Activity> getActivities() {
        return activities;
    }

    public void setActivities(Set<Activity> activities) {
        this.activities = activities;
    }

    public PfMember getReferent() {
        return referent;
    }

    public void setReferent(PfMember referent) {
        this.referent = referent;
    }
    
}
