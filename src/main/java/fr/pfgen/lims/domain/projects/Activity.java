/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pfgen.lims.domain.projects;

import fr.pfgen.lims.domain.people.PfMember;
import fr.pfgen.lims.domain.util.AbstractGenericEntity;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
    private ApplicationType type;
    
    @NotNull
    @ManyToOne
    private PfMember referent;
    
    @OneToMany(mappedBy = "experimentalActivity")
    private Set<ApplicationParams> experimentalParams;
    
    @OneToMany(mappedBy = "analysisActivity")
    private Set<ApplicationParams> analysisParams;

    public Set<ApplicationParams> getExperimentalParams() {
        return experimentalParams;
    }

    public void setExperimentalParams(Set<ApplicationParams> experimentalParams) {
        this.experimentalParams = experimentalParams;
    }

    public Set<ApplicationParams> getAnalysisParams() {
        return analysisParams;
    }

    public void setAnalysisParams(Set<ApplicationParams> analysisParams) {
        this.analysisParams = analysisParams;
    }


    public PfMember getReferent() {
        return referent;
    }

    public void setReferent(PfMember referent) {
        this.referent = referent;
    }

    public ApplicationType getType() {
        return type;
    }

    public void setType(ApplicationType type) {
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
