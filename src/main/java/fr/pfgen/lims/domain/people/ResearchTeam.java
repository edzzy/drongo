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
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author eric
 */
@Entity
@Table(name = "research_teams", uniqueConstraints = @UniqueConstraint(columnNames = {"research_unit", "name"}))
public class ResearchTeam extends AbstractGenericEntity{
    
    @NotNull
    @Size(min = 2, max = 50)
    private String name;

    @NotNull
    @ManyToOne
    private ResearchUnit researchUnit;
    
    @OneToMany(mappedBy = "researchTeam", cascade = CascadeType.ALL)
    private Set<Client> clients = new HashSet<>();

    public Set<Client> getClients() {
        return clients;
    }

    public void setClients(Set<Client> clients) {
        this.clients = clients;
    }

    public ResearchUnit getResearchUnit() {
        return researchUnit;
    }

    public void setResearchUnit(ResearchUnit researchUnit) {
        this.researchUnit = researchUnit;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.name);
        hash = 79 * hash + Objects.hashCode(this.researchUnit);
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
        final ResearchTeam other = (ResearchTeam) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.researchUnit, other.researchUnit)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.name+" ("+this.researchUnit.getName()+")";
    }
}
