package fr.pfgen.lims.domain.people;

import fr.pfgen.lims.domain.util.AbstractGenericEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Created by edouard on 03/03/14.
 */
@Entity
@Table(name="structures")
public class Structure extends AbstractGenericEntity {
    @NotNull
    private String name;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
