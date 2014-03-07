package fr.pfgen.lims.domain.people;

import fr.pfgen.lims.domain.equipments.Equipment;
import fr.pfgen.lims.domain.util.AbstractGenericEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
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



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
