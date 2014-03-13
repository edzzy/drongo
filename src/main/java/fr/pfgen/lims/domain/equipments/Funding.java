package fr.pfgen.lims.domain.equipments;

import fr.pfgen.lims.domain.people.Organism;
import fr.pfgen.lims.domain.util.AbstractGenericEntity;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * Created by edouard on 04/03/14.
 */
@Entity
@Table(name="fundings")
public class Funding extends AbstractGenericEntity {

    @ManyToOne(cascade = CascadeType.ALL)
    private Organism organism;

    @ManyToOne(cascade = CascadeType.ALL)
    private Equipment equipment;

    @Max(100)
    @Min(0)
    private Double percent;

    private String context;

    public Organism getOrganism() {
        return organism;
    }

    public void setOrganism(Organism organism) {
        this.organism = organism;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    public Double getPercent() {
        return percent;
    }

    public void setPercent(Double percent) {
        this.percent = percent;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }
}
