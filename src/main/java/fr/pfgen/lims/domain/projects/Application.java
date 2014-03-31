/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pfgen.lims.domain.projects;

import fr.pfgen.lims.domain.util.AbstractGenericEntity;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
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
    @Size(min = 3, max = 5)
    @Column(unique = true)
    private String code;
    
    @OneToMany(mappedBy = "application",cascade = CascadeType.ALL)
    private Set<Activity> activities;
    
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    private ApplicationCategory category;

    public ApplicationCategory getCategory() {
        return category;
    }

    public void setCategory(ApplicationCategory category) {
        this.category = category;
    }
    
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
