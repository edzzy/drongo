package fr.pfgen.lims.domain.people;

import fr.pfgen.lims.domain.equipments.Equipment;
import fr.pfgen.lims.domain.equipments.Funding;
import fr.pfgen.lims.domain.util.AbstractGenericEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

/**
 * Created by edouard on 03/03/14.
 */
@Entity
@Table(name="organisms")
public class Organism extends AbstractGenericEntity{

    @NotNull
    private String name;

    @OneToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH}, mappedBy = "organism")
    private Set<Funding> fundings;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString(){
        return name;
    }

    public Set<Funding> getFundings() {
        return fundings;
    }

    public void setFundings(Set<Funding> fundings) {
        this.fundings = fundings;
    }

}
