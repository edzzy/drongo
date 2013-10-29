/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pfgen.lims.domain.projects;

import fr.pfgen.lims.domain.people.PfMember;
import fr.pfgen.lims.domain.util.AbstractGenericEntity;
import fr.pfgen.lims.domain.util.ApplicationType;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
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
@Table(name = "applications")
public class Application extends AbstractGenericEntity{
    
    @NotNull
    @Size(min = 2, max = 100)
    @Column(unique = true)
    private String name;
    
    @NotNull
    @Size(min = 3, max = 3)
    @Column(unique = true)
    private String code;
    
    @OneToMany(mappedBy = "application")
    private Set<Activity> activities;
    
    @NotNull
    @ManyToOne
    private PfMember referent;
    
    @NotNull
    private ApplicationType applicationType;
    
    @NotNull
    @OneToMany(mappedBy = "application")
    private DefaultApplicationParams defaultParams;

    public DefaultApplicationParams getDefaultParams() {
        return defaultParams;
    }

    public void setDefaultParams(DefaultApplicationParams defaultParams) {
        this.defaultParams = defaultParams;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ApplicationType getApplicationType() {
        return applicationType;
    }

    public void setApplicationType(ApplicationType applicationType) {
        this.applicationType = applicationType;
    }

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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.code);
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
        final Application other = (Application) obj;
        if (!Objects.equals(this.code, other.code)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.name+" ("+this.code+")";
    }
}
