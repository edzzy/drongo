/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pfgen.lims.domain.people;

import fr.pfgen.lims.domain.util.AbstractGenericEntity;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author eric
 */
@Entity
@Table(name = "research_units")
public class ResearchUnit extends AbstractGenericEntity{
    
    @NotNull
    @Column(unique = true)
    @Size(min = 2, max = 50)
    private String name;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "researchUnit")
    private Set<ResearchTeam> researchTeams = new HashSet<>();

    public Set<ResearchTeam> getResearchTeams() {
        return researchTeams;
    }

    public void setResearchTeams(Set<ResearchTeam> researchTeams) {
        this.researchTeams = researchTeams;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + Objects.hashCode(this.name);
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
        final ResearchUnit other = (ResearchUnit) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
